package shoppingList.mappers;

import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;

public class ProductMapper {

    public ProductDto productToDto(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productEntity.getProductId());
        productDto.setProductName(productEntity.getProductName());
        productDto.setProductRegularPrice(productEntity.getProductRegularPrice());
        productDto.setProductCategory(productEntity.getProductCategory());
        productDto.setProductDiscount(productEntity.getProductDiscount());
        productDto.setProductDescription(productEntity.getProductDescription());
        productDto.setProductActualPrice();
        return productDto;
    }

    public ProductEntity productToEntity(ProductDto productDto){
        ProductEntity productEntity = new ProductEntity(productDto.getProductId(), productDto.getProductName(), productDto.getProductRegularPrice(),
                productDto.getProductCategory(), productDto.getProductDiscount(), productDto.getProductDescription());
        productEntity.setProductActualPrice();
        return productEntity;
    }
}
