
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {

	//Managed repo
	@Autowired
	private CreditCardRepository	creditCardRepository;

	//Supporting services
}
