
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	//Managed repo
	@Autowired
	private CustomerRepository	customerRepository;


	//Supporting services
	public Customer create() {
		final Customer res;
		res = new Customer();
		//set

		return res;
	}

	public Customer findOne(final Integer customerId) {
		final Customer res;
		Assert.notNull(customerId);
		res = this.customerRepository.findOne(customerId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Customer> findAll() {
		final Collection<Customer> res;
		res = this.customerRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Customer save(final Customer customer) {
		final Customer res;
		Assert.notNull(customer);
		res = this.customerRepository.save(customer);
		Assert.notNull(res);

		return res;
	}

	public void delete(final Customer customer) {
		Assert.notNull(customer);
		this.customerRepository.delete(customer);
	}
}
