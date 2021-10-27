package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtimeTerror.autoCare.model.Appointment;
import runtimeTerror.autoCare.model.Location;
import runtimeTerror.autoCare.model.Rent;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findAllById(Long id);
    List<Appointment> findAllByWorkShop_Id(Long id);

}
