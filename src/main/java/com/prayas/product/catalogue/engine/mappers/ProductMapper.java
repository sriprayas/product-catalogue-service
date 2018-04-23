package com.prayas.product.catalogue.engine.mappers;

import com.prayas.product.catalogue.engine.model.database.Product;
import com.prayas.product.catalogue.engine.model.request.ProductRequest;
import com.prayas.product.catalogue.engine.model.response.ProductResponse;

import java.sql.Date;
import java.time.LocalDate;

public class ProductMapper {

    public static Product mapRequest(ProductRequest request){
        Product product = new Product();
        product.setType(request.getType());
        product.setName(request.getName());
        product.setRate(request.getRate());
        product.setDescription(request.getDescription());
        product.setCreateDate(Date.valueOf(LocalDate.now()));
        return product;
    }

    public static ProductResponse mapResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setDescription(product.getDescription());
        response.setName(product.getName());
        response.setRate(product.getRate());
        return response;
    }
}
