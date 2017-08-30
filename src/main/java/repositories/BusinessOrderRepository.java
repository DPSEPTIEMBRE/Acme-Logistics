
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.BusinessOrder;
import domain.Store;

@Repository
public interface BusinessOrderRepository extends JpaRepository<BusinessOrder, Integer> {

	@Query("select c.store from Businessman c join c.orders u where u.id = ?1")
	public Store storeOf(Integer id);
}
