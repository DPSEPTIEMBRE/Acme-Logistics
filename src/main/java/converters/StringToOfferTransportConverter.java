
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.OfferTransport;
import repositories.OfferTransportRepository;

@Component
@Transactional
public class StringToOfferTransportConverter implements Converter<String, OfferTransport> {

	@Autowired
	OfferTransportRepository arRepository;


	@Override
	public OfferTransport convert(String text) {
		OfferTransport result;
		int id;
		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = arRepository.findOne(id);
			}
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
