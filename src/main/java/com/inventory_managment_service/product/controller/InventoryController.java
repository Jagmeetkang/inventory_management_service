package com.inventory_managment_service.product.controller;

import com.inventory_managment_service.product.model.Product;
import com.inventory_managment_service.product.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product){
        inventoryService.addProduct(product);
        return "Product added successfully";
    }

    @PostMapping("/reserve")
    public String reserveStock(@RequestParam String sku, @RequestParam int quantity){
        if(inventoryService.reserveStock(sku,quantity)){
            return "Stock reserve Successfully";
        }else{
            return "Not enough stock";
        }
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
