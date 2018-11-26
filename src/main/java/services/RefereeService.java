
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.UserAccount;
import domain.Referee;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository	refereeRepository;

	@Autowired
	private MessageBoxService	messageBoxService;


	public Referee create() {
		Referee res;
		res = new Referee();
		final UserAccount userAccount = new UserAccount();
		final List<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.REFEREE);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);
		this.messageBoxService.addDefaultMessageBoxs(res);
		return res;
	}

	public Collection<Referee> findAll() {
		Collection<Referee> res;
		res = this.refereeRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Referee findOne(final Integer refereeId) {
		Referee res;
		Assert.notNull(refereeId);
		res = this.refereeRepository.findOne(refereeId);
		Assert.notNull(res);
		return res;
	}

	public void delete(final Referee referee) {
		Assert.notNull(referee);
		this.refereeRepository.delete(referee);
	}

	public void save(final Referee referee) {
		Assert.notNull(referee);
		this.refereeRepository.save(referee);
	}
}
