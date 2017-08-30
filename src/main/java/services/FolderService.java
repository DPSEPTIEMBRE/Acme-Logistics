package services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import domain.Businessman;
import domain.Buyer;
import domain.Fisherman;
import domain.Folder;
import domain.MailMessage;
import domain.Transporter;

@Service
@Transactional
public class FolderService {

	@Autowired
	private FolderRepository folderRepository;

	//Services
	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private BusinessmanService businessmanService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private FishermanService fishermanService;

	@Autowired
	private TransporterService transporterService;
	
	@Autowired
	private MailMessageService mailmessageService;

	public Folder create() {
		Folder folder=new Folder();

		folder.setFolderName(new String());
		folder.setMessages(new ArrayList<MailMessage>());

		return folder;
	}

	public List<Folder> createDefaultFolders() {
		List<Folder> folders=new ArrayList<Folder>();

		Folder inbox= create();
		inbox.setFolderName("inbox");
		inbox.setMessages(new LinkedList<MailMessage>());

		Folder outbox= create();
		outbox.setFolderName("outbox");
		outbox.setMessages(new LinkedList<MailMessage>());

		Folder trashbox= create();
		trashbox.setFolderName("trashbox");
		trashbox.setMessages(new LinkedList<MailMessage>());

		Folder spambox= create();
		spambox.setFolderName("spambox");
		spambox.setMessages(new LinkedList<MailMessage>());

		folders.add(inbox);
		folders.add(outbox);
		folders.add(trashbox);
		folders.add(spambox);

		return folders;
	}

	public Actor selectByUsername(String username) {
		return folderRepository.selectByUsername(username);
	}

	public Folder saveCreate(Folder folder) {
		Assert.notNull(folder);

		Folder saved = folderRepository.save(folder);
		UserAccount userAccount = LoginService.getPrincipal();

		Actor actor = folderRepository.selectByUsername(userAccount.getUsername());
		actor.getFolders().add(saved);

		if (actor instanceof Businessman) {
			businessmanService.save((Businessman) actor);
		} else if (actor instanceof Buyer) {
			buyerService.save((Buyer) actor);
		} else if (actor instanceof Fisherman) {
			fishermanService.save((Fisherman) actor);
		} else if (actor instanceof Transporter) {
			transporterService.save((Transporter) actor);
		} else if (actor instanceof Administrator) {
			administratorService.save((Administrator) actor);
		}

		return saved;
	}


	public Folder save(Folder entity) {
		return folderRepository.save(entity);
	}

	public List<Folder> save(Iterable<Folder> entities) {
		return folderRepository.save(entities);
	}

	public void delete(Folder entity) {
		Assert.notNull(entity);

		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = folderRepository.selectByUsername(userAccount.getUsername());
		actor.getFolders().remove(entity);

		if (actor instanceof Businessman) {
			businessmanService.save((Businessman) actor);
		} else if (actor instanceof Buyer) {
			buyerService.save((Buyer) actor);
		} else if (actor instanceof Fisherman) {
			fishermanService.save((Fisherman) actor);
		} else if (actor instanceof Transporter) {
			transporterService.save((Transporter) actor);
		} else if (actor instanceof Administrator) {
			administratorService.save((Administrator) actor);
		}
		mailmessageService.delete(entity.getMessages());
		folderRepository.delete(entity);
	}


	public void delete(Iterable<Folder> entities) {
		folderRepository.delete(entities);
	}

	public void flush() {
		folderRepository.flush();
	}

	public Folder findOne(Integer id) {
		return folderRepository.findOne(id);
	}
	
	

}
