package com.go2wheel.facade.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Post;
import com.go2wheel.domain.Unread;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.UnreadFacadeRepository;
import com.go2wheel.katharsis.dto.UnreadDto;
import com.go2wheel.repository.UnreadRepository;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class UnreadFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<Unread,UnreadDto, UnreadRepository> implements UnreadFacadeRepository {
	
	@Autowired
	public UnreadFacadeRepositoryImpl(UnreadRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	public Unread save(Unread entity, UnreadDto dto) {
		return super.save(entity, dto);
	}
	

	@Override
	public List<Unread> findAll() {
		return getRepository().findAll();
	}

	@Override
	public Unread newByDto(UnreadDto dto) {
		return null;
	}

	@Override
	public Page<Unread> findByBootUserAndType(BootUser user, String type, PageFacade pf) {
		org.springframework.data.domain.Page<Unread> opage = getRepository().findByBootUserAndType(user, type, new SimplePageable(pf)); 
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}

	public Unread findByBootUserAndTypeAndObid(BootUser user, String type, Long id) {
		return getRepository().findByBootUserAndTypeAndObid(user,type,id);
	}

	@Override
	public boolean userHasReadThisPost(BootUser user, Long id) {
		Unread ur = getRepository().findByBootUserAndTypeAndObid(user,Post.class.getSimpleName(),id);
		return ur != null;
	}
}
