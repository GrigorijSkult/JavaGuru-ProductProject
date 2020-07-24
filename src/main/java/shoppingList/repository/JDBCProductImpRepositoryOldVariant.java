package shoppingList.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import shoppingList.domain.ProductCategory;
import shoppingList.domain.ProductEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql_old")
public class JDBCProductImpRepositoryOldVariant implements ProductRepository {

    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${database.driverName}")
    private String dataBaseDriverName;

    @Override
    public ProductEntity addProduct(ProductEntity newProduct) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "INSERT INTO products (name, regular_price, category, discount, description)" +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newProduct.getProductName());
            preparedStatement.setBigDecimal(2, newProduct.getProductRegularPrice());
            preparedStatement.setString(3, String.valueOf(newProduct.getProductCategory()));
            preparedStatement.setBigDecimal(4, newProduct.getProductDiscount());
            preparedStatement.setString(5, newProduct.getProductDescription());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                newProduct.setProductId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while trying to get list of products from DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return newProduct;
    }

    @Override
    public Optional<ProductEntity> findProductByID(Long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM products WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductEntity product = null;
            if (resultSet.next()) {
                product = new ProductEntity();
                product.setProductId(resultSet.getLong("id"));
                product.setProductName(resultSet.getString("name"));
                product.setProductRegularPrice(resultSet.getBigDecimal("regular_price"));
                product.setProductCategory(ProductCategory.valueOf(resultSet.getString("category")));
                product.setProductDiscount(resultSet.getBigDecimal("discount"));
                product.setProductDescription(resultSet.getString("description"));
            }
            return Optional.ofNullable(product);
        } catch (Throwable e) {
            System.out.println("Exception while trying to find product in by ID DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<ProductEntity> removeProductByID(Long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "DELETE FROM products WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            Optional<ProductEntity> productByID = findProductByID(id);
            preparedStatement.execute();
            return productByID;
        } catch (Throwable e) {
            System.out.println("Exception while trying to delete product by ID in DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<ProductEntity> listOfAllProducts() {
        List<ProductEntity> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM products";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductEntity product = new ProductEntity();
                product.setProductId(resultSet.getLong("id"));
                product.setProductName(resultSet.getString("name"));
                product.setProductRegularPrice(resultSet.getBigDecimal("regular_price"));
                product.setProductCategory(ProductCategory.valueOf(resultSet.getString("category")));
                product.setProductDiscount(resultSet.getBigDecimal("discount"));
                product.setProductDescription(resultSet.getString("description"));
                products.add(product);
            }
        } catch (Throwable e) {
            System.out.println("Exception while trying to get list of products from DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return products;
    }

    @Override
    public Optional<ProductEntity> updateProduct(Long id, ProductEntity updatedProduct) {

        return Optional.empty();
    }

    @Override
    public boolean existsByName(ProductEntity productEntity) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM products WHERE name=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productEntity.getProductName());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Throwable e) {
            System.out.println("Exception while trying to delete product by ID in DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean existsById(Long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM products WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (Throwable e) {
            System.out.println("Exception while trying to delete product by ID in DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    protected Connection getConnection() {
        try {
            return DriverManager.getConnection(databaseUrl, username, password);
        } catch (SQLException e) {
            System.out.println("Exception while getting connection to DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception while closing connection to DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
