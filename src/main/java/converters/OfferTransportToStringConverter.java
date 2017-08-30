
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.OfferTransport;

@Component
@Transactional
public class OfferTransportToStringConverter implements Converter<OfferTransport, String> {

	@Override
	public String convert(OfferTransport ar) {
		String res;
		if (ar == null) {
			res = null;
		} else {
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
