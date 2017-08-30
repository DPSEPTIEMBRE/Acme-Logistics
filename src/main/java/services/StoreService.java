
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Businessman;
import domain.Comment;
import domain.Product;
import domain.Store;
import repositories.StoreRepository;
import security.LoginService;

@Service
@Transactional
public class StoreService {

	//Repository
	@Autowired
	private StoreRepository storeRepository;

	//Service
	@Autowired
	private LoginService loginService;
	@Autowired
	private BusinessmanService businessmanService;


	//Constructor
	public StoreService() {
		super();
	}

	//Method CRUD
	public Store create() {
		Store store = new Store();
		store.setName(new String());
		store.setPostalAddress(new String());
		store.setCity(new String());
		store.setVAT(new String());
		store.setComments(new ArrayList<Comment>());
		store.setProducts(new ArrayList<Product>());

		return store;
	}
	
	public Store selectSelf() {
		Actor actor = loginService.selectSelf();
		
		Assert.isTrue(actor instanceof Businessman);
		
		Businessman businessman = (Businessman) actor;
		
		return businessman.getStore();
	}

	public Store save(Store entity) {
		Assert.notNull(entity);
		
		if(storeRepository.exists(entity.getId())) {
			
			return storeRepository.save(entity);
		} else {
			Actor actor = loginService.selectSelf();
			
			Assert.isTrue(actor instanceof Businessman);
			
			Businessman businessman = (Businessman) actor;
			
			Store saved = storeRepository.save(entity);
			businessman.setStore(saved);
			
			businessmanService.save(businessman);
			
			return saved;
		}
	}

	public Store findOne(Integer id) {
		Assert.notNull(id);
		return storeRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return storeRepository.exists(id);
	}

	public void delete(Store entity) {
		Assert.notNull(entity);
		storeRepository.delete(entity);
	}

	public List<Store> findAll() {
		return storeRepository.findAll();
	}

}
