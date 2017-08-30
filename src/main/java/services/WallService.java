
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Wall;
import repositories.WallRepository;
import security.LoginService;

@Service
@Transactional
public class WallService {

	//Repository
	@Autowired
	private WallRepository wallRepository;

	//Service
	@Autowired
	private LoginService loginService;

	//Constructor
	public WallService() {
		super();
	}

	//Method CRUD

	public Wall create() {
		Wall wall = new Wall();
		wall.setName(new String());
		wall.setOffer(0);

		return wall;

	}
	
	public Wall selectSelfWall() {
		Actor actor = loginService.selectSelf();
		Assert.notNull(actor);
		
		return actor.getWall();
	}

	public Wall save(Wall entity) {
		Assert.notNull(entity);
		
		return wallRepository.save(entity);
	}

	public Wall findOne(Integer id) {
		Assert.notNull(id);
		
		return wallRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		
		return wallRepository.exists(id);
	}

	public void delete(Wall entity) {
		Assert.notNull(entity);
		
		wallRepository.delete(entity);
	}

	public List<Wall> findAll() {
		return wallRepository.findAll();
	}

}
