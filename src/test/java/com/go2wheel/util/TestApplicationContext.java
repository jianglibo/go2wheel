package com.go2wheel.util;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;

import com.go2wheel.Tbase;
import com.go2wheel.config.DtoEntityMapper;
import com.go2wheel.domain.Role;
import com.go2wheel.repository.SimpleJpaRepositoryBase;
import com.go2wheel.repository.RepositoryBase;
import com.go2wheel.repository.RoleRepository;

public class TestApplicationContext extends Tbase {
	
	@Autowired
	private Repositories repositories;
	
	@SuppressWarnings("unused")
	@Autowired
	private DtoEntityMapper dtoEntityMapper;
	
	
	@Test
	public void tByname() {
		RoleRepository rr = (RoleRepository) repositories.getRepositoryFor(Role.class);
		long n = rr.findAll().size();
		assertThat(n, greaterThan(0L));
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void t() {
		Map<String, RepositoryBase> rbs = context.getBeansOfType(RepositoryBase.class);
		Map<String, SimpleJpaRepositoryBase> dsrs = context.getBeansOfType(SimpleJpaRepositoryBase.class);
		assertThat(rbs.size() / 2, equalTo(dsrs.size()));
		
		Set<String> keys = dsrs.keySet();
		
		for(String bn : keys) {
			assertThat(rbs.get(bn), equalTo(dsrs.get(bn)));
		}
	}
}
