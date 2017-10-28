package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Unread;

public interface UnreadRepository extends RepositoryBase<Unread> {
    Page<Unread> findByBootUserAndType(BootUser user, String type, Pageable pageable);
	Unread findByBootUserAndTypeAndObid(BootUser user, String type, Long id);
}
