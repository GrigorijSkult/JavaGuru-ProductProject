package shoppingList.mappers;

import org.springframework.stereotype.Component;
import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.services.businessLogic.ProductActualPriceCalculation;

@Component
public class ProductMapper {

    public ProductDto productToDto(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setRegularPrice(productEntity.getRegularPrice());
        productDto.setCategory(productEntity.getCategory());
        productDto.setDiscount(productEntity.getDiscount());
        productDto.setDescription(productEntity.getDescription());
        productDto.setActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(productDto));
        return productDto;
    }

    public ProductEntity productToEntity(ProductDto productDto){
        return new ProductEntity(productDto.getId(), productDto.getName(), productDto.getRegularPrice(),
                productDto.getCategory(), productDto.getDiscount(), productDto.getDescription());
    }
}
