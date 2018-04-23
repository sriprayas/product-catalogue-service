package com.prayas.product.catalogue.engine.repository;

import com.prayas.product.catalogue.engine.model.database.Product;
import com.prayas.product.catalogue.engine.model.response.ProductResponse;

import java.util.List;

public interface IProductCatalogueRepository {

    ProductResponse addProduct(Product product);

    List<Product> findProductByType(String productType);

    int deleteProduct(int productId);

}
