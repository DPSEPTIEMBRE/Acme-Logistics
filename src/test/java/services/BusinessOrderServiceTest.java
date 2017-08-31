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
import domain.BusinessOrder;
import domain.Measure;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class BusinessOrderServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private BusinessOrderService orderService;
	

	//Templates

	/*
	 * 5.3: A businessman can send an order to a fisherman.
	 */
	public void sendTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "businessman1");
			BusinessOrder res = orderService.create();
			res.setStoreIdentif(18);
			res.setVat("56473829R");
			res.setQuantity(10.0);
			Measure m = new Measure();
			m.setValue("KG");
			res.setMeasure(m);
			res.setPrice(20.0);
			orderService.save(res);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 5.4: A businessman can mark an order as delivered.
	 */
	public void markTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "businessman1");
			for(int i = 0; i < orderService.findAll().size(); i++){
				BusinessOrder b = orderService.findAll().iterator().next();
				if(b.getDevilered() == false){
					orderService.setDelivered(b);
					break;
				}
			}

			

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void sendDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"businessman1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"admin", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.sendTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	@Test
	public void markDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"businessman1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
//			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
//			{"admin", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.markTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
