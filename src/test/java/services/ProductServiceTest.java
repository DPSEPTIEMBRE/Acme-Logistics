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
import domain.Measure;
import domain.Product;
import domain.Store;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class ProductServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private ProductoService productService;
	
	@Autowired
	private FishService fishService;
	
	@Autowired
	private StoreService storeService;

	//Templates

	/*
	 * 5.2: A businessman can manage his or her products: listing, creating,
	 * 		editing and deleting them.
	 */
	public void manageTemplate(final String username, Double quantity, Double price, String measure, Double quantity2, Double price2, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "businessman1");
			productService.findAll();
			Assert.notNull(quantity);
			Assert.notNull(price);
			Assert.notNull(measure);
			Assert.isTrue(measure == "KG");
			Assert.notNull(quantity2);
			Assert.notNull(price2);
			Assert.isTrue(quantity > 0.0);
			Assert.isTrue(quantity2 > 0.0);
			
			Product res = productService.create();
			res.setQuantity(quantity);
			res.setPrice(price);
			Measure m = new Measure();
			m.setValue(measure);
			res.setMeasure(m);
			Fish f = fishService.findAll().iterator().next();
			res.setFish(f);
			productService.save(res);
			
			res.setQuantity(quantity2);
			res.setPrice(price2);
			productService.save(res);
			
			productService.delete(res);
			
			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 7.1: An administrator can list the items of a store.
	 */
	public void listTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "admin");
			Store s = storeService.findAll().iterator().next();
			s.getProducts();

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
					
			//Test #01: Correct process. Expected true.
			{"businessman1", 10.0, 20.0, "KG", 30.0, 10.0, null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, 10.0, 20.0, "KG", 30.0, 10.0, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"admin", 10.0, 20.0, "KG", 30.0, 10.0, IllegalArgumentException.class},
			
			//Test #04: Attempt to introduce negative values on creation. Expected false.
			{"businessman1", -10.0, -20.0, "KG", 30.0, 10.0, IllegalArgumentException.class},
			
			//Test #05: Attempt to introduce null values on creation. Expected false.
			{"businessman1", null, null, "KG", 30.0, 10.0, IllegalArgumentException.class},
			
			//Test #06: Attempt to introduce negative values on edition. Expected false.
			{"businessman1", 10.0, 20.0, "KG", -30.0, -10.0, IllegalArgumentException.class},
			
			//Test #07: Attempt to introduce negative values on edition. Expected false.
			{"businessman1", 10.0, 20.0, "KG", null, null, IllegalArgumentException.class},
			
			//Test #08: Attempt to introduce unregistered measure. Expected false.
			{"businessman1", 10.0, 20.0, "measure", 30.0, 10.0, IllegalArgumentException.class},
			
			//Test #09: Attempt to introduce null neasure. Expected false.
			{"businessman1", 10.0, 20.0, null, 30.0, 10.0, IllegalArgumentException.class},
			
			//Test #10: All null fields. Expected false.
			{"businessman1", null, null, null, null, null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.manageTemplate((String) testingData[i][0], (Double) testingData[i][1], (Double) testingData[i][2], (String) testingData[i][3], 
					(Double) testingData[i][4], (Double) testingData[i][5], (Class<?>) testingData[i][6]);
	}
	
	@Test
	public void listDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"admin", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"buyer1", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
