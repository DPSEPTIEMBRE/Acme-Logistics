
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.OfferTransport;

@Repository
public interface OfferTransportRepository extends JpaRepository<OfferTransport, Integer> {

	@Query("select count(c) from BusinessOrder c where c.offerTransport.id = ?1")
	Long isOccupated(Integer id);
	
	@Query("select c from OfferTransport c where c.available = true")
	List<OfferTransport> getAvaliableTransports();
}
