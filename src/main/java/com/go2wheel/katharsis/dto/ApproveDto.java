package com.go2wheel.katharsis.dto;

import javax.validation.constraints.NotNull;

import com.go2wheel.annotation.DtoToEntity;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.Approve;
import com.go2wheel.eu.ApproveState;

import io.katharsis.resource.annotations.JsonApiRelation;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.LookupIncludeBehavior;
import io.katharsis.resource.annotations.SerializeType;

@JsonApiResource(type = JsonApiResourceNames.APPROVE)
@DtoToEntity(entityClass=Approve.class)
public class ApproveDto extends DtoBase {

	@JsonApiRelation(lookUp=LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,serialize=SerializeType.LAZY, opposite="sentApproves")
	@NotNull
	private UserDto requester;
	
	@JsonApiRelation(lookUp=LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,serialize=SerializeType.LAZY, opposite="receivedApproves")
	@NotNull
	private UserDto receiver;
	
	@NotNull
	private String targetType;
	
	@NotNull
	private Long targetId;
	
	private ApproveState state;
	
	private String descriptionTpl;
	
	public ApproveDto() {}

	public ApproveDto(Long id) {
		super(id);
	}

	public UserDto getRequester() {
		return requester;
	}

	public void setRequester(UserDto requester) {
		this.requester = requester;
	}

	public UserDto getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDto receiver) {
		this.receiver = receiver;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getDescriptionTpl() {
		return descriptionTpl;
	}

	public void setDescriptionTpl(String descriptionTpl) {
		this.descriptionTpl = descriptionTpl;
	}

	public ApproveState getState() {
		return state;
	}

	public void setState(ApproveState state) {
		this.state = state;
	}
	
	
}
