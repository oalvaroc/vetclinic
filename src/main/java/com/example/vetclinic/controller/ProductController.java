package com.example.vetclinic.controller;

import com.example.vetclinic.model.Product;
import com.example.vetclinic.model.ProductDAO;
import java.util.List;

public class ProductController {

    private final ProductDAO productDAO = new ProductDAO();

    public Product create() {
        Product prod = new Product();
        productDAO.create(prod);
        return prod;
    }

    public void update(Product prod) {
        productDAO.update(prod);
    }

    public void delete(Product prod) {
        productDAO.delete(prod);
    }

    public List<Product> retrieveAll() {
        return productDAO.retrieveAll();
    }

    public List<Product> retrieveBySimilarName(String search) {
        return productDAO.retrieveBySimilarName(search);
    }

}
