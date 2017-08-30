
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Buyer;
import domain.Folder;
import domain.Ticker;
import domain.Wall;
import repositories.BuyerRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class BuyerService {

	//Repository
	@Autowired
	private BuyerRepository buyerRepository;

	//Service
	@Autowired
	private FolderService folderService;
	@Autowired
	WallService wallService;

	//Constructor
	public BuyerService() {
		super();
	}
	
	public void delete(Buyer entity) {
		buyerRepository.delete(entity);
	}

	//Method CRUD

	public Buyer create() {

		Buyer buyer = new Buyer();
		buyer.setActorName(new String());
		buyer.setSurname(new String());
		buyer.setNumberidentif(new String());
		buyer.setCity(new String());
		buyer.setPhone(new String());
		buyer.setPostalAddress(new String());
		buyer.setEmail(new String());
		
		Authority a = new Authority();
		a.setAuthority(Authority.BUYER);
		
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		
		buyer.setUserAccount(account);
		buyer.setFolders(new ArrayList<Folder>());
		buyer.setFollower(new ArrayList<Actor>());
		buyer.setFollowed(new ArrayList<Actor>());
		buyer.setTickers(new ArrayList<Ticker>());

		return buyer;

	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return buyerRepository.exists(id);
	}

	public List<Buyer> findAll() {
		return buyerRepository.findAll();
	}

	public Buyer save(Buyer entity) {
		Assert.notNull(entity);
		Assert.notNull(entity.getTickers());
		
		if(!buyerRepository.exists(entity.getId())) {
			Md5PasswordEncoder enc = new Md5PasswordEncoder();
			entity.getUserAccount().setPassword(enc.encodePassword(entity.getUserAccount().getPassword(), null));
			
			entity.setFolders(folderService.save(folderService.createDefaultFolders()));
			
			Wall wall = wallService.create();
			wall.setName(String.format("%s%d", entity.getActorName(), entity.hashCode()));
			
			entity.setWall(wallService.save(wall));
		}
		
		return buyerRepository.save(entity);
	}

	public Buyer findOne(Integer id) {
		Assert.notNull(id);
		return buyerRepository.findOne(id);
	}

}
