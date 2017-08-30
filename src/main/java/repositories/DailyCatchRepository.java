
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.DailyCatch;

@Repository
public interface DailyCatchRepository extends JpaRepository<DailyCatch, Integer> {

}
