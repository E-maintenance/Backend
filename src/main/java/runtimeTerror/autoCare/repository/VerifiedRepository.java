package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.Verification;

@Repository
public interface VerifiedRepository extends JpaRepository<Verification,String> {
    Verification findVerificationByUserEmail(String email);
    Verification findVerificationByToken(String token);

}
