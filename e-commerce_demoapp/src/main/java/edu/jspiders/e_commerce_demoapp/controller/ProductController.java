package edu.jspiders.e_commerce_demoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.jspiders.e_commerce_demoapp.entity.Category;
import edu.jspiders.e_commerce_demoapp.entity.Product;
import edu.jspiders.e_commerce_demoapp.repository.CategoryRepository;
import edu.jspiders.e_commerce_demoapp.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping
	protected Product addProduct(@RequestBody Product product, @RequestParam int categoryId) {
		Product addedProduct = productRepository.save(product);
		Category category = categoryRepository.findById(categoryId).get();
		List<Product> products = category.getProducts();
		products.add(addedProduct);
		category.setProducts(products);
		categoryRepository.save(category);
		return addedProduct;
	}

	@PutMapping
	protected Product updateProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}

	@DeleteMapping("/{id}")
	protected Product deleteProduct(@PathVariable int id) {
		Product product = productRepository.findById(id).get();
		productRepository.delete(product);
		return product;
	}

	@GetMapping("/{id}")
	protected Product findProductById(@PathVariable int id) {
		return productRepository.findById(id).get();
	}

	@GetMapping
	protected Page<Product> findAllProducts(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable);
	}

}
