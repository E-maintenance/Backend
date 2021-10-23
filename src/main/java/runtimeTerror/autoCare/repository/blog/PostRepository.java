package runtimeTerror.autoCare.repository.blog;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import runtimeTerror.autoCare.model.blog.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
    Optional<Post> findPostById(Long id);
}
