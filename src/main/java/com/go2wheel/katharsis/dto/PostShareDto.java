package com.go2wheel.katharsis.dto;

import javax.validation.constraints.NotNull;

import com.go2wheel.annotation.DtoToEntity;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.PostShare;

import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = JsonApiResourceNames.POST_SHARE)
@DtoToEntity(entityClass=PostShare.class)
public class PostShareDto extends DtoBase {
	
	@NotNull
	private UserDto bootUser;
	
	@NotNull
	private PostDto post;

	public UserDto getBootUser() {
		return bootUser;
	}

	public void setBootUser(UserDto bootUser) {
		this.bootUser = bootUser;
	}

	public PostDto getPost() {
		return post;
	}

	public void setPost(PostDto post) {
		this.post = post;
	}


}
