package shoppingList.services.validations;

import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductDescriptionValidation implements ValidationRule<ProductEntity> {

    @Override
    public void validate(ProductDto productDto) {
        if (!(productDto.getProductDescription() == null || productDto.getProductDescription().isEmpty())
                && productDto.getProductDescription().length() > 999) {
            throw new ProductValidationException("Product`s description should be shorter then 999 symbols");
        }
    }

}
