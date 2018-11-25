
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	administratorRepository;

	@Autowired
	private MessageBoxService		messageBoxService;


	public Administrator create() {
		Administrator res;

		res = new Administrator();
		final UserAccount userAccount = new UserAccount();
		final List<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);
		this.messageBoxService.addDefaultMessageBoxs(res);

		return res;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> res;
		res = this.administratorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Administrator findOne(final Integer administratorId) {
		Administrator res;
		Assert.notNull(administratorId);
		res = this.administratorRepository.findOne(administratorId);
		Assert.notNull(res);
		return res;
	}

	public void delete(final Administrator administrator) {
		Assert.notNull(administrator);
		this.administratorRepository.delete(administrator);
	}
	public void save(final Administrator administrator) {
		Assert.notNull(administrator);
		this.administratorRepository.save(administrator);
	}

	//Queries del dashboard
	public Collection<Object> query1() {
		return this.administratorRepository.query1();
	}
	public Collection<Object> query2() {
		return this.administratorRepository.query2();
	}
	public Collection<Object> query3() {
		return this.administratorRepository.query3();
	}
	public Collection<Object> query4() {
		return this.administratorRepository.query4();
	}
	public Collection<Object> query5() {
		return this.administratorRepository.query5();
	}
	public Collection<Object> query6() {
		return this.administratorRepository.query6();
	}
	public Collection<Object> query7() {
		return this.administratorRepository.query7();
	}
	public Collection<Object> query8() {
		return this.administratorRepository.query8();
	}
	public Collection<Object> query9() {
		return this.administratorRepository.query9();
	}
	public Collection<Object> query10() {
		return this.administratorRepository.query10();
	}
	public Collection<Object> query11() {
		return this.administratorRepository.query11();
	}
	public Collection<Object> query12() {
		return this.administratorRepository.query12();
	}
	public Collection<Object> query13() {
		return this.administratorRepository.query13();
	}
	public Collection<Object> query14() {
		return this.administratorRepository.query14();
	}
	public Collection<Object> query15() {
		return this.administratorRepository.query15();
	}
}
