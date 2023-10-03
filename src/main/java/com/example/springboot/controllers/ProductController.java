package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        ProductModel productModel = productService.createProduct(productRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productModel);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProduct(){
        List<ProductModel> productModels = productService.readAllActivated();
        return ResponseEntity.status(HttpStatus.OK).body(productModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable(value = "id") UUID id){
        ProductModel productModel = productService.readProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){
        ProductModel productModel = productService.updateProduct(id,productRecordDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductModel> disableProduct(@PathVariable(value = "id") UUID id){
        ProductModel productModel = productService.disableProductById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productModel);
    }


}
