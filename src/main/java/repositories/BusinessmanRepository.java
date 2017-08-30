
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Businessman;

@Repository
public interface BusinessmanRepository extends JpaRepository<Businessman, Integer> {

}
