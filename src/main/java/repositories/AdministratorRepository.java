
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	//The average, maximum and minimum of messages sent by actors.
	@Query("select avg(a.messages.size),max(a.messages.size),min(a.messages.size) from Folder a where a.folderName like '%outbox%'")
	Object[] avgMaxMinMessagesSentByActor();

	//The average, maximum and minimum of messages received by actors.
	@Query("select avg(a.messages.size),max(a.messages.size),min(a.messages.size) from Folder a where a.folderName like '%inbox%'")
	Object[] avgMaxMinMessagesReceivedByActor();

	//The average, maximum and minimum of OfferMarkets per fisherman.
	@Query("select avg(o.size),max(o.size),min(o.size) from Fisherman a join a.offerMarkets o")
	Object[] avgMaxMinMarkets();

	//The average, maximum and minimum of OfferMarkets per fisherman daily.
	@Query("select avg(o.size),max(o.size),min(o.size) from Fisherman a join a.offerMarkets o where o.date=CURDATE()")
	Object[] avgMaxMinMarketsDaily();

	//The ratio of static markets.
	@Query("select count(om)*1./(select count(o)*1. from OfferMarket o) from OfferMarket om where om.isEstatic=true")
	Integer ratioStaticMarkets();

	//Number of actors registered as fishermen
	@Query("select count(a) from Fisherman a")
	Integer numberFisherman();

	//Number of actors registered as buyers.
	@Query("select count(a) from Buyer a")
	Integer numberBuyer();

	//Number of actors registered as businessmen.
	@Query("select count(a) from Businessman a")
	Integer numBussinesman();

	//Number of actors registered as transporters.
	@Query("select count(a) from Transporter a")
	Integer numTransporter();

	//The average, maximum and minimum of orders made per businessman. 
	@Query("select avg(a.orders.size),max(a.orders.size),min(a.orders.size) from Businessman a")
	Object[] avgMaxMinOrders();

	//The average, maximum and minimum of orders per businessman daily.
	@Query("select avg(a.orders.size),max(a.orders.size),min(a.orders.size) from Businessman a join a.orders o where o.day=CURDATE()")
	Object[] avgMaxMinOrdersDaily();

	//The average, maximum and minimum of properties per fish variety.
	@Query("select avg(f.listProperties.size),max(f.listProperties.size),min(f.listProperties.size) from Fish f")
	Object[] avgMaxMinPropertiesFish();

}
