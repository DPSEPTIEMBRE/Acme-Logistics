
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Wall;

@Repository
public interface WallRepository extends JpaRepository<Wall, Integer> {

}
