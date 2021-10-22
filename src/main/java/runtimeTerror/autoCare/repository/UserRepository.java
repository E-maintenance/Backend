package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtimeTerror.autoCare.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUsername(String username);
    User findAllByUsername(String username);
    User findUserByEmail(String email);

}
