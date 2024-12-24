package Property.Property;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import Property.Property.entity.AccountEntity;
import Property.Property.entity.PermissionEntity;
import Property.Property.entity.RoleEntity;
import Property.Property.repository.AccountRepository;
import Property.Property.repository.PermissionRepository;
import Property.Property.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DataInitalize{
	PermissionRepository permissionRepository;
	RoleRepository roleRepository;
	AccountRepository accountRepository;
	@Bean
	CommandLineRunner inital() {
		return args -> {
			initalPermission();
			initalRole();
			initalAccount();
		};
	}

	private void initalPermission() {
		permissionRepository
				.save(PermissionEntity.builder().name("ACCESS_ADMIN").description("inital permission").build());
	}

	private void initalRole() {	
		roleRepository.save(RoleEntity.builder().name("ADMIN").description("inital role").build());
	}

	private void initalAccount() {
		RoleEntity roleEntity =  roleRepository.findByName("ADMIN").orElse(null);
		accountRepository.save(AccountEntity.builder().username("admin").password("123456").email("abc@123")
				.role(roleEntity).build());
	}
}
