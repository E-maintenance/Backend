package runtimeTerror.autoCare.repository.blog;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.blog.Review;
import runtimeTerror.autoCare.model.blog.WorkshopReview;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkshopRevRepository extends JpaRepository <WorkshopReview, Long> {
    Optional <WorkshopReview> findWorkshopReviewById(Long id);
    Optional<List<WorkshopReview>> findAllByUserId(Long id);
    Optional<List<WorkshopReview>> findAllByUser_Username(String username);
}
