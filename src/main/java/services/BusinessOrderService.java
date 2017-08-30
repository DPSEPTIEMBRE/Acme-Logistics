
package services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.BusinessOrder;
import domain.Businessman;
import domain.Fish;
import domain.Measure;
import domain.OfferMarket;
import domain.OfferTransport;
import domain.Product;
import domain.Store;
import repositories.BusinessOrderRepository;
import security.LoginService;

@Service
@Transactional
public class BusinessOrderService {

	//Repository
	@Autowired
	private BusinessOrderRepository businessOrderRepository;

	//Services
	@Autowired
	private LoginService loginService;
	@Autowired
	private BusinessmanService businessmanService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private OfferTransportService offerTransportService;
	
	//Constructor
	public BusinessOrderService() {
		super();
	}
	
	public void setDelivered(BusinessOrder q) {
		Assert.notNull(q);
		Assert.isTrue(!q.getDevilered());
		
		Store store = businessOrderRepository.storeOf(q.getId());
		Assert.notNull(store);
		
		List<Product> products = new LinkedList<Product>();
		
		for(Fish e : q.getFish()) {
			Product p = productoService.create();
			p.setFish(e);
			p.setMeasure(q.getMeasure());
			p.setPrice(new Double(q.getPrice()));
			p.setQuantity(new Double(q.getQuantity()));
			
			products.add(p);
		}
		
		store.getProducts().addAll(productoService.save(products));
		
		OfferTransport offerTransport = q.getOfferTransport();
		offerTransport.setAvailable(true);
		offerTransportService.save(offerTransport);
		
		q.setDevilered(true);
		q.setOfferTransport(null);
		
		businessOrderRepository.save(q);
		
		storeService.save(store);
		
	}

	//Methods CRUD

	public BusinessOrder create() {
		BusinessOrder businessOrder = new BusinessOrder();
		businessOrder.setStoreIdentif(new Integer(0));
		businessOrder.setVat(new String());
		businessOrder.setQuantity(new Double(0.));
		businessOrder.setMeasure(new Measure());
		businessOrder.setPrice(new Double(0.));
		businessOrder.setDevilered(new Boolean(false));
		businessOrder.setDay(new Date());
		return businessOrder;
	}
	
	public BusinessOrder create(OfferMarket q) {
		Assert.notNull(q);
		
		BusinessOrder bussines = new BusinessOrder();
		bussines.setDay(new Date());
		bussines.setDevilered(false);
		bussines.setFish(new LinkedList<Fish>(q.getFishers()));
		bussines.setMeasure(q.getMeasure());
		bussines.setOfferTransport(null);
		bussines.setPrice(new Double(q.getPrice()));
		bussines.setQuantity(new Double(q.getPrice()));
		bussines.setStoreIdentif(Math.abs(new Random().nextInt()));
		bussines.setVat(generateVAT());
		
		return bussines;
	}
	
	public BusinessOrder createAndSave(BusinessOrder bussines, OfferTransport offerTransport) {
		Assert.notNull(bussines);
		Assert.notNull(offerTransport);
		
		Actor actor = loginService.selectSelf();
		Assert.isTrue(actor instanceof Businessman);
		
		bussines.setOfferTransport(offerTransport);
		
		BusinessOrder saved = businessOrderRepository.save(bussines);
		
		Businessman self = (Businessman) actor;
		self.getOrders().add(saved);
		
		businessmanService.save(self);
		
		return bussines;
	}
	
	private String generateVAT() {
		final String abc = new String("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM7894561230");
		
		StringBuilder str = new StringBuilder();
		Random rand = new Random();
		
		for(int i = 0; i < 12; i++) {
			str.append(abc.charAt(rand.nextInt(abc.length())));
		}
		
		return str.toString();
	}
	
	public BusinessOrder save(BusinessOrder entity) {
		Assert.notNull(entity);
		
		return businessOrderRepository.save(entity);
	}
	
	public BusinessOrder save(BusinessOrder entity, boolean isStatic) {
		Assert.notNull(entity);
		
		Actor actor = loginService.selectSelf();
		Assert.isTrue(actor instanceof Businessman);
		
		OfferTransport offer = entity.getOfferTransport();
		offer.setAvailable(false);
		entity.setOfferTransport(offerTransportService.save(offer));
		
		BusinessOrder saved = businessOrderRepository.save(entity);
		Businessman businessman = (Businessman) actor;
		
		businessman.getOrders().add(saved);
		
		businessmanService.save(businessman);
		
		if(isStatic) {
			chreateSchedule(entity, actor.getId(), this, loginService, businessmanService);
		}
		
		return saved;
	}
	
	private static void chreateSchedule(final BusinessOrder order, final int actor_id, final BusinessOrderService service, final LoginService loginService, final BusinessmanService businessmanService) {
		final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				
				Actor actor = loginService.findById(actor_id);
				
				if(actor == null || !(actor instanceof Businessman)) {
					exec.shutdown();
				}
				
				BusinessOrder aux = order;
				aux.setDay(new Date());
				
				BusinessOrder saved = service.save(aux);
				Businessman businessman = (Businessman) actor;
				
				businessman.getOrders().add(saved);
				
				businessmanService.save(businessman);
				
			}
		}, 30, 30, TimeUnit.DAYS);
	}

	public BusinessOrder findOne(Integer id) {
		Assert.notNull(id);
		return businessOrderRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return businessOrderRepository.exists(id);
	}

	public void delete(BusinessOrder entity) {
		Assert.notNull(entity);
		businessOrderRepository.delete(entity);
	}

	public List<BusinessOrder> findAll() {
		return businessOrderRepository.findAll();
	}
	
	public List<BusinessOrder> selectSelf() {
		Actor actor = loginService.selectSelf();
		
		Assert.isTrue(actor instanceof Businessman);
		
		Businessman businessman = (Businessman) actor;
		
		return businessman.getOrders();
	}

}
