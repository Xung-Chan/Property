package Property.Property.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Property.Property.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
