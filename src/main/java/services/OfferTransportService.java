
package services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Measure;
import domain.OfferTransport;
import domain.Transporter;
import repositories.OfferTransportRepository;
import security.LoginService;

@Service
@Transactional
public class OfferTransportService {

	//Repository
	@Autowired
	private OfferTransportRepository offerTransportRepository;

	//Service
	@Autowired
	private LoginService loginService;
	@Autowired
	private TransporterService transporterService;

	//Constructor
	public OfferTransportService() {
		super();
	}

	public OfferTransport create() {
		OfferTransport offerTransport = new OfferTransport();

		offerTransport.setTransporterIdentif(genIdentificator());
		offerTransport.setOrigin(new String());
		offerTransport.setDestination(new String());
		offerTransport.setChargeMax(new Double(0.));
		offerTransport.setMeasure(new Measure());
		offerTransport.setPrice(new Double(0.));
		offerTransport.setPeriodeStart(new Date());
		offerTransport.setPeriodeEnd(new Date());
		offerTransport.setAvailable(true);

		return offerTransport;
	}
	
	private String genIdentificator() {
		final String abc = new String("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
		
		StringBuilder str = new StringBuilder();
		Random rand = new Random();
		
		for(int i = 0; i < 4; i++) {
			str.append(abc.charAt(rand.nextInt(abc.length()))); }
		str.append('-');
		
		for(int i = 0; i < 8; i++) {
			str.append(abc.charAt(rand.nextInt(abc.length()))); }
		str.append('-');
		
		for(int i = 0; i < 4; i++) {
			str.append(abc.charAt(rand.nextInt(abc.length()))); }
		
		return str.toString();
	}
	
	public Boolean isOccupated(Integer id) {
		return offerTransportRepository.isOccupated(id) > 0;
	}
	
	public List<OfferTransport> getAvaliableTransports() {
		return offerTransportRepository.getAvaliableTransports();
	}

	public OfferTransport save(OfferTransport entity) {
		Assert.notNull(entity);
		
		if(offerTransportRepository.exists(entity.getId())) {
			return offerTransportRepository.save(entity);
		} else {
			Actor self = loginService.selectSelf();
			Assert.isTrue(self instanceof Transporter);
			
			OfferTransport saved = offerTransportRepository.save(entity);
			
			Transporter trans = (Transporter) self;
			trans.getOfferTransport().add(saved);
			
			transporterService.save(trans);
			
			return saved;
		}
		
	}
	
	public OfferTransport findOne(Integer id) {
		Assert.notNull(id);
		return offerTransportRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return offerTransportRepository.exists(id);
	}

	public void delete(OfferTransport entity) {
		Assert.notNull(entity);
		Assert.isTrue(entity.getAvailable());
		
		Actor self = loginService.selectSelf();
		Assert.isTrue(self instanceof Transporter);
		
		Transporter trans = (Transporter) self;
		trans.getOfferTransport().remove(entity);
		
		transporterService.save(trans);
		transporterService.flush();
		
		offerTransportRepository.delete(entity);
	}

	public List<OfferTransport> findAll() {
		return offerTransportRepository.findAll();
	}
	
	public List<OfferTransport> selectSelf() {
		Actor actor = loginService.selectSelf();
		
		Assert.isTrue(actor instanceof Transporter);
		
		Transporter transporter = (Transporter) actor;
		
		return transporter.getOfferTransport();
	}

}
