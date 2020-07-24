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
        entity.setProductId(rs.getLong("ProductId"));
        entity.setProductRegularPrice(rs.getBigDecimal("ProductRegularPrice"));
        entity.setProductCategory(ProductCategory.valueOf(rs.getString("ProductCategory")));
        entity.setProductDiscount(rs.getBigDecimal("ProductDiscount"));
        entity.setProductDescription(rs.getString("ProductDescription"));
        return null;
    }
}
