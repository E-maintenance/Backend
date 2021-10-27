package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runtimeTerror.autoCare.model.Appointment;
import runtimeTerror.autoCare.model.Location;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {


}
