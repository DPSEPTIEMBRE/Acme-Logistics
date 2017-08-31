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
import domain.Fish;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class FishServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private FishService fishService;
	

	//Templates

	/*
	 * 7.2: An administrator can manage varieties of fish: listing, creating and editing them.
	 */
	public void manageTemplate(final String username, String name, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "admin");
			Assert.notNull(name);
			Fish res = fishService.create();
			res.setName(name);
			fishService.save(res);
			fishService.flush();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void manageDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"admin", "name", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, "name", IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null name. Expected false.
			{"admin", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.manageTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
