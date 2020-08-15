package shoppingList.product.mappers;

import org.springframework.stereotype.Component;
import shoppingList.product.domain.ProductEntity;
import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.ProductActualPriceCalculation;

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
        productDto.setShopping_cart(productEntity.getShoppingCart());
        productDto.setActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(productDto));
        return productDto;
    }

    public ProductEntity productToEntity(ProductDto productDto){
        ProductEntity productEntity = new ProductEntity(productDto.getId(), productDto.getName(), productDto.getRegularPrice(),
                productDto.getCategory(), productDto.getDiscount(), productDto.getDescription());
        productEntity.setShoppingCart(productDto.getShopping_cart());
        return productEntity;
    }
}
