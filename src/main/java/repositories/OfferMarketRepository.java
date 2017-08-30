
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.OfferMarket;

@Repository
public interface OfferMarketRepository extends JpaRepository<OfferMarket, Integer> {

}
