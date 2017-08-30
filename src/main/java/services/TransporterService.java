
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
import domain.Folder;
import domain.OfferTransport;
import domain.Transporter;
import domain.Wall;
import repositories.TransporterRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class TransporterService {

	//Repository
	@Autowired
	private TransporterRepository transporterRepository;

	//Service
	@Autowired
	private FolderService folderService;
	@Autowired
	WallService wallService;

	//Constructor
	public TransporterService() {
		super();
	}

	//Methods CRUD

	public Transporter create() {
		Transporter transporter = new Transporter();
		transporter.setActorName(new String());
		transporter.setSurname(new String());
		transporter.setNumberidentif(new String());
		transporter.setCity(new String());
		transporter.setPhone(new String());
		transporter.setPostalAddress(new String());
		transporter.setEmail(new String());
		
		Authority a = new Authority();
		a.setAuthority(Authority.TRANSPORTER);
		
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		
		transporter.setUserAccount(account);
		transporter.setFolders(new ArrayList<Folder>());
		transporter.setFollower(new ArrayList<Actor>());
		transporter.setFollowed(new ArrayList<Actor>());
		transporter.setOfferTransport(new ArrayList<OfferTransport>());
		return transporter;

	}
	public Transporter save(Transporter entity) {
		Assert.notNull(entity);
		Assert.notNull(entity.getOfferTransport());
		
		if(!transporterRepository.exists(entity.getId())) {
			Md5PasswordEncoder enc = new Md5PasswordEncoder();
			entity.getUserAccount().setPassword(enc.encodePassword(entity.getUserAccount().getPassword(), null));
			
			entity.setFolders(folderService.save(folderService.createDefaultFolders()));
			
			Wall wall = wallService.create();
			wall.setName(String.format("%s%d", entity.getActorName(), entity.hashCode()));
			
			entity.setWall(wallService.save(wall));
		}
		
		return transporterRepository.save(entity);
	}

	public void flush() {
		transporterRepository.flush();
	}

	public Transporter findOne(Integer id) {
		Assert.notNull(id);
		return transporterRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return transporterRepository.exists(id);
	}

	public void delete(Transporter entity) {
		Assert.notNull(entity);
		transporterRepository.delete(entity);
	}

	public List<Transporter> findAll() {
		return transporterRepository.findAll();
	}

}
