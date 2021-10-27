package runtimeTerror.autoCare.repository.ContactUs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.ContactUs.ContactUsModel;

@Repository
public interface ContactUsRep extends JpaRepository<ContactUsModel,Long> {
}
