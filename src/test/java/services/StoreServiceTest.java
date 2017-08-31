package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Store;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class StoreServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private StoreService storeService;
	

	//Templates

	/*
	 * 5.1: A businessman can register a store.
	 */
	public void registerTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "businessman1");
			Store res = storeService.create();
			res.setName("name");
			res.setPostalAddress("address");
			res.setCity("city");
			res.setVAT("45634865H");
			storeService.save(res);

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
					
			//Test #01: Correct registry. Expected true.
			{"businessman1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null city. Expected false.
			{"buyer1", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.registerTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
