package com.example.springboot.services;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductModel createProduct(ProductRecordDto productRecordDto){
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto,productModel);

        return productRepository.save(productModel);
    }

    public List<ProductModel> readAllProduct(){
        List<ProductModel> productModels = productRepository.findAll();
        return productModels;
    }

    public List<ProductModel> readAllActivated(){
        List<ProductModel> productModelList = productRepository.findAllByIsActivatedTrue();
        return productModelList;
    }

    public ProductModel readProductById(UUID id){
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);

        if(optionalProductModel.isEmpty()){
            throw new EntityNotFoundException();
        }

        ProductModel productModel = optionalProductModel.get();
        return productModel;
    }

    @Transactional
    public ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto){
        ProductModel productModel = readProductById(id);


        productModel.setName(productRecordDto.name());
        productModel.setValue(productRecordDto.value());


        return productModel;

    }

    @Transactional
    public ProductModel disableProductById(UUID id){
        Optional<ProductModel> productModelOptional = productRepository.findById(id);

        if(productModelOptional.isEmpty()){
            throw new EntityNotFoundException();
        }

        productModelOptional.get().setActivated(false);
        return productModelOptional.get();
    }
}
