package com.go2wheel.facade;

import java.util.List;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Unread;
import com.go2wheel.katharsis.dto.UnreadDto;


public interface UnreadFacadeRepository extends FacadeRepositoryBase<Unread, UnreadDto> {
    Page<Unread> findByBootUserAndType(BootUser user, String type, PageFacade pf);
    
    List<Unread> findAll();
    boolean userHasReadThisPost(BootUser user, Long id);
}
