package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUsername(String username);
    User findAllByUsername(String username);
    User findUserByEmail(String email);

    User findUserById(long id);


}
