package edu.jspiders.e_commerce_demoapp.controller;

import java.util.ArrayList;

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

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping
	protected Category addCategory(@RequestBody Category category) {
		category.setProducts(new ArrayList<Product>());
		return categoryRepository.save(category);
	}

	@PutMapping
	protected Category updateCategory(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	@DeleteMapping("/{id}")
	protected Category deleteCategory(@PathVariable int id) {
		Category category = categoryRepository.findById(id).get();
		categoryRepository.delete(category);
		return category;
	}

	@GetMapping("/{id}")
	protected Category findCategoryById(@PathVariable int id) {
		return categoryRepository.findById(id).get();
	}

	@GetMapping
	protected Page<Category> findAllCategories(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return categoryRepository.findAll(pageable);
	}

}
