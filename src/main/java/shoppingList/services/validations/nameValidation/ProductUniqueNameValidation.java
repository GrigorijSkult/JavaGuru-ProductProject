package shoppingList.services.validations.nameValidation;

import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.repository.ProductRepository;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductUniqueNameValidation implements ValidationRule<ProductDto> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductUniqueNameValidation(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void validate(ProductDto productDto) {
        ProductEntity entity = productMapper.productToEntity(productDto);
        if (productRepository.existsByName(entity)) {
            throw new ProductValidationException("Product name should be unique");
        }
    }
}

