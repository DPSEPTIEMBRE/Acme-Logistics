
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Fisherman;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Integer> {

}
