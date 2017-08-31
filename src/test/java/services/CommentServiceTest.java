package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Store;

import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class CommentServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private StoreService storeService;
	

	//Templates

	/*
	 * 2.3: An authorized actor can publish a comment on an entity that accepts comments.
	 */
	public void publishTemplate(final String username, String text, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.notNull(username);
			Assert.notNull(text);
			Store s = storeService.findAll().iterator().next();
			Comment c = commentService.create();
			c.setBody(text);
			commentService.save(c, s.getId());

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 7.5: An administrator can delete a comment.
	 */
	public void deleteTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "admin");
			Comment c = commentService.findAll().iterator().next();
			commentService.delete(c);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	//Drivers

	@Test
	public void publishDriver() {

		final Object testingData[][] = {
					
			//Test #01: . Expected true.
			{"fisherman1", "text", null},
				
			//Test #02: . Expected false.
			{null, "text", IllegalArgumentException.class},
				
			//Test #03: . Expected false.
			{"fisherman1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.publishTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	@Test
	public void deleteDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"admin", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"buyer1", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
