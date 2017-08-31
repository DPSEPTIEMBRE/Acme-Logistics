package services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Buyer;
import domain.Fisherman;

import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class FishermanServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private FishermanService fishermanService;
	
	@Autowired
	private BuyerService buyerService;
	

	//Templates

	/*
	 * 1.1: An actor who is not authenticated must be able to register as a fisherman. 
	 */
	public void registerTemplate(final String username, final String password, String actorName, String surname, String city, String id, String email, String phone, String postalAddress, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Fisherman res = fishermanService.create();

			Assert.notNull(username);
			Assert.notNull(password);
			if(phone != null){
				Assert.isTrue(phone.matches("(\\+\\d{2} \\(\\d{1,3}\\) \\d{4,})|(\\+\\d{2} \\d{4,})"));
			}
			Assert.notNull(email);
			Assert.notNull(actorName);
			Assert.notNull(surname);
			Assert.notNull(id);

			res.getUserAccount().setUsername(username);
			res.getUserAccount().setPassword(password);
			res.setActorName(actorName);
			res.setSurname(surname);
			res.setNumberidentif(id);
			res.setCity(city);
			res.setEmail(email);
			res.setPhone(phone);
			res.setPostalAddress(postalAddress);

			fishermanService.save(res);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 2.2: An authenticated actor can edit his or her personal data.
	 */
	public void editTemplate(final String username, String edit, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.notNull(username);
			Assert.notNull(edit);
			Fisherman f = fishermanService.findAll().iterator().next();
			f.setActorName(edit);
			fishermanService.save(f);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 2.6: An authenticated actor can follow other actors.
	 */
	public void followTemplate(final String username, final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.notNull(username);
			Assert.notNull(id);
			Buyer b1 = buyerService.findAll().iterator().next();
			Buyer b2 = buyerService.findOne(id);
			List<Actor> l = b1.getFollower();
			l.add(b2);

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
				
			//Test #01: All parameters correct. Expected true.
			{"fishermanTest", "fishermanTest", "actorName", "surname", "city", "country", "fisherman@mail.com", "+34 (29) 1259", "address", null},
			
			//Test #02: All fields empty. Expected false.
			{null, null, null, null, null, null, null, null, null, IllegalArgumentException.class},
			
			//Test #03: Phone number doesn't match pattern. Expected false.
			{"fishermanTest", "fishermanTest", "actorName", "surname", "city", "country", "fisherman@mail.com", "6824560", "address", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.registerTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (Class<?>) testingData[i][9]);
	}
	
	@Test
	public void editDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct edition. Expected true.
			{"fisherman1", "newname", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, "newname", IllegalArgumentException.class},
				
			//Test #03: Attempt to edit with null data. Expected false.
			{"fisherman1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	@Test
	public void followDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct action. Expected true.
			{"buyer1", 356, null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, 356, IllegalArgumentException.class},
				
			//Test #03: Attempt to search a null entity. Expected false.
			{"buyer1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.followTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
