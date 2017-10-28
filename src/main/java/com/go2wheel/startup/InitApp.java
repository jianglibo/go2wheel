package com.go2wheel.startup;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Role;
import com.go2wheel.domain.Tag;
import com.go2wheel.facade.RoleFacadeRepository;
import com.go2wheel.facade.TagFacadeRepository;
import com.go2wheel.vo.RoleNames;

@Component
public class InitApp {
	
	@Autowired
	private RoleFacadeRepository roleRepo;
	
	@Autowired
	private TagFacadeRepository tagRepo;
	
	@PostConstruct
	public void pc() {
		Set<String> rnIndb = roleRepo.findAll().stream().map(role -> role.getName()).collect(Collectors.toSet());
		RoleNames.allFields().stream().filter(n -> !rnIndb.contains(n)).map(rn -> new Role(rn)).forEach(r -> {
			roleRepo.initSave(r);
		});
		initTags();
	}

	private void initTags() {
		String[] preDefinedTags = new String[] {"SUPERSPORT", "SUPERSPORT TOURING", "SPORT", "TOURING", "CRUISERS", "DUAL PURPOSE", "OFF-ROAD", "MOTOCROSS"};
		Stream.of(preDefinedTags).forEach(ts -> {
			try {
				tagRepo.save(new Tag(ts));
			} catch (Exception e) {
			}
		});
	}
}
