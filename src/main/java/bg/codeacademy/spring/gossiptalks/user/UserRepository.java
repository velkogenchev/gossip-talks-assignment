package bg.codeacademy.spring.gossiptalks.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Page<User> findByNameContainsIgnoreCaseOrUsernameContainsIgnoreCase(String substring1, String substring2, Pageable pageable);

  Optional<User> getByEmail(String email);

  Optional<User> getByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM User u JOIN u.following f WHERE u.id = :userId AND f.id = :followingId")
  boolean isFollowing(long userId, long followingId);
}
