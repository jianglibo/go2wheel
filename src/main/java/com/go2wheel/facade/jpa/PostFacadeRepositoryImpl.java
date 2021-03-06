package com.go2wheel.facade.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Medium;
import com.go2wheel.domain.MessageNotify;
import com.go2wheel.domain.Post;
import com.go2wheel.domain.PostShare;
import com.go2wheel.domain.Post_;
import com.go2wheel.domain.Unread;
import com.go2wheel.facade.BootGroupFacadeRepository;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.MediumFacadeRepository;
import com.go2wheel.facade.MessageNotifyFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.PostFacadeRepository;
import com.go2wheel.facade.PostShareFacadeRepository;
import com.go2wheel.facade.UnreadFacadeRepository;
import com.go2wheel.katharsis.dto.MediumDto;
import com.go2wheel.katharsis.dto.PostDto;
import com.go2wheel.repository.PostRepository;
import com.go2wheel.util.SecurityUtil;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class PostFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<Post,PostDto, PostRepository> implements PostFacadeRepository {
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private MediumFacadeRepository mediumRepo;
	
	@Autowired
	private PostShareFacadeRepository psRepo;
	
	@Autowired
	private BootGroupFacadeRepository groupRepo;
	
	@Autowired
	private GroupUserRelationFacadeRepositoryImpl guRepo;
	
	@Autowired
	private UnreadFacadeRepository unreadRepo;
	
	@Autowired
	private MessageNotifyFacadeRepository mnRepo;
	
	public PostFacadeRepositoryImpl(PostRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public Post save(Post entity, PostDto dto) {
		return super.save(entity, dto);
	}

	@Override
	@PreAuthorize(PreAuthorizeExpression.ENTITY_ID_EQUAL_OR_HAS_ADMINISTRATOR_ROLE)
	public Page<Post> findMine(@P("entity") BootUser user, PageFacade pf) {
		org.springframework.data.domain.Page<Post> opage = getRepository().findAll(creatorEq(user), new SimplePageable(pf)); 
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}

	public Specification<Post> creatorEq(BootUser user) {
		return new Specification<Post>() {
			public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				query.distinct(true);
				return builder.equal(root.get(Post_.creator), user);
			}
		};
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public Post findOne(Long id, boolean internalCall) {
		Post post =  super.findOne(id, true);
		// creator allow access.
		if (post.getCreator().getId().equals(SecurityUtil.getLoginUserId())) {
			return post;
		}
		// user other than creator
		BootUser currentUser = userRepo.findOne(SecurityUtil.getLoginUserId(), true);
		boolean hasRead = unreadRepo.userHasReadThisPost(currentUser, post.getId());
		
		if (!hasRead) {
			Unread unread = new Unread();
			unread.setObid(post.getId());
			unread.setType(Post.class.getSimpleName());
			unread.setBootUser(currentUser);
			unreadRepo.save(unread, null);
		}

		if (post.isToAll()) {
			return post;
		}
		PostShare ps = psRepo.findByPostAndBootUser(post, currentUser);
		if (ps == null) {
			throw new AccessDeniedException("you cannot access a post people without share to you.");
		}
		return post;
	}


	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public Post newByDto(PostDto dto) {
		Post entity = new Post();
		getPropertyCopyUtil().copyPropertyWhenCreate(entity, dto);
		entity.setCreator(userRepo.findOne(SecurityUtil.getLoginUserId(), true));
		entity = getRepository().save(entity);
		List<Medium> media = new ArrayList<>();
		if (dto.getMedia() != null) {
			for(MediumDto mdto : dto.getMedia()) {
				Medium m = mediumRepo.findOne(mdto.getId(), true);
				media.add(mediumRepo.save(m, null));
			}
		}
		entity.setMedia(media);
		
		final Post fe = entity;

		if (dto.getSharedUsers() != null) {
			dto.getSharedUsers().stream().map(udto -> userRepo.findOne(udto.getId(), true)).forEach(u -> {
				saveSharePost(fe, u);
			});
		}
		
		if (dto.getSharedGroups() != null) {
			dto.getSharedGroups().stream()
				.map(gdto -> groupRepo.findOne(gdto.getId(), true))
				.flatMap(g -> guRepo.findByBootGroup(g, new PageFacade(10000L)).getContent().stream())
				.map(gu -> gu.getBootUser()).forEach(bu -> {
					saveSharePost(fe, bu);
				});
		}
		
		return entity;
	}
	
	public void saveSharePost(final Post post, BootUser user) {
//		try {
			PostShare ps = new PostShare(post, user);
			psRepo.save(ps, null);
			Unread ur = new Unread();
			ur.setBootUser(user);
			ur.setObid(post.getId());
			ur.setType(Post.class.getName());
			unreadRepo.save(ur, null);
			
			MessageNotify mn = mnRepo.findByBootUserAndNtype(user, Post.class.getSimpleName());
			if (mn == null) {
				mn = new MessageNotify();
				mn.setNumber(0);
				mn.setBootUser(user);
				mn.setNtype(Post.class.getSimpleName());
				mn = mnRepo.save(mn, null);
			}
			
			mn.setNumber(mn.getNumber() + 1);
			mnRepo.save(mn, null);
//		} catch (Exception e) {
//			
//		}
	}

	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED + " and #toAll")
	@Override
	public Page<Post> findAllByToAll(@P("toAll")boolean toAll, PageFacade pageFacade) {
		org.springframework.data.domain.Page<Post> opage = getRepository().findAllByToAll(toAll, new SimplePageable(pageFacade));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}
}
