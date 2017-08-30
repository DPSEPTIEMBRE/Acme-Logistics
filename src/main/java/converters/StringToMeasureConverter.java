
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Measure;

@Component
@Transactional
public class StringToMeasureConverter implements Converter<String, Measure> {

	@Override
	public Measure convert(String text) {
		Measure result = new Measure();
		
		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				result.setValue(text);
			}
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
