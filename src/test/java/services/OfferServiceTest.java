package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.OfferMarket;

import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class OfferServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private OfferMarketService offerService;
	

	//Templates

	/*
	 * 2.4: An authenticated actor can search for an offer using a single keyword
	 * 		to appear in its name or city.
	 */
	public void searchTemplate(final String username, String keyword, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.notNull(username);
			Assert.notNull(keyword);
			offerService.search(keyword);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 4.2: A fisherman can publish a market.
	 */
	public void publishTemplate(final String username, Double quantity, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "fisherman1");
			Assert.notNull(quantity);
			OfferMarket res = offerService.create();
			res.setQuantity(quantity);
			offerService.save(res);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void searchDriver() {

		final Object testingData[][] = {
					
			//Test #01: Valid search. Expected true.
			{"buyer1", "search", null},
				
			//Test #02: Attempt to search by anonymous user. Expected false.
			{null, "search", IllegalArgumentException.class},
				
			//Test #03: Attempt to insert a null search term. Expected false.
			{"buyer1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.searchTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	@Test
	public void publishDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct creation. Expected true.
			{"fisherman1", 10.0, null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, 10.0, IllegalArgumentException.class},
				
			//Test #03: Attempt to insert a null quantity. Expected false.
			{"fisherman1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.publishTemplate((String) testingData[i][0], (Double) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
