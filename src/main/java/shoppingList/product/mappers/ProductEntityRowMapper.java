package shoppingList.product.mappers;

import org.springframework.jdbc.core.RowMapper;
import shoppingList.product.domain.ProductCategory;
import shoppingList.product.domain.ProductEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductEntityRowMapper implements RowMapper<ProductEntity> {

    @Override
    public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductEntity entity = new ProductEntity();
        entity.setId(rs.getLong("id"));
        entity.setName(rs.getString("name"));
        entity.setRegularPrice(rs.getBigDecimal("regular_price"));
        entity.setCategory(ProductCategory.valueOf(rs.getString("category")));
        entity.setDiscount(rs.getBigDecimal("discount"));
        entity.setDescription(rs.getString("description"));
        return entity;
    }
}
