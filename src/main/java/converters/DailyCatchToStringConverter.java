
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.DailyCatch;

@Component
@Transactional
public class DailyCatchToStringConverter implements Converter<DailyCatch, String> {

	@Override
	public String convert(DailyCatch ar) {
		String res;
		if (ar == null) {
			res = null;
		} else {
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
