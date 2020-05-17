package shoppingList.businessLogic.validations;

import shoppingList.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductValidationService implements ValidationForm<Product> {
    private final ProductNameValidation nameValidation = new ProductNameValidation();
    private final ProductPriceValidation priceValidation = new ProductPriceValidation();
    private final ProductCategoryValidation categoryValidation = new ProductCategoryValidation();
    private final ProductDiscountValidation discountValidation = new ProductDiscountValidation();
    private final ProductDescriptionValidation descriptionValidation = new ProductDescriptionValidation();
    private final List<String> errorLogs = new ArrayList<>();

    @Override
    public boolean isValid(Product product) {
        boolean priceIsValid = priceValidation.isValid(product);
        if (!nameValidation.isValid(product)) {
            errorLogs.add(nameValidation.errorMessage());
        }
        if (!priceIsValid) {
            errorLogs.add(priceValidation.errorMessage());
        }
        if (!categoryValidation.isValid(product)) {
            errorLogs.add(categoryValidation.errorMessage());
        }
        if (priceIsValid) {
            if (!discountValidation.isValid(product)) {
                errorLogs.add(discountValidation.errorMessage());
            }
        }
        if (!descriptionValidation.isValid(product)) {
            errorLogs.add(descriptionValidation.errorMessage());
        }
        return errorLogs.isEmpty();
}

    @Override
    public String errorMessage() {
        return errorLogs.toString();
    }
}
