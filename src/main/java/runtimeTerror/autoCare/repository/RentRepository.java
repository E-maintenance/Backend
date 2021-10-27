package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtimeTerror.autoCare.model.Rent;

public interface RentRepository extends JpaRepository<Rent,Long> {
    Rent findByName(String name);

}
