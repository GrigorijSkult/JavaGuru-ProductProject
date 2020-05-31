package shoppingList.services.validations;

import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductDescriptionValidation implements ValidationRule<ProductEntity> {

    public String errorMessage = "Product`s description should be shorter then 100 symbols;";

    @Override
    public void validate(ProductDto productDto) {
        if (!(productDto.getProductDescription() == null || productDto.getProductDescription().isEmpty())
                && productDto.getProductDescription().length() > 100) {
            throw new ProductValidationException(errorMessage);
        }
    }

}
