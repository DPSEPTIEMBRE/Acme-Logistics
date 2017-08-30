
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Actor;
import services.AdministratorService;
import services.BusinessmanService;
import services.BuyerService;
import services.FishermanService;
import services.TransporterService;

@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actor> {

	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private FishermanService		fishermanService;
	@Autowired
	private BuyerService			buyerService;
	@Autowired
	private BusinessmanService		businessmanService;
	@Autowired
	private TransporterService		transporterService;


	@Override
	public Actor convert(String actor) {
		int id;

		try {
			id = Integer.valueOf(actor.trim());
		} catch (NumberFormatException e) {
			return null;
		}

		if (administratorService.exists(id)) {
			return administratorService.findOne(id);
		}

		if (fishermanService.exists(id)) {
			return fishermanService.findOne(id);
		}
		if (buyerService.exists(id)) {
			return buyerService.findOne(id);
		}
		if (businessmanService.exists(id)) {
			return businessmanService.findOne(id);
		}

		return transporterService.findOne(id);

	}

}
