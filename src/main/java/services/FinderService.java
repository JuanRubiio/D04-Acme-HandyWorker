
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Finder create() {
		Finder res;
		res = new Finder();
		res.setFixUpTasks(new ArrayList<FixUpTask>());
		final HandyWorker handyWorker = this.handyWorkerService.getPrincipal();
		res.setHandyWorker(handyWorker);
		return res;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> res;
		res = this.finderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Finder findOne(final Integer finderId) {
		Finder res;
		Assert.notNull(finderId);
		res = this.finderRepository.findOne(finderId);
		Assert.notNull(res);
		return res;
	}

	public void delete(final Finder finder) {
		Assert.notNull(finder);
		this.finderRepository.delete(finder);
	}

	public void save(final Finder finder) {
		Assert.notNull(finder);
		this.finderRepository.save(finder);
	}

	public Collection<FixUpTask> findFixUpTasks(final Finder f) {
		final String keyWord = f.getKeyWord();
		Double minPrice = f.getMinPrice();
		Double maxPrice = f.getMaxPrice();
		final Date minDate = f.getMinDate();
		final Date maxDate = f.getMaxDate();

		if (minPrice == null)
			minPrice = 0000.00;
		if (maxPrice == null)
			maxPrice = 9999.99;
		if (minDate == null) {
			final Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -30);
			minDate = c.getTime();
		}
		if (maxDate == null) {
			final Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, 30);
			maxDate = c.getTime();
		}

		if (keyWord == null || keyWord == "")
			return this.finderRepository.findFixUpTasksNoKey(minPrice, maxPrice, minDate, maxDate);
		else
			return this.finderRepository.findFixUpTasks(keyWord, minPrice, maxPrice, minDate, maxDate);
	}
}
