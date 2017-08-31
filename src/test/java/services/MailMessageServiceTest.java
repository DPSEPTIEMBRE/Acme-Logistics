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
public class MailMessageServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private MailMessageService mailService;
	

	//Templates

	/*
	 * 2.5: An actor can exchange messages with other actors.
	 */
	public void sendTemplate(final String username, String subject, String body, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.notNull(username);
			Assert.notNull(subject);
			Assert.notNull(body);
			mailService.send(subject, body, "LOW", "admin");

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
					
			//Test #01: Correct message. Expected true.
			{"buyer1", "subject", "body", null},
				
			//Test #02: Attempt to send by anonymous user. Expected false.
			{null, "subject", "body", IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null fields. Expected false.
			{"buyer1", null, null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.sendTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}
}
