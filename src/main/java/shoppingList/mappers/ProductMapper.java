package shoppingList.mappers;

import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;

public class ProductMapper {

    public ProductDto productToDto(ProductEntity productEntity){
        return new ProductDto(productEntity.getProductId(), productEntity.getProductName(), productEntity.getProductRegularPrice(),
                productEntity.getProductCategory(), productEntity.getProductDiscount(), productEntity.getProductDescription());
    }

    public ProductEntity productToEntity(ProductDto productDto){
        return new ProductEntity(productDto.getProductId(), productDto.getProductName(), productDto.getProductRegularPrice(),
                productDto.getProductCategory(), productDto.getProductDiscount(), productDto.getProductDescription());
    }
}
