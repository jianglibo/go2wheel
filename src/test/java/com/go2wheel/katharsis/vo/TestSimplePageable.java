package com.go2wheel.katharsis.vo;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.go2wheel.facade.jpa.SimplePageable;
import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.util.QuerySpecUtil;

import io.katharsis.queryspec.FilterOperator;
import io.katharsis.queryspec.FilterSpec;
import io.katharsis.queryspec.QuerySpec;

public class TestSimplePageable {

	@Test
	public void tOffset() {
		QuerySpec spec = new QuerySpec(UserDto.class);
		SimplePageable sp = new SimplePageable(spec);
		assertThat(sp.getOffset(), equalTo(0));
		assertThat(sp.getPageSize(), equalTo(0));
		
		spec = new QuerySpec(UserDto.class);
		spec.setLimit(10L);
		spec.setOffset(33L);
		sp = new SimplePageable(spec);
		assertThat(sp.getOffset(), equalTo(33));
		assertThat(sp.getPageSize(), equalTo(10));
		
		spec = new QuerySpec(UserDto.class);
		FilterSpec idf = new FilterSpec(Arrays.asList("id"), FilterOperator.EQ, Arrays.asList(1000L));
		spec.setFilters(Arrays.asList(idf));
		assertTrue(QuerySpecUtil.hasMyId(spec).size() > 0);
	}
}
