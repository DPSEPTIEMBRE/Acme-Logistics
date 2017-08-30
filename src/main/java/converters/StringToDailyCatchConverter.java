
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.DailyCatch;
import repositories.DailyCatchRepository;

@Component
@Transactional
public class StringToDailyCatchConverter implements Converter<String, DailyCatch> {

	@Autowired
	DailyCatchRepository arRepository;


	@Override
	public DailyCatch convert(String text) {
		DailyCatch result;
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
