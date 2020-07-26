package shoppingList.mappers;

import org.springframework.jdbc.core.RowMapper;
import shoppingList.domain.ProductCategory;
import shoppingList.domain.ProductEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductEntityRowMapper implements RowMapper<ProductEntity> {

    @Override
    public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductEntity entity = new ProductEntity();
        entity.setId(rs.getLong("ProductId"));
        entity.setRegularPrice(rs.getBigDecimal("ProductRegularPrice"));
        entity.setCategory(ProductCategory.valueOf(rs.getString("ProductCategory")));
        entity.setDiscount(rs.getBigDecimal("ProductDiscount"));
        entity.setDescription(rs.getString("ProductDescription"));
        return null;
    }
}
