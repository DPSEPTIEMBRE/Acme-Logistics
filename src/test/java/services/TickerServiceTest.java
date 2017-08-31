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

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class TickerServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private TickerService tickerService;
	

	//Templates

	/*
	 * 3.1: A buyer can see his or her own tickers.
	 */
	public void listTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "buyer1");
			tickerService.findAll();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void listDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"buyer1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"admin", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
