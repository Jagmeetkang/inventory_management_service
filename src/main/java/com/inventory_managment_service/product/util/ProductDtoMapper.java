package com.inventory_managment_service.product.util;

import com.inventory_managment_service.product.dto.ProductDTO;
import com.inventory_managment_service.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    public Product productDtotoentity(ProductDTO dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        return product;
    }
}
