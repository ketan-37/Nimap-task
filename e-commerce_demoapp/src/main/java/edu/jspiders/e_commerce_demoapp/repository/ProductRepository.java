package edu.jspiders.e_commerce_demoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.jspiders.e_commerce_demoapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
