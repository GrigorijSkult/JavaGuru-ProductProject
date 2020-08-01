package shoppingList.product.services.validations.nameValidation;

import shoppingList.product.domain.ProductEntity;
import shoppingList.product.dto.ProductDto;
import shoppingList.product.mappers.ProductMapper;
import shoppingList.product.repository.ProductRepository;
import shoppingList.product.services.validations.ValidationRule;
import shoppingList.product.services.validations.exception.ProductValidationException;

import java.util.Optional;

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
        Optional<ProductEntity> productByName = productRepository.findProductByName(entity.getName());
        if (productByName.isPresent()) {
            if (!productByName.get().getId().equals(productDto.getId())) {
                throw new ProductValidationException("Product name should be unique");
            }
        }
    }
}


