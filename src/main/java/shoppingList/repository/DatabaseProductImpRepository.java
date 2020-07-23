package shoppingList.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shoppingList.domain.ProductEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql")
public class DatabaseProductImpRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseProductImpRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductEntity addProduct(ProductEntity newProduct) {
        String query = "INSERT INTO products (name, regular_price, category, discount, description)" +
                "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newProduct.getProductName());
            preparedStatement.setBigDecimal(2, newProduct.getProductRegularPrice());
            preparedStatement.setString(3, String.valueOf(newProduct.getProductCategory()));
            preparedStatement.setBigDecimal(4, newProduct.getProductDiscount());
            preparedStatement.setString(5, newProduct.getProductDescription());
            return preparedStatement;
        }, keyHolder);
        return new ProductEntity(
                keyHolder.getKey().longValue(),
                newProduct.getProductName(),
                newProduct.getProductRegularPrice(),
                newProduct.getProductCategory(),
                newProduct.getProductDiscount(),
                newProduct.getProductDescription()
        );
    }

    @Override
    public Optional<ProductEntity> removeProductByID(Long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<ProductEntity> listOfAllProducts() {
        return null;
    }

    @Override
    public Optional<ProductEntity> findProductByID(Long id) {
//        String query = "";
        return Optional.empty();
    }

    @Override
    public Optional<ProductEntity> updateProduct(Long id, ProductEntity updatedProduct) {
        return Optional.empty();
    }

    @Override
    public boolean existsByName(ProductEntity productEntity) {
        //query problem (SQL Injection)
        String query = "SELECT * FROM products WHERE name=" + "VALUES (?)";
        jdbcTemplate.query(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //////////////////////
            preparedStatement.setString(1, productEntity.getProductName());
            return preparedStatement;
        });
        List<ProductEntity> productEntities = jdbcTemplate.query(query, new BeanPropertyRowMapper<ProductEntity>());
        return !productEntities.isEmpty();
    }

    @Override
    public boolean existsById(Long Id) {
        return false;
    }
}
