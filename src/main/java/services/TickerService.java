
package services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Buyer;
import domain.Fish;
import domain.Measure;
import domain.OfferMarket;
import domain.Ticker;
import repositories.TickerRepository;
import security.LoginService;

@Service
@Transactional
public class TickerService {

	//Repository
	@Autowired
	private TickerRepository tickerRepository;

	//Service
	@Autowired
	LoginService loginService;

	//Constructor
	public TickerService() {
		super();
	}

	//Method CRUDS

	public Ticker create() {
		Ticker ticker = new Ticker();
		ticker.setQuantity(new Double(0.));
		ticker.setPrice(new Double(0.));
		ticker.setMeasure(new Measure());

		return ticker;
	}
	
	public Ticker create(OfferMarket offer) {
		Ticker ticker = new Ticker();
		ticker.setQuantity(offer.getQuantity());
		ticker.setPrice(offer.getPrice());
		ticker.setMeasure(offer.getMeasure());
		ticker.setFish(new LinkedList<Fish>(offer.getFishers()));

		return ticker;
	}
	
	public List<Ticker> selectSelf() {
		Actor actor = loginService.selectSelf();
		
		Assert.notNull(actor);
		Assert.isTrue(actor instanceof Buyer);
		
		Buyer buyer = (Buyer) actor;
		
		return buyer.getTickers();
	}

	public Ticker save(Ticker entity) {
		Assert.notNull(entity);
		return tickerRepository.save(entity);
	}

	public Ticker findOne(Integer id) {
		Assert.notNull(id);
		return tickerRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return tickerRepository.exists(id);
	}

	public void delete(Ticker entity) {
		Assert.notNull(entity);
		tickerRepository.delete(entity);
	}

	public List<Ticker> findAll() {
		return tickerRepository.findAll();
	}

}
