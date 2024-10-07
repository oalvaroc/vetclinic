package com.example.vetclinic.controller;

import com.example.vetclinic.model.Product;
import com.example.vetclinic.model.ProductDAO;
import com.example.vetclinic.view.ProductTableModel;
import java.util.List;

public class Controller {

    private final ProductDAO productDAO = new ProductDAO();

    public Product createProduct() {
        Product prod = new Product();
        productDAO.create(prod);
        return prod;
    }

    public void updateProduct(Product prod) {
        productDAO.update(prod);
    }

    public void deleteProduct(Product prod) {
        productDAO.delete(prod);
    }

    public List<Product> retrieveAllProducts() {
        return productDAO.retrieveAll();
    }

    public List<Product> retrieveProductsByName(String search) {
        return productDAO.retrieveBySimilarName(search);
    }

}
