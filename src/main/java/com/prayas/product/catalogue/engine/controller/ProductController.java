package com.prayas.product.catalogue.engine.controller;

import com.prayas.product.catalogue.engine.mappers.ProductMapper;
import com.prayas.product.catalogue.engine.model.request.ProductRequest;
import com.prayas.product.catalogue.engine.model.response.ProductResponse;
import com.prayas.product.catalogue.engine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ProductController caters basically below three request
 * addProduct : Add products.
 * retrieveProducts : Retrieve products based on product type
 * deleteProduct : Delete products, based on provided product id
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest){
        return productService.addProduct(ProductMapper.mapRequest(productRequest));
    }

    @GetMapping("/products/{product_type}")
    public List<ProductResponse> retrieveProducts(@PathVariable(value = "product_type") String productType){
        return productService.getProductByType(productType);
    }

    @DeleteMapping("/products/{product_id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "product_id") int productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
