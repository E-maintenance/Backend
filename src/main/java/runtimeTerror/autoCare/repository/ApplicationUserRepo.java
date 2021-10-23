package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtimeTerror.autoCare.model.ApplicationUser;

public interface ApplicationUserRepo extends JpaRepository<ApplicationUser,Integer> {
    ApplicationUser findByUsername (String username);
}
