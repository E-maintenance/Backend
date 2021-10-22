package runtimeTerror.autoCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.WorkShop;
import runtimeTerror.autoCare.model.WorkShopFeeds;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkShopRepository extends JpaRepository<WorkShop, Long> {
    WorkShop findWorkShopByUsername(String username);

    WorkShop findWorkShopById(Long id);

}
