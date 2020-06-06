package shoppingList.services.validations;

import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.repository.ProductImpRepository;
import shoppingList.services.validations.exception.ProductValidationException;

import java.util.ArrayList;
import java.util.List;

public class ProductNameValidation implements ValidationRule<ProductDto> {

    private final ProductImpRepository productImpRepository;
    private final ProductMapper productMapper;

    public ProductNameValidation(ProductImpRepository productImpRepository, ProductMapper productMapper) {
        this.productImpRepository = productImpRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void validate(ProductDto productDto) {
        List<String> errorLogs = new ArrayList<>();
        try {
            nameLengthValidation(productDto);
        }catch (ProductValidationException e){
            errorLogs.add(e.getMessage());
        }
        try {
            nameUniqueValidation(productDto);
        }catch (ProductValidationException e){
            errorLogs.add(e.getMessage());
        }
        if (!errorLogs.isEmpty()) {
            throw new ProductValidationException(errorLogs.toString());
        }
    }

    private void nameLengthValidation(ProductDto productDto){
        if (productDto.getProductName().length() < 3 || productDto.getProductName().length() > 32) {
            throw new ProductValidationException("Product name cannot be less than 3 characters and more than 32");
        }
    }

    private void nameUniqueValidation(ProductDto productDto){
        ProductEntity entity = productMapper.productToEntity(productDto);
        if (productImpRepository.existsByName(entity)){
            throw new ProductValidationException("Product name should be unique");
        }
    }
}
