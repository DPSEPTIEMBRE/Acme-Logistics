
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {

	//Repository
	@Autowired
	private CreditCardRepository creditCardRepository;

	//Service


	//Constructor
	public CreditCardService() {
		super();
	}

	public CreditCard create() {
		CreditCard creditCard = new CreditCard();

		creditCard.setHolder(new String());
		creditCard.setBrand(new String());
		creditCard.setCardNumber(new String());
		creditCard.setExpMonth(new Integer(0));
		creditCard.setExpYear(new Integer(0));
		creditCard.setCvv(new Integer(0));

		return creditCard;

	}

	public CreditCard save(CreditCard entity) {
		Assert.notNull(entity);
		return creditCardRepository.save(entity);
	}

	public CreditCard findOne(Integer id) {
		Assert.notNull(id);
		return creditCardRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return creditCardRepository.exists(id);
	}

	public void delete(CreditCard entity) {
		Assert.notNull(entity);
		creditCardRepository.delete(entity);
	}

	public List<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}

}
