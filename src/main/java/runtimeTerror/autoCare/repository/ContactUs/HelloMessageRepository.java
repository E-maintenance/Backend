package runtimeTerror.autoCare.repository.ContactUs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.ContactUs.HelloMessage;

@Repository

public interface HelloMessageRepository extends JpaRepository<HelloMessage, Long> {
}
