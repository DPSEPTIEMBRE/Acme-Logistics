
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Fish;
import domain.Property;
import repositories.PropertyRepository;

@Service
@Transactional
public class PropertyService {

	//Repository
	@Autowired
	private PropertyRepository propertyRepository;

	//Service
	@Autowired
	private FishService fishService;

	//Constructor
	public PropertyService() {
		super();
	}

	//Method CRUDS

	public Property create() {
		Property property = new Property();
		property.setName(new String());
		property.setDescription(new String());

		return property;
	}

	public Property save(Property entity, Integer fish) {
		Assert.notNull(entity);
		Assert.notNull(fish);
		Assert.isTrue(fishService.exists(fish));
		
		Property saved = propertyRepository.save(entity);
		
		Fish f = fishService.findOne(fish);
		f.getListProperties().add(saved);
		
		fishService.save(f);
		
		return saved;
	}

	public Property findOne(Integer id) {
		Assert.notNull(id);
		return propertyRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return propertyRepository.exists(id);
	}

	public void delete(Property entity, Fish fish) {
		Assert.notNull(entity);
		Assert.notNull(fish);
		
		fish.getListProperties().remove(entity);
		fishService.save(fish);
		fishService.flush();
		
		propertyRepository.delete(entity);
		propertyRepository.flush();
	}

	public List<Property> findAll() {
		return propertyRepository.findAll();
	}

}
