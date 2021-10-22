package runtimeTerror.autoCare.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.UserM;

@Repository
public interface UserRepostry extends JpaRepository<UserM, Long> {
    UserM findStudentByUsername(String username);
}
