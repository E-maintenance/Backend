package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtimeTerror.autoCare.model.Verification;

public interface VerifiedRepository extends JpaRepository<Verification,String> {
    Verification findVerificationByUserEmail(String email);
    Verification findVerificationByToken(String token);

}
