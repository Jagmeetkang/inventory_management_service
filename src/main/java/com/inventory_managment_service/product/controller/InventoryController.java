package com.inventory_managment_service.product.controller;

import com.inventory_managment_service.product.dto.ProductDTO;
import com.inventory_managment_service.product.model.Product;
import com.inventory_managment_service.product.service.InventoryService;
import com.inventory_managment_service.product.util.ProductDtoMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ProductDtoMapper productDtoMapper;

    public InventoryController(InventoryService inventoryService, ProductDtoMapper productDtoMapper){
        this.inventoryService = inventoryService;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDto){
        Product product = productDtoMapper.productDtotoentity(productDto);
        ResponseEntity<Product> productResponseEntity = null;
        try{
            Product addedProduct =  inventoryService.addProduct(product);
            productResponseEntity = ResponseEntity
                    .status(HttpStatusCode.valueOf(201))
                    .header("Origin", "product-inventory")
                    .header("message", "Product added successfully")
                    .body(addedProduct);

        }catch (Exception e){
            Product product1 = new Product();
            productResponseEntity = ResponseEntity
                    .status(HttpStatusCode.valueOf(400))
                    .header("Origin", "product-inventory")
                    .header("message", e.getMessage())
                    .body(product1);
        }
        return productResponseEntity;
    }

    @PostMapping("/reserve")
    public ResponseEntity<Boolean> reserveStock(@RequestParam String sku, @RequestParam int quantity){
        ResponseEntity<Boolean> productReserveResponseEntity = null;
        try{
            Boolean reserveProduct = inventoryService.reserveStock(sku,quantity);
            if(reserveProduct){
                productReserveResponseEntity = ResponseEntity
                        .status(HttpStatusCode.valueOf(201))
                        .header("Origin", "product-inventory")
                        .header("message", "Stock reserve Successfully")
                        .body(reserveProduct);

            }else {
                productReserveResponseEntity = ResponseEntity
                        .status(HttpStatusCode.valueOf(201))
                        .header("Origin", "product-inventory")
                        .header("message", "Not enough stock")
                        .body(reserveProduct);
            }

        }catch (Exception e){
            Boolean reserveProduct1 = null;
            productReserveResponseEntity = ResponseEntity
                    .status(HttpStatusCode.valueOf(400))
                    .header("Origin", "product-inventory")
                    .header("message", e.getMessage())
                    .body(reserveProduct1);
        }
        return productReserveResponseEntity;

    }

    @PostMapping("/restore")
    public String restoreStock(@RequestParam String sku,@RequestParam int quantity){
        inventoryService.restoreStock(sku,quantity);
        return "Stock restore Successfully";
    }

    @GetMapping("/stock")
    public int getStock(@RequestParam String sku){
        return inventoryService.getStock(sku);
    }
}
