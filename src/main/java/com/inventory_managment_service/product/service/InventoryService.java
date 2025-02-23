package com.inventory_managment_service.product.service;

import com.inventory_managment_service.product.model.Product;
import com.inventory_managment_service.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean addProduct(Product product) {
        if(product.getStock() > 0 && product.getPrice()>0 && product.getName()!=null && product.getSku()!=null){
            productRepository.save(product);
            return true;
        }
        else {
            return false;
        }
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
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
    public boolean restoreStock(String sku, int quantity) {
        Product product = productRepository.findBySku(sku);
        if (product != null) {
            if (sku != null && quantity >= 0) {
                System.out.println(product);
                product.setStock(product.getStock() + quantity);
                productRepository.save(product);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int getStock(String sku){
        Product product = productRepository.findBySku(sku);
        return product != null ? product.getStock() : 0;
    }
}
