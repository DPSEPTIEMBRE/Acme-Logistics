
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

}
