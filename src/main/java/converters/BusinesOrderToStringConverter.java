
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.BusinessOrder;

@Component
@Transactional
public class BusinesOrderToStringConverter implements Converter<BusinessOrder, String> {

	@Override
	public String convert(BusinessOrder ar) {
		String res;
		if (ar == null) {
			res = null;
		} else {
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
