
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Fisherman;

@Component
@Transactional
public class FishermanToStringConverter implements Converter<Fisherman, String> {

	@Override
	public String convert(Fisherman ar) {
		String res;
		if (ar == null) {
			res = null;
		} else {
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
