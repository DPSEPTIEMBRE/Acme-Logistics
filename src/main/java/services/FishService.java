
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Fish;
import domain.Property;
import repositories.FishRepository;

@Service
@Transactional
public class FishService {

	//Repository
	@Autowired
	private FishRepository fishRepository;

	//Service


	//Constructor
	public FishService() {
		super();
	}

	public Fish create() {
		Fish fish = new Fish();
		fish.setName(new String());
		fish.setListProperties(new ArrayList<Property>());

		return fish;
	}

	public Fish save(Fish fish) {
		Assert.notNull(fish);
		Fish f = null;
		
		if(exists(fish.getId())){
			f = findOne(fish.getId());
			
			f.setName(fish.getName());
			f.setListProperties(fish.getListProperties());
			
			return fishRepository.save(f);
		}else{
			return fishRepository.save(fish);
		}
	}
	

	public void flush() {
		fishRepository.flush();
	}

	public Fish findOne(Integer id) {
		Assert.notNull(id);
		return fishRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return fishRepository.exists(id);
	}

	public void delete(Fish entity) {
		Assert.notNull(entity);
		fishRepository.delete(entity);
	}

	public List<Fish> findAll() {
		return fishRepository.findAll();
	}

}
