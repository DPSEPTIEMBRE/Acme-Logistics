
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.OfferMarket;
import repositories.OfferMarketRepository;

@Component
@Transactional
public class StringToOfferMarketConverter implements Converter<String, OfferMarket> {

	@Autowired
	OfferMarketRepository arRepository;


	@Override
	public OfferMarket convert(String text) {
		OfferMarket result;
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
