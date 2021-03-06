package com.go2wheel.katharsis.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.BaseEntity;
import com.go2wheel.facade.FacadeRepositoryBase;
import com.go2wheel.facade.Page;
import com.go2wheel.katharsis.dto.Dto;
import com.go2wheel.katharsis.dto.converter.DtoConverter;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.exception.AppException;
import com.go2wheel.katharsis.exception.UnsortableException;
import com.go2wheel.katharsis.exception.UnsupportedRelationException;
import com.go2wheel.util.PropertyCopyUtil;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceListBase;

public abstract class DtoRepositoryBase<T extends Dto, L extends ResourceListBase<T, DtoListMeta, DtoListLinks>, E extends BaseEntity, F extends FacadeRepositoryBase<E, T>>
		extends ResourceRepositoryBase<T, Long> {
	
	private static Logger log = LoggerFactory.getLogger(DtoRepositoryBase.class);

	private final F repository;

	private final Class<L> resourceListClass;
	
	private final Class<E> entityClass;
	
	private Validator validator;
	
	private final DtoConverter<E, T> converter;
	
	@Autowired
	private PropertyCopyUtil propertyCopyUtil;
	
	public void validate(Dto o, Class<?>...groups) {
		Set<ConstraintViolation<Dto>> cve = validator.validate(o, groups);
		if (!cve.isEmpty()) {
			throw new ConstraintViolationException(cve);
		}
	}

	protected DtoRepositoryBase(Class<T> resourceClass, Class<L> resourceListClass,Class<E> entityClass, F repository, DtoConverter<E, T> converter) {
		super(resourceClass);
		this.repository = repository;
		this.resourceListClass = resourceListClass;
		this.entityClass = entityClass;
		this.converter = converter;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
	}
	
	//MethodSecurityMetadataSourceAdvisor
	@Override
	public void delete(Long id) {
		E entity = repository.findOne(id, true);
		repository.delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <S extends T> S save(S dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return (S) createNew(dto);
		} else {
			return (S) modify(dto);
		}
	}
	
	public T modify(T dto) {
		validate(dto);
		E entity = repository.findOne(dto.getId(), false);
		getPropertyCopyUtil().applyPatch(entity,dto);
		return converter.entity2Dto(saveToBackendRepo(dto, entity), Scenario.MODIFY);
	}
	
	public T createNew(T dto) {
		validate(dto);
		try {
			E entity = entityClass.newInstance();
			entity = getRepository().newByDto(dto);
			return converter.entity2Dto(saveToBackendRepo(dto, entity), Scenario.NEW);
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("instantiationException {}", entityClass.getName());
			throw new AppException().addError(1000, entityClass.getName(), "cannot instantiation " + entityClass.getName());
		}
	}
	
	public E saveToBackendRepo(T dto, E entity) {
		return repository.save(entity, dto);
	}
	
	@Override
	public T findOne(Long id, QuerySpec querySpec) {
		E entity = repository.findOne(id, false);
		return converter.entity2Dto(entity, Scenario.FIND_ONE);
	}

	@Override
	public L findAll(Iterable<Long> ids, QuerySpec querySpec) {
		List<E> bus = new ArrayList<>();
		for (Long lid : ids) {
			bus.add(repository.findOne(lid, false));
		}
		L udl;
		try {
			udl = resourceListClass.newInstance();
			udl.addAll(bus.stream().map(entity -> converter.entity2Dto(entity, Scenario.FIND_LIST)).collect(Collectors.toList()));
			return udl;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Branch out from here. Implements common pattern here.
	 * [creator.id EQ [229376]]
	 */
	@Override
	public L findAll(QuerySpec querySpec) {
		List<Long> ids = QuerySpecUtil.hasMyId(querySpec);
		List<E> entities = new ArrayList<>();
		if (ids.size() > 0) {
			entities = ids.stream().map(id -> repository.findOne(id, true)).filter(ne -> ne != null).collect(Collectors.toList());
			return convertToResourceList(entities, entities.size(), Scenario.FIND_LIST);
		}
		
		List<String> unsported = checkAllSortableFieldAllowed(querySpec); 
		if (unsported != null && unsported.size() > 0) {
			 throw new UnsortableException(String.join(",", unsported));
		}

		RelationQuery rq = QuerySpecUtil.findRelationQuery(querySpec); 
		if (rq == null) {
			return findAllWithQuerySpec(querySpec);
		} else {
			return findWithRelationAndSpec(rq, querySpec);
		}
	}
	
	protected abstract L findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec);

	protected abstract List<String> checkAllSortableFieldAllowed(QuerySpec querySpec);

	protected abstract L findAllWithQuerySpec(QuerySpec querySpec);

	protected L convertToResourceList(List<E> entities, long count, Scenario scenario) {
		List<T> list = entities.stream().map(entity -> converter.entity2Dto(entity, scenario)).collect(Collectors.toList());		
		L listOb = null;
		try {
			listOb = resourceListClass.newInstance();
			listOb.setMeta(new DtoListMeta(count));
			listOb.setLinks(new DtoListLinks());
			listOb.addAll(list);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return listOb;
	}
	
	protected L convertToResourceList(Page<E> page, Scenario scenario) {
		return convertToResourceList(page.getContent(), page.getTotalResourceCount(), scenario);
	}
	
	protected L convertToResourceList(E entity, Scenario scenario) {
		List<E> l = new ArrayList<>();
		l.add(entity);
		return convertToResourceList(l, 1, scenario);
	}
	
	
	public F getRepository() {
		return repository;
	}

	public DtoConverter<E, T> getConverter() {
		return converter;
	}
	
	protected UnsupportedRelationException getUnsupportRelationException(String thisDtoName, String oppositeName) {
		return new UnsupportedRelationException(String.format("%s's opposite relation: %s does't exists.", thisDtoName, oppositeName));
	}

	public PropertyCopyUtil getPropertyCopyUtil() {
		return propertyCopyUtil;
	}
}
