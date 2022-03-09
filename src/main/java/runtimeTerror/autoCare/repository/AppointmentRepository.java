package runtimeTerror.autoCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.Appointment;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByWorkShop_Id(Long id);

    Appointment findAppointmentById(Long id);
}
