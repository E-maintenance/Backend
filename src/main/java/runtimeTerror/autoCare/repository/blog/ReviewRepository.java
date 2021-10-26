package runtimeTerror.autoCare.repository.blog;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.blog.Review;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository <Review, Long> {
    Optional<Review> findReviewById(Long id);
}
