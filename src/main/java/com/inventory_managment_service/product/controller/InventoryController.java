package com.inventory_managment_service.product.controller;

import com.inventory_managment_service.product.dto.ProductDTO;
import com.inventory_managment_service.product.model.Product;
import com.inventory_managment_service.product.service.InventoryService;
import com.inventory_managment_service.product.util.ProductDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ProductDtoMapper productDtoMapper;

    public InventoryController(InventoryService inventoryService, ProductDtoMapper productDtoMapper){
        this.inventoryService = inventoryService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        try{
             List<Product> product = inventoryService.getAllProduct();
             return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDto) {
        try {
            Product product = productDtoMapper.productDtotoentity(productDto);
            boolean productStatus = inventoryService.addProduct(product);
            if(productStatus){
                return ResponseEntity.ok("Product added successfully");
            }else {
                return ResponseEntity.badRequest().body("Product not added");
            }
        } catch (Exception e) {
            // General exception handling
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveStock(@RequestParam String sku, @RequestParam int quantity){
        try{
            if(inventoryService.reserveStock(sku,quantity)){
                return ResponseEntity.ok("Stock reserved successfully");
            }else{
                return ResponseEntity.badRequest().body("Stock not reserved");
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }

    }


    @PostMapping("/restore")
    public ResponseEntity<String> restoreStock(@RequestParam String sku,@RequestParam int quantity){
        try{

           if( inventoryService.restoreStock(sku,quantity)) {
               return ResponseEntity.ok("Stock restored successfully");
           }else {
               return ResponseEntity.badRequest().body("Stock not restored");
           }
        }catch (Exception e){
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }

    }

    @GetMapping("/stock")
    public ResponseEntity<Integer> getStock(@RequestParam String sku){
        try{
            return ResponseEntity.ok(inventoryService.getStock(sku));
        }catch (Exception e){
            return ResponseEntity.status(500).body(-1);
        }

    }
}
