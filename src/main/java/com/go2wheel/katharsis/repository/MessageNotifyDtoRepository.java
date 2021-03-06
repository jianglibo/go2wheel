package com.go2wheel.katharsis.repository;

import com.go2wheel.katharsis.dto.MessageNotifyDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface MessageNotifyDtoRepository extends ResourceRepositoryV2<MessageNotifyDto, Long> {


	public class MessageNotifyDtoList extends ResourceListBase<MessageNotifyDto, DtoListMeta, DtoListLinks> {

	}

	@Override
	public MessageNotifyDtoList findAll(QuerySpec querySpec);
}

