package runtimeTerror.autoCare.repository.blog;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.blog.WorkshopReview;

@Repository
public interface WorkshopRevRepository extends JpaRepository <WorkshopReview, Long> {
}
