package runtimeTerror.autoCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.WorkShopFeeds;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkShopFeedsRepository extends JpaRepository<WorkShopFeeds, Long> {

    Optional< List<WorkShopFeeds> > findAllByWorkShop_Username(String username);
    Optional < List<WorkShopFeeds>> findAllByWorkShop_Id(Long id);
    Optional< WorkShopFeeds>  findWorkShopFeedsById(Long id);
    Optional< WorkShopFeeds>  deleteWorkShopFeedsById(Long id);


}
