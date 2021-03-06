package com.go2wheel.util;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Role;
import com.go2wheel.domain.BootUser.Gender;
import com.go2wheel.facade.RoleFacadeRepository;
import com.go2wheel.katharsis.dto.UserDto;

public class BootUserBuilder {
	
	private BootUser bu = new BootUser();
	
	private RoleFacadeRepository roleRepository;
	
	protected BootUserBuilder(RoleFacadeRepository roleRepository, String name, String email, String mobile, String openId) {
		this.roleRepository = roleRepository;
        bu.setName(name);
        bu.setEmail(email);
        bu.setMobile(mobile);
        bu.setOpenId(openId);
        bu.setPassword("");
	}
	
	protected BootUserBuilder(RoleFacadeRepository roleRepository, UserDto userDto) {
		this.roleRepository = roleRepository;
        bu.setName(userDto.getName());
        bu.setEmail(userDto.getEmail());
        bu.setMobile(userDto.getMobile());
        bu.setOpenId(userDto.getOpenId());
        enable()
        .avatar(userDto.getAvatar())
        .displayName(userDto.getDisplayName())
        .gender(userDto.getGender())
        .password(userDto.getPassword() == null ? "" : userDto.getPassword())
        .withWxProperties(userDto.getCity(), userDto.getCountry(), userDto.getLanguage(), userDto.getProvince());
	}
	
	public BootUserBuilder id(Long id) {
		bu.setId(id);
		return this;
	}
	
	public BootUserBuilder enable() {
        bu.setAccountNonExpired(true);
        bu.setAccountNonLocked(true);
        bu.setCreatedAt(Date.from(Instant.now()));
        bu.setCredentialsNonExpired(true);
        bu.setEnabled(true);
		return this;
	}
	
	public BootUserBuilder roles(Role...roles) {
		bu.getRoles().addAll(Arrays.asList(roles));
		return this;
	}
	
	public BootUserBuilder roles(Iterable<Role> ri) {
		for (Role role : ri) {
			bu.getRoles().add(role);
		}
		return this;
	}
	
	public BootUserBuilder roles(String...roles) {
		bu.getRoles().addAll(Stream.of(roles).map(rn -> roleRepository.findByName(rn)).collect(Collectors.toList()));
		return this;
	}
	
	public BootUserBuilder displayName(String displayName) {
		bu.setDisplayName(displayName);
		return this;
	}
	
	public BootUserBuilder avatar(String avatar) {
		bu.setAvatar(avatar);
		return this;
	}
	
	public BootUserBuilder password(String password) {
		bu.setPassword(password);
		return this;
	}
	
	public BootUserBuilder gender(Gender gender) {
		bu.setGender(gender);
		return this;
	}
	
	public BootUserBuilder gender(int gender) {
		bu.setGender(gender == 1 ? Gender.MALE : Gender.FEMALE);
		return this;
	}
	
	public BootUserBuilder withWxProperties(String city, String country, String language, String province) {
		bu.setCity(city);
		bu.setCountry(country);
		bu.setLanguage(language);
		bu.setProvince(province);
		return this;
	}
	
	public BootUser build() {
		return bu;
	}

}
