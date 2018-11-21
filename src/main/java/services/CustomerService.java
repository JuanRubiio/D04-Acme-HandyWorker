
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	//Managed repo
	@Autowired
	private CustomerRepository	customerRepository;

	//Supporting services

}
