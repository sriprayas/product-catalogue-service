package com.prayas.product.catalogue.engine.repository;

import com.prayas.product.catalogue.engine.model.database.Product;
import com.prayas.product.catalogue.engine.model.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductCatalogueRepository implements IProductCatalogueRepository {

    private static final String TABLE_NAME = "PRODUCT";
    private static final String QUERY_FIND_PRODUCT = "select * from product where product_type = ?";
    private static final String QUERY_DELETE_PRODUCT = "delete from product where product_id=?";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public ProductCatalogueRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns("product_id");
    }

    // Insert product in the PRODUCT table
    @Override
    public ProductResponse addProduct(Product product){
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("product_type", product.getType());
        stringObjectMap.put("product_name", product.getName());
        stringObjectMap.put("product_rate", product.getRate());
        stringObjectMap.put("product_description", product.getDescription());
        stringObjectMap.put("product_available", true);
        stringObjectMap.put("product_date", product.getCreateDate());
        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(stringObjectMap));

        ProductResponse response = new ProductResponse();
        response.setId(key.intValue());
        response.setRate(product.getRate());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setType(product.getType());
        response.setAvailable(true);
        response.setCreateDate(product.getCreateDate());
        return response;
    }

    // Find product by product type
    @Override
    public List<Product> findProductByType(String productType){
        return  jdbcTemplate.query(QUERY_FIND_PRODUCT, new Object[]{productType}, this::mapRow);
    }

    // Delete product, by product id
    @Override
    public int deleteProduct(int productId){
        return jdbcTemplate.update(QUERY_DELETE_PRODUCT, new Object[] { productId });
    }

    private Product mapRow(ResultSet rs, int i) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setType(rs.getString("product_type"));
        product.setName(rs.getString("product_name"));
        product.setRate(rs.getDouble("product_rate"));
        product.setDescription(rs.getString("product_description"));
        product.setAvailable(rs.getBoolean("product_available"));
        product.setCreateDate(rs.getDate("product_date"));
        return product;
    }
}
