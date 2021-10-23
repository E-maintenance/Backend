package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtimeTerror.autoCare.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

}
