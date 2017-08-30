
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.OfferMarket;

@Component
@Transactional
public class OfferMarketToStringConverter implements Converter<OfferMarket, String> {

	@Override
	public String convert(OfferMarket ar) {
		String res;
		if (ar == null) {
			res = null;
		} else {
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
