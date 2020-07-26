package shoppingList.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shoppingList.domain.ProductEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql_JdbcTemplate")
public class JDBCProductImpRepository implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;

    public JDBCProductImpRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override//not ok
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
        ProductEntity productEntities = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(ProductEntity.class));
        return Optional.ofNullable(productEntities);
    }

    @Override//no-id
    public Optional<ProductEntity> removeProductByID(Long id) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.execute((ConnectionCallback<Optional<ProductEntity>>) connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            Optional<ProductEntity> productByID = findProductByID(id);
            preparedStatement.execute();
            return (productByID);
        });
        return Optional.empty();
    }

    @Override
    public List<ProductEntity> listOfAllProducts() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ProductEntity.class));
    }

    @Override//no
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        return null;
    }

    @Override//no
    public boolean existsByName(ProductEntity productEntity) {
        //query problem (SQL Injection)
        String query = "SELECT * FROM products WHERE name=?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productEntity.getName());
            return preparedStatement;
        });
        List<ProductEntity> productEntities = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ProductEntity.class));
        return !productEntities.isEmpty();
//        return false;
    }

    @Override//no
    public boolean existsById(Long id) {
/*        String query = "SELECT * FROM products WHERE id=?";
        jdbcTemplate.execute(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        });*/
        return false;
    }
}
