package shoppingList.services.validations.nameValidation;

import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.repository.ProductImpRepository;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductUniqueNameValidation implements ValidationRule<ProductDto>  {

    private final ProductImpRepository productImpRepository;
    private final ProductMapper productMapper;

    public ProductUniqueNameValidation(ProductImpRepository productImpRepository, ProductMapper productMapper) {
        this.productImpRepository = productImpRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void validate(ProductDto productDto) {
        ProductEntity entity = productMapper.productToEntity(productDto);
        if (productImpRepository.existsByName(entity)) {
            throw new ProductValidationException("Product name should be unique");
        }
    }
}

