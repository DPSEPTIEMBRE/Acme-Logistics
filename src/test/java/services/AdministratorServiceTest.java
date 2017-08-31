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
public class AdministratorServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private AdministratorService adminService;
	

	//Templates

	/*
	 * 7.7: An administrator can display a dashboard of system information.
	 */
	public void dashboardTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "admin");
			adminService.avgMaxMinMessagesSentByActor();
			adminService.avgMaxMinMessagesReceivedByActor();
			adminService.avgMaxMinMarkets();
			adminService.avgMaxMinMarketsPublisheDaily();
			adminService.ratioStaticMarkets();
			adminService.numberFisherman();
			adminService.numberBuyer();
			adminService.numBussinesman();
			adminService.numTransporter();
			adminService.avgMaxMinOrders();
			adminService.avgMaxMinOrdersDaily();
			adminService.avgMaxMinPropertiesFish();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void dashboardDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"admin", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"buyer1", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.dashboardTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
