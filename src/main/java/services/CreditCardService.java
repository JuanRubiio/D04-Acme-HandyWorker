
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	//Managed repo
	@Autowired
	private CreditCardRepository	creditCardRepository;


	//Supporting services
	public CreditCard create() {
		final CreditCard res;
		res = new CreditCard();
		//Set

		return res;
	}

	public CreditCard findOne(final Integer creditCardId) {
		final CreditCard res;
		Assert.notNull(creditCardId);
		res = this.creditCardRepository.findOne(creditCardId);
		Assert.notNull(res);

		return res;
	}

	public Collection<CreditCard> findAll() {
		final Collection<CreditCard> res;
		res = this.creditCardRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public CreditCard save(final CreditCard creditCard) {
		final CreditCard res;
		Assert.notNull(creditCard);
		res = this.save(creditCard);
		Assert.notNull(res);

		return res;
	}

	public void delete(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		this.creditCardRepository.delete(creditCard);
	}
}
