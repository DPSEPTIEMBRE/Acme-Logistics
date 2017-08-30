
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
import domain.BusinessOrder;
import domain.Businessman;
import domain.Folder;
import domain.Wall;
import repositories.BusinessmanRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class BusinessmanService {

	//Repository
	@Autowired
	private BusinessmanRepository businessManRepository;
	//Services
	@Autowired
	private FolderService folderService;
	@Autowired
	WallService wallService;

	//Constructor
	public BusinessmanService() {
		super();
	}

	//Methods CRUDS

	public Businessman create() {
		Businessman businessman = new Businessman();
		businessman.setActorName(new String());
		businessman.setSurname(new String());
		businessman.setNumberidentif(new String());
		businessman.setCity(new String());
		businessman.setPhone(new String());
		businessman.setPostalAddress(new String());
		businessman.setEmail(new String());
		
		Authority a = new Authority();
		a.setAuthority(Authority.BUSINESSMAN);
		
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		
		businessman.setUserAccount(account);
		businessman.setFolders(new ArrayList<Folder>());
		businessman.setFollower(new ArrayList<Actor>());
		businessman.setFollowed(new ArrayList<Actor>());
		businessman.setOrders(new ArrayList<BusinessOrder>());
		businessman.setStore(null);
		

		return businessman;
	}

	public Businessman save(Businessman entity) {
		Assert.notNull(entity);
		Assert.notNull(entity.getOrders());
		
		if(!businessManRepository.exists(entity.getId())) {
			Md5PasswordEncoder enc = new Md5PasswordEncoder();
			entity.getUserAccount().setPassword(enc.encodePassword(entity.getUserAccount().getPassword(), null));
			
			entity.setFolders(folderService.save(folderService.createDefaultFolders()));
			
			Wall wall = wallService.create();
			wall.setName(String.format("%s%d", entity.getActorName(), entity.hashCode()));
			
			entity.setWall(wallService.save(wall));
		}
		
		return businessManRepository.save(entity);
	}

	public Businessman findOne(Integer id) {
		Assert.notNull(id);
		return businessManRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return businessManRepository.exists(id);
	}

	public void delete(Businessman entity) {
		Assert.notNull(entity);
		businessManRepository.delete(entity);
	}

	public List<Businessman> findAll() {
		return businessManRepository.findAll();
	}

}
