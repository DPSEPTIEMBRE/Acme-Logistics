
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Buyer;
import domain.Fish;
import domain.Fisherman;
import domain.Measure;
import domain.OfferMarket;
import domain.Ticker;
import domain.Wall;
import repositories.OfferMarketRepository;
import security.LoginService;

@Service
@Transactional
public class OfferMarketService {

	//Repository
	@Autowired
	private OfferMarketRepository offerMarketRepository;

	//Service
	@Autowired
	private LoginService loginService;
	@Autowired
	private FishermanService fishermanService;
	@Autowired
	private TickerService tickerService;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private WallService wallService;

	//Constructor
	public OfferMarketService() {
		super();
	}
	
	public void favourite(OfferMarket q) {
		Assert.notNull(q);
		
		Actor actor = loginService.selectSelf();
		
		Assert.notNull(actor);
		
		Wall wall = actor.getWall();
		wall.setOffer(new Integer(q.getId()));
		
		wallService.save(wall);
	}
	
	public void buy(OfferMarket offerMarket) {
		Assert.notNull(offerMarket);
		
		Actor actor = loginService.selectSelf();
		
		Assert.isTrue(actor instanceof Buyer);
		
		Buyer buyer = (Buyer) actor;
		
		Fisherman fisherman = offerMarket.getFisherman();
		fisherman.getOfferMarkets().remove(offerMarket);
		
		offerMarketRepository.delete(offerMarket);

		Ticker ticker = tickerService.create(offerMarket);
		
		buyer.getTickers().add(tickerService.save(ticker));
		
		buyerService.save(buyer);
		fishermanService.save(fisherman);
	}

	//Method CRUD
	public OfferMarket create() {
		OfferMarket offerMarket = new OfferMarket();
		offerMarket.setDate(new Date());
		offerMarket.setPlace(new String());
		offerMarket.setDuration(new Integer(0));
		offerMarket.setQuantity(new Double(0d));
		offerMarket.setMeasure(new Measure());
		offerMarket.setPrice(new Double(0d));
		offerMarket.setFishers(new ArrayList<Fish>());
		offerMarket.setIsEstatic(new Boolean(false));

		return offerMarket;

	}
	
	public List<OfferMarket> search(String q) {
		if(q == null || q.trim().isEmpty()) {
			return new LinkedList<OfferMarket>();
		}
		
		List<OfferMarket> res = new LinkedList<OfferMarket>();
		
		final String rq = q.trim().toLowerCase();
		
		loop: for(OfferMarket e : offerMarketRepository.findAll()) {
			if(res.size() >= 12) {
				break;
			}
			
			if(e.getPlace().toLowerCase().contains(rq)) {
				res.add(e);
				continue;
			}
			
			if(e.getMeasure().getValue().toLowerCase().contains(rq)) {
				res.add(e);
				continue;
			}
			
			if(e.getFisherman().getActorName().toLowerCase().contains(rq)) {
				res.add(e);
				continue;
			}
			
			if(e.getFisherman().getSurname().toLowerCase().contains(rq)) {
				res.add(e);
				continue;
			}
			
			if(e.getFisherman().getNumberidentif().toLowerCase().contains(rq)) {
				res.add(e);
				continue;
			}
			
			for(Fish r : e.getFishers()) {
				if(r.getName().toLowerCase().contains(rq)) {
					res.add(e);
					continue loop;
				}
			}
		}
		
		return res;
	}

	public OfferMarket save(OfferMarket entity) {
		Assert.notNull(entity);
		return offerMarketRepository.save(entity);
	}

	public OfferMarket findOne(Integer id) {
		Assert.notNull(id);
		return offerMarketRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return offerMarketRepository.exists(id);
	}

	public void delete(OfferMarket entity) {
		Assert.notNull(entity);
		offerMarketRepository.delete(entity);
	}

	public List<OfferMarket> findAll() {
		return offerMarketRepository.findAll();
	}

	public List<OfferMarket> selectSelfOffers() {
		Actor actor = loginService.selectSelf();
		Assert.notNull(actor);
		Assert.isTrue(actor instanceof Fisherman);
		
		Fisherman fisherman = (Fisherman) actor;
		
		return fisherman.getOfferMarkets();
		
	}
}
