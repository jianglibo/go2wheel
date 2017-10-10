package com.go2wheel.facade.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.Approve;
import com.go2wheel.domain.Approve_;
import com.go2wheel.domain.BootUser;
import com.go2wheel.facade.ApproveFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.katharsis.dto.ApproveDto;
import com.go2wheel.katharsis.exception.UnsupportedRequestException;
import com.go2wheel.repository.ApproveRepository;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class ApproveFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<Approve,ApproveDto, ApproveRepository> implements ApproveFacadeRepository {
	
	public ApproveFacadeRepositoryImpl(ApproveRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize("((#entity.receiver.id == principal.id) and ((#entity.state == T(com.go2wheel.eu.ApproveState).REJECT) or (#entity.state == T(com.go2wheel.eu.ApproveState).APPROVED))) or ((#entity.requester.id == principal.id) and ((#entity.state == T(com.go2wheel.eu.ApproveState).PENDING)))")
	public Approve save(@P("entity") Approve entity, ApproveDto dto) {
		return super.save(entity, dto);
	}
	
	public Specification<Approve> requestEq(BootUser user) {
		return new Specification<Approve>() {
			public Predicate toPredicate(Root<Approve> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				query.distinct(true);
				return builder.equal(root.get(Approve_.requester), user);
			}
		};
	}

	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public Approve newByDto(ApproveDto dto) {
		throw new UnsupportedRequestException("directly creating approve object is not allowed.");
//		Approve entity = new Approve();
//		BeanUtils.copyProperties(dto, entity, "receiver", "requester", "state");
//		entity.setReceiver(userRepo.findOne(dto.getReceiver().getId()));
//		entity.setRequester(userRepo.findOne(dto.getRequester().getId()));
//		return entity;
	}

	@Override
	@PreAuthorize("#entity.id == principal.id")
	public Page<Approve> findSent(@P("entity") BootUser user, PageFacade pf) {
		org.springframework.data.domain.Page<Approve> opage = getRepository().findAllByRequester(user, new SimplePageable(pf)); 
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}

	@Override
	@PreAuthorize("#entity.id == principal.id")
	public Page<Approve> findReceived(@P("entity") BootUser user, PageFacade pf) {
		org.springframework.data.domain.Page<Approve> opage = getRepository().findAllByReceiver(user, new SimplePageable(pf)); 
		return new Page<>(opage.getTotalElements(), opage.getContent()); 
	}
}
