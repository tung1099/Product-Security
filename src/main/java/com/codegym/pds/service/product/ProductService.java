package com.codegym.pds.service.product;

import com.codegym.pds.model.Product;
import com.codegym.pds.repository.IProductRepository;
import com.codegym.pds.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    public IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {

        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {

        productRepository.deleteById(id);
    }
}
