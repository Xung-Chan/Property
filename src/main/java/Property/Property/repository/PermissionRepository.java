package Property.Property.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Property.Property.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>{
	Optional<PermissionEntity> findByName(String name);
}
