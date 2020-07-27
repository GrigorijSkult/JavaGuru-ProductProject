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
@Profile("mysql")
public class JDBCProductImpRepository implements ProductRepository {

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
            String query = "INSERT INTO products (name, regularPrice, category, discount, description)" +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newProduct.getName());
            preparedStatement.setBigDecimal(2, newProduct.getRegularPrice());
            preparedStatement.setString(3, String.valueOf(newProduct.getCategory()));
            preparedStatement.setBigDecimal(4, newProduct.getDiscount());
            preparedStatement.setString(5, newProduct.getDescription());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                newProduct.setId(rs.getLong(1));
            }
            return newProduct;
        } catch (Throwable e) {
            System.out.println("Exception while trying to get list of products from DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
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
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setRegularPrice(resultSet.getBigDecimal("regularPrice"));
                product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));
                product.setDiscount(resultSet.getBigDecimal("discount"));
                product.setDescription(resultSet.getString("description"));
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
    public boolean removeProductByID(Long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "DELETE FROM products WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return true;
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
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setRegularPrice(resultSet.getBigDecimal("regularPrice"));
                product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));
                product.setDiscount(resultSet.getBigDecimal("discount"));
                product.setDescription(resultSet.getString("description"));
                products.add(product);
            }
            return products;
        } catch (Throwable e) {
            System.out.println("Exception while trying to get list of products from DB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        Connection connection = null;
        try {
            connection = getConnection();
            updatedProduct.setId(id);
            String query = "UPDATE products SET name = ?, regularPrice = ?, category = ?, discount = ?, description = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, updatedProduct.getName());
            preparedStatement.setBigDecimal(2, updatedProduct.getRegularPrice());
            preparedStatement.setString(3, String.valueOf(updatedProduct.getCategory()));
            preparedStatement.setBigDecimal(4, updatedProduct.getDiscount());
            preparedStatement.setString(5, updatedProduct.getDescription());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            return updatedProduct;
        } catch (Throwable e) {
            System.out.println("Exception while trying to update product");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<ProductEntity> findProductByName(String name) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM products WHERE name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductEntity product = null;
            if (resultSet.next()) {
                product = new ProductEntity();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setRegularPrice(resultSet.getBigDecimal("regularPrice"));
                product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));
                product.setDiscount(resultSet.getBigDecimal("discount"));
                product.setDescription(resultSet.getString("description"));
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
