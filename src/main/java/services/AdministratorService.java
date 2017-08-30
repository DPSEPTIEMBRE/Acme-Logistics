
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Businessman;
import domain.Buyer;
import domain.CreditCard;
import domain.Fisherman;
import domain.Folder;
import domain.Transporter;
import domain.Wall;
import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	// Repository
	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private LoginService loginService;

	@Autowired
	private BusinessmanService businessmanService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private FishermanService fishermanService;

	@Autowired
	private TransporterService transporterService;

	// Constructor
	public AdministratorService() {
		super();
	}
	
	public List<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	// Methods CRUDS
	public Administrator save(Administrator entity) {
		Assert.notNull(entity);

		return administratorRepository.save(entity);
	}
	
	public Administrator create() {
	    
	    Administrator administrator = new Administrator();
	    administrator.setActorName(new String());
	    administrator.setCity(new String());
	    administrator.setCreditCard(new CreditCard());
	    administrator.setEmail(new String());
	    administrator.setFollowed(new ArrayList<Actor>());
	    administrator.setFollower(new ArrayList<Actor>());
	    administrator.setFolders(new ArrayList<Folder>());
	    administrator.setNumberidentif(new String());
	    administrator.setPhone(new String());
	    administrator.setPostalAddress(new String());
	    administrator.setSurname(new String());
	    administrator.setWall(new Wall());

	    Authority a = new Authority();
	    a.setAuthority(Authority.ADMINISTRATOR);
	    UserAccount account = new UserAccount();
	    account.setAuthorities(Arrays.asList(a));
	    administrator.setUserAccount(account);

	    return administrator;

	  }

	public Administrator findOne(Integer id) {
		Assert.notNull(id);
		return administratorRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return administratorRepository.exists(id);
	}

	// Other Methods
	public Object[] avgMaxMinMessagesSentByActor() {
		return administratorRepository.avgMaxMinMessagesSentByActor();
	}

	public Object[] avgMaxMinMessagesReceivedByActor() {
		return administratorRepository.avgMaxMinMessagesReceivedByActor();
	}

	public Object[] avgMaxMinMarkets() {
		return administratorRepository.avgMaxMinMarkets();
	}

	public Object[] avgMaxMinMarketsPublisheDaily() {
		return administratorRepository.avgMaxMinMarketsDaily();
	}

	public Integer ratioStaticMarkets() {
		return administratorRepository.ratioStaticMarkets();
	}

	public Integer numberFisherman() {
		return administratorRepository.numberFisherman();
	}

	public Integer numberBuyer() {
		return administratorRepository.numberBuyer();
	}

	public Integer numBussinesman() {
		return administratorRepository.numBussinesman();
	}

	public Integer numTransporter() {
		return administratorRepository.numTransporter();
	}

	public Object[] avgMaxMinOrders() {
		return administratorRepository.avgMaxMinOrders();
	}

	public Object[] avgMaxMinOrdersDaily() {
		return administratorRepository.avgMaxMinOrdersDaily();
	}

	public Object[] avgMaxMinPropertiesFish() {
		return administratorRepository.avgMaxMinPropertiesFish();
	}

	public Actor follow(Actor actor) {
		Assert.notNull(actor);

		Actor ac = loginService.selectSelf();

		ac.getFollowed().add(actor);
		actor.getFollower().add(ac);

		if (actor.getUserAccount().getAuthorities().contains(Authority.BUSINESSMAN)) {
			businessmanService.save((Businessman) actor);
		} else if (actor.getUserAccount().getAuthorities().contains(Authority.BUYER)) {
			buyerService.save((Buyer) actor);
		} else if (actor.getUserAccount().getAuthorities().contains(Authority.FISHERMAN)) {
			fishermanService.save((Fisherman) actor);
		} else if (actor.getUserAccount().getAuthorities().contains(Authority.TRANSPORTER)) {
			transporterService.save((Transporter) actor);
		} else if (actor.getUserAccount().getAuthorities().contains(Authority.ADMINISTRATOR)) {
			administratorRepository.save((Administrator) actor);
		}

		return actor;
	}

}
