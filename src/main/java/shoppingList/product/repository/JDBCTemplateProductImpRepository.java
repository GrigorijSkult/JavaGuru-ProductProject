package shoppingList.product.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shoppingList.product.domain.ProductEntity;
import shoppingList.product.mappers.ProductEntityRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql_JdbcTemplate")
public class JDBCTemplateProductImpRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCTemplateProductImpRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductEntity addProduct(ProductEntity newProduct) {
        String query = "INSERT INTO products (name, regularPrice, category, discount, description)" +
                "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newProduct.getName());
            preparedStatement.setBigDecimal(2, newProduct.getRegularPrice());
            preparedStatement.setString(3, String.valueOf(newProduct.getCategory()));
            preparedStatement.setBigDecimal(4, newProduct.getDiscount());
            preparedStatement.setString(5, newProduct.getDescription());
            return preparedStatement;
        }, keyHolder);
        return new ProductEntity(
                keyHolder.getKey().longValue(),
                newProduct.getName(),
                newProduct.getRegularPrice(),
                newProduct.getCategory(),
                newProduct.getDiscount(),
                newProduct.getDescription()
        );
    }

    @Override
    public Optional<ProductEntity> findProductByID(Long id) {
        String query = "SELECT * FROM products WHERE id=?";
        try {
            ProductEntity entity = jdbcTemplate.queryForObject(query, new Object[]{id}, new ProductEntityRowMapper());
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void removeProductByID(Long id) {
        String query = "DELETE FROM products WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return preparedStatement;
        });
    }

    @Override
    public List<ProductEntity> listOfAllProducts() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, new ProductEntityRowMapper());
    }

    @Override//no!
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        String query = "UPDATE products SET name = ?, regularPrice = ?, category = ?, discount = ?, description = ? WHERE id = ?";

        updatedProduct.setId(id);
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, updatedProduct.getName());
            preparedStatement.setBigDecimal(2, updatedProduct.getRegularPrice());
            preparedStatement.setString(3, String.valueOf(updatedProduct.getCategory()));
            preparedStatement.setBigDecimal(4, updatedProduct.getDiscount());
            preparedStatement.setString(5, updatedProduct.getDescription());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            return preparedStatement;
        });
        return updatedProduct;
    }

    @Override
    public Optional<ProductEntity> findProductByName(String name) {
        String query = "SELECT * FROM products WHERE name=?";
        try {
            ProductEntity entity = jdbcTemplate.queryForObject(query, new Object[]{name}, new ProductEntityRowMapper());
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(Long id) {
        //query problem (SQL Injection)-is solved?
        String query = "SELECT * FROM products WHERE id=?";
        ProductEntity productEntitie = jdbcTemplate.queryForObject(query, new Object[]{id}, new ProductEntityRowMapper());
        return productEntitie != null;
    }
}
