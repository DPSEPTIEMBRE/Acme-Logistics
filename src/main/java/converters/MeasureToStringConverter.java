
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Measure;

@Component
@Transactional
public class MeasureToStringConverter implements Converter<Measure, String> {

	@Override
	public String convert(Measure ar) {
		String res;
		if (ar == null) {
			res = null;
		} else {
			res = String.valueOf(ar.getValue());
		}
		return res;
	}

}
