package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.Approve;
import com.go2wheel.domain.BootUser;

public interface ApproveRepository extends RepositoryBase<Approve> {

	Page<Approve> findAllByRequester(BootUser user, Pageable simplePageable);

	Page<Approve> findAllByReceiver(BootUser user, Pageable simplePageable);

}
