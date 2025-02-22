package com.inventory_managment_service.product.service;

import com.inventory_managment_service.product.model.Product;
import com.inventory_managment_service.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        System.out.println(product.getStock());
        productRepository.save(product);
        return product;
    }

   @Transactional
   public boolean reserveStock(String sku, int quantity){
        Product product = productRepository.findBySku(sku);
        if(product!=null && product.getStock()>=quantity){
            product.setStock(product.getStock()-quantity);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Transactional
    public void restoreStock(String sku, int quantity){
        Product product = productRepository.findBySku(sku);
        if(product != null){
            product.setStock(product.getStock() + quantity);
            productRepository.save(product);
        }
    }

    public int getStock(String sku){
        Product product = productRepository.findBySku(sku);
        return product != null ? product.getStock() : 0;
    }
}
