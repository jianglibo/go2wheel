package com.go2wheel.katharsis;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.KatharsisBase;
import com.go2wheel.katharsis.dto.RoleDto;

import io.katharsis.client.KatharsisClient;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;

public class TestClient extends KatharsisBase {

	@Autowired
	private KatharsisClient katharsisClient;
	
	@SuppressWarnings("unused")
	@Test
	public void t() {
		ResourceRepositoryV2<RoleDto, Long> taskRepo = katharsisClient.getRepositoryForType(RoleDto.class);
		try {
			List<RoleDto> tasks = taskRepo.findAll(new QuerySpec(RoleDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<RoleDto> tasks = taskRepo.findAll(new QuerySpec(RoleDto.class));
		assertThat(tasks.size(), equalTo(2));
	}

	@Override
	protected String getResourceName() {
		return null;
	}
}
