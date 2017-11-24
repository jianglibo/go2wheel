package com.go2wheel.katharsis.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.go2wheel.annotation.DtoToEntity;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.Medium;

import io.katharsis.resource.annotations.JsonApiRelation;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.LookupIncludeBehavior;
import io.katharsis.resource.annotations.SerializeType;

@JsonApiResource(type = JsonApiResourceNames.MEDIUM)
@DtoToEntity(entityClass=Medium.class)
public class MediumDto extends DtoBase {
	
	private String url;
	private String contentType;
	
	@JsonIgnore
	private String localPath;
	
	private long size;
	
	private String originName;
	
	@JsonApiRelation(lookUp=LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,serialize=SerializeType.LAZY, opposite="media")
	private List<PostDto> posts;
	
	@JsonApiRelation(lookUp=LookupIncludeBehavior.NONE,serialize=SerializeType.LAZY, opposite="media")
	private UserDto creator;
	
	@Override
	public String toString() {
		return String.format("[%s,%s]", getId(), getUrl());
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public String getLocalPath() {
		return localPath;
	}


	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}


	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	public UserDto getCreator() {
		return creator;
	}


	public void setCreator(UserDto creator) {
		this.creator = creator;
	}


	public List<PostDto> getPosts() {
		return posts;
	}


	public void setPosts(List<PostDto> posts) {
		this.posts = posts;
	}

}
