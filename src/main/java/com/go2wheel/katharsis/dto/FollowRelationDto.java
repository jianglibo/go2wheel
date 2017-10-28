package com.go2wheel.katharsis.dto;

import javax.validation.constraints.NotNull;

import com.go2wheel.annotation.DtoToEntity;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.FollowRelation;

import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = JsonApiResourceNames.FOLLOW_RELATION)
@DtoToEntity(entityClass=FollowRelation.class)
public class FollowRelationDto extends DtoBase {
	
	@NotNull
	private UserDto follower;
	
	@NotNull
	private UserDto befollowed;

	public UserDto getFollower() {
		return follower;
	}

	public void setFollower(UserDto follower) {
		this.follower = follower;
	}

	public UserDto getBefollowed() {
		return befollowed;
	}

	public void setBefollowed(UserDto befollowed) {
		this.befollowed = befollowed;
	}
}
