
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.DailyCatch;
import domain.Fisherman;
import domain.Measure;
import repositories.DailyCatchRepository;
import security.LoginService;

@Service
@Transactional
public class DailyCatchService {

	// Repository
	@Autowired
	private DailyCatchRepository dailyCatchRepository;

	@Autowired
	private FishService fishservice;
	
	@Autowired
	private FishermanService fishermanService;

	@Autowired
	private LoginService loginService;

	// Service

	// Constructor
	public DailyCatchService() {
		super();
	}

	// Methods CRUDS

	public DailyCatch create() {
		DailyCatch dailyCatch = new DailyCatch();

		Fisherman fisherman = (Fisherman) loginService.selectSelf();

		dailyCatch.setFisherman(fisherman);
		dailyCatch.setQuantity(new Double(0.0));
		Measure m = new Measure();
		m.setValue("KG");
		dailyCatch.setMeasure(m);
		dailyCatch.setFish(fishservice.create());

		return dailyCatch;

	}

	public DailyCatch save(DailyCatch daily) {
		Assert.notNull(daily);
		daily = dailyCatchRepository.save(daily);
		
		Fisherman f = daily.getFisherman();
		List<DailyCatch> lc = f.getDailyCatchs();
		lc.add(daily);
		f.setDailyCatchs(lc);
		fishermanService.save(f);
		
		return daily;
	}

	public DailyCatch findOne(Integer id) {
		Assert.notNull(id);
		return dailyCatchRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return dailyCatchRepository.exists(id);
	}

	public void delete(DailyCatch entity) {
		Assert.notNull(entity);
		dailyCatchRepository.delete(entity);
	}

	public List<DailyCatch> findAll() {
		return dailyCatchRepository.findAll();
	}

}
