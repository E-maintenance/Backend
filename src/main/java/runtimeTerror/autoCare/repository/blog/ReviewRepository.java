package runtimeTerror.autoCare.repository.blog;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.WorkShopFeeds;
import runtimeTerror.autoCare.model.blog.Review;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository <Review, Long> {
    Optional <Review> findReviewById(Long id);
    Optional <List<Review>> findAllByUserId(Long id);
    Optional <List <Review>> findAllByUser_Username(String username);

}
