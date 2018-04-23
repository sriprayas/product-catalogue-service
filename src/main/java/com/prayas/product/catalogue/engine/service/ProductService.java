package com.prayas.product.catalogue.engine.service;

import com.prayas.product.catalogue.engine.exception.ProductNotFoundException;
import com.prayas.product.catalogue.engine.mappers.ProductMapper;
import com.prayas.product.catalogue.engine.model.database.Product;
import com.prayas.product.catalogue.engine.model.response.ProductResponse;
import com.prayas.product.catalogue.engine.repository.IProductCatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class to handle
 * 1) insert product
 * 2) retrieve product
 * 3) delete product
 */
@Service
public class ProductService {

    private IProductCatalogueRepository productCatalogueRepository;

    @Autowired
    public ProductService(IProductCatalogueRepository productCatalogueRepository) {
        this.productCatalogueRepository = productCatalogueRepository;
    }

    public ProductResponse addProduct(Product product){
        return productCatalogueRepository.addProduct(product);
    }

    public List<ProductResponse> getProductByType(String productType){
        List<Product> productList = productCatalogueRepository.findProductByType(productType);
        if (productList == null || productList.size() == 0) {
            throw new ProductNotFoundException(MessageFormat.format("No product found with type : {0}", productType));
        }
        return productList.stream()
                .map(ProductMapper::mapResponse)
                .collect(Collectors.toList());
    }

    public void deleteProduct(int productId){
        int rowEffected =  productCatalogueRepository.deleteProduct(productId);
        if(rowEffected == 0){
            throw new ProductNotFoundException(MessageFormat.format("No product found with id : {0}", productId));
        }
    }
}
