
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CategoryServiceTest extends AbstractTest {

	@Autowired
	private CategoryService	categoryService;


	@Test
	public void createCategoryTest() {
		final Category category = this.categoryService.create();
		category.setEngName("name");
		category.setEspName("nombre");
		final Category prueba = this.categoryService.save(category);

		final Category cat = this.categoryService.findOne(prueba.getId());
		Assert.notNull(cat);
	}

	@Test
	public void createChildrenCategoryTest() {
		final Collection<Category> cat = this.categoryService.findAll();
		final List<Category> car = (List<Category>) cat;
		final Category category = this.categoryService.create(car.get(2).getId());
		category.setEngName("name");
		category.setEspName("nombre");
		final Category tt = this.categoryService.save(category);

		final Category prueba = this.categoryService.findOne(tt.getId());
		Assert.notNull(prueba);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteCategoryTest() {
		final Category category = this.categoryService.findOne(1390);
		this.categoryService.delete(category);
		this.categoryService.findOne(1390);
	}

}
