
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Measure;
import domain.Product;
import repositories.ProductRepository;

@Service
@Transactional
public class ProductoService {

	//Repository
	@Autowired
	private ProductRepository productRepository;

	//Service


	//Constructor
	public ProductoService() {
		super();
	}

	public Product create() {
		Product product = new Product();
		product.setQuantity(new Double(0.));
		product.setPrice(new Double(0.));
		product.setMeasure(new Measure());
		product.setComments(new ArrayList<Comment>());
		return product;
	}
	public Product save(Product entity) {
		Assert.notNull(entity);
		return productRepository.save(entity);
	}
	
	public List<Product> save(Iterable<Product> entities) {
		Assert.notNull(entities);
		return productRepository.save(entities);
	}

	public Product findOne(Integer id) {
		Assert.notNull(id);
		return productRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return productRepository.exists(id);
	}

	public void delete(Product entity) {
		Assert.notNull(entity);
		productRepository.delete(entity);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

}
