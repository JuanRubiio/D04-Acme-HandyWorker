
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;


	public Category create() {
		Category result;

		result = new Category();

		return result;
	}

	public Category create(final Integer categoryId) {
		Category result;

		result = new Category();
		final Category father = this.findOne(categoryId);
		Assert.notNull(father);
		result.setFather(father);

		return result;
	}

	public Collection<Category> findAll() {
		Collection<Category> result;

		result = this.categoryRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Category findOne(final Integer categoryId) {
		Category result;

		Assert.notNull(categoryId);

		result = this.categoryRepository.findOne(categoryId);

		Assert.notNull(result);

		return result;

	}

	public Category save(final Category category) {
		Category result;

		Assert.notNull(category);

		result = this.categoryRepository.save(category);

		Assert.notNull(result);

		return result;

	}

	public void delete(final Category category) {
		Assert.notNull(category);

		this.categoryRepository.delete(category);

	}

}
