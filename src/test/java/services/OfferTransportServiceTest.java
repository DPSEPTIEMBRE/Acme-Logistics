package services;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Measure;
import domain.OfferTransport;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class OfferTransportServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private OfferTransportService transportService;
	

	//Templates

	/*
	 * 6.1: A transporter can manage his or her transport offers: list, create and delete them.
	 */
	public void manageTemplate(final String username, String destination, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "transporter1");
			Assert.notNull(destination);
			transportService.findAll();
			OfferTransport res = transportService.create();
			res.setOrigin("origin");
			res.setDestination(destination);
			res.setChargeMax(10.0);
			Measure m = new Measure();
			m.setValue("KG");
			res.setMeasure(m);
			res.setPrice(20.0);
			res.setAvailable(true);
			transportService.save(res);
			transportService.delete(res);

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
					
			//Test #01: Correct operation. Expected true.
			{"transporter1", "destination", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, "destination", IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null destination. Expected false.
			{"transporter1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.manageTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
