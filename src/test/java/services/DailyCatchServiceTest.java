package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import security.LoginService;
import utilities.AbstractTest;
import domain.DailyCatch;
import domain.Fish;
import domain.Fisherman;
import domain.Measure;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class DailyCatchServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private DailyCatchService catchService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private FishService fishService; 
	

	//Templates

	/*
	 * 4.1: A fisherman can register his or her daily catch.
	 */
	public void registerTemplate(final String username, Double quantity, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "fisherman1");
			Assert.notNull(quantity);
			DailyCatch res = catchService.create();
			Fisherman fisherman = (Fisherman) loginService.selectSelf();
			res.setFisherman(fisherman);
			res.setQuantity(quantity);
			Measure m = new Measure();
			m.setValue("KG");
			res.setMeasure(m);
			Fish f = fishService.findAll().iterator().next();
			res.setFish(f);
			catchService.save(res);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	//Drivers

	@Test
	public void registerDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct creation. Expected true.
			{"fisherman1", 20.0, null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, 20.0, IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null quantity. Expected false.
			{"fisherman1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.registerTemplate((String) testingData[i][0], (Double) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
