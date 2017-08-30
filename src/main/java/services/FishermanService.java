
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
import domain.DailyCatch;
import domain.Fisherman;
import domain.Folder;
import domain.OfferMarket;
import domain.Wall;
import repositories.FishermanRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class FishermanService {

	//Repository
	@Autowired
	private FishermanRepository fishermanRepository;

	//Service
	@Autowired
	FolderService folderService;
	@Autowired
	WallService wallService;

	//Constructor

	public FishermanService() {
		super();
	}

	//Method CRUD
	public Fisherman create() {
		Fisherman fisherman = new Fisherman();
		fisherman.setActorName(new String());
		fisherman.setSurname(new String());
		fisherman.setNumberidentif(new String());
		fisherman.setCity(new String());
		fisherman.setPhone(new String());
		fisherman.setPostalAddress(new String());
		fisherman.setEmail(new String());
		fisherman.setWall(null);
		
		Authority a = new Authority();
		a.setAuthority(Authority.FISHERMAN);
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		
		fisherman.setUserAccount(account);
		fisherman.setFolders(new ArrayList<Folder>());
		fisherman.setFollower(new ArrayList<Actor>());
		fisherman.setFollowed(new ArrayList<Actor>());
		fisherman.setOfferMarkets(new ArrayList<OfferMarket>());
		fisherman.setDailyCatchs(new ArrayList<DailyCatch>());
		fisherman.setWall(wallService.create());

		return fisherman;

	}

	public Fisherman save(Fisherman entity) {
		Assert.notNull(entity);
		Assert.notNull(entity.getDailyCatchs());
		Assert.notNull(entity.getOfferMarkets());
		
		if(!fishermanRepository.exists(entity.getId())) {
			Md5PasswordEncoder enc = new Md5PasswordEncoder();
			entity.getUserAccount().setPassword(enc.encodePassword(entity.getUserAccount().getPassword(), null));
			
			entity.setFolders(folderService.save(folderService.createDefaultFolders()));
			
			Wall wall = wallService.create();
			wall.setName(String.format("%s%d", entity.getActorName(), entity.hashCode()));
			
			entity.setWall(wallService.save(wall));
		}
		
		return fishermanRepository.save(entity);
	}

	public Fisherman findOne(Integer id) {
		Assert.notNull(id);
		return fishermanRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return fishermanRepository.exists(id);
	}

	public void delete(Fisherman entity) {
		Assert.notNull(entity);
		fishermanRepository.delete(entity);
	}

	public void deleteAll() {
		fishermanRepository.deleteAll();
	}

	public List<Fisherman> findAll() {
		return fishermanRepository.findAll();
	}

}
