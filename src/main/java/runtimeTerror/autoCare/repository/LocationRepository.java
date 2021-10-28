package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtimeTerror.autoCare.model.Location;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
