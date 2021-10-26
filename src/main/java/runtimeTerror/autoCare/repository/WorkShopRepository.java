package runtimeTerror.autoCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.WorkShop;

@Repository
public interface WorkShopRepository extends JpaRepository<WorkShop, Long> {
    WorkShop findWorkShopByUsername(String username);


}
