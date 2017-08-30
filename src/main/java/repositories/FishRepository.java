
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {

}
