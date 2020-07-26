package shoppingList.mappers;

import org.springframework.stereotype.Component;
import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.services.businessLogic.ProductActualPriceCalculation;

@Component
public class ProductMapper {

    public ProductDto productToDto(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productEntity.getId());
        productDto.setProductName(productEntity.getName());
        productDto.setProductRegularPrice(productEntity.getRegularPrice());
        productDto.setProductCategory(productEntity.getCategory());
        productDto.setProductDiscount(productEntity.getDiscount());
        productDto.setProductDescription(productEntity.getDescription());
        productDto.setProductActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(productDto));
        return productDto;
    }

    public ProductEntity productToEntity(ProductDto productDto){
        return new ProductEntity(productDto.getProductId(), productDto.getProductName(), productDto.getProductRegularPrice(),
                productDto.getProductCategory(), productDto.getProductDiscount(), productDto.getProductDescription());
    }
}
