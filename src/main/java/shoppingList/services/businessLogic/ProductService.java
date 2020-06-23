package shoppingList.services.businessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.services.validations.exception.ProductNotFoundException;
import shoppingList.services.validations.ProductValidationService;
import shoppingList.repository.ProductImpRepository;
import shoppingList.domain.ProductEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService implements TemplateService<ProductDto> {

    private final ProductImpRepository productImpRepository;
    private final ProductValidationService validationService;
    private final ProductMapper productMapper;

    public ProductService(ProductImpRepository productImpRepository, ProductValidationService validationService, ProductMapper productMapper) {
        this.productImpRepository = productImpRepository;
        this.validationService = validationService;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto addProductService(ProductDto newProductDto) {
        validationService.validate(newProductDto);
        ProductEntity newProductEntity = productMapper.productToEntity(newProductDto);
        ProductEntity savedProductEntity = productImpRepository.addProduct(newProductEntity);
        return productMapper.productToDto(savedProductEntity);
    }

    @Override
    public boolean removeProductByIDService(Long id) throws ProductNotFoundException {
        if (productImpRepository.existsById(id)) {
            productImpRepository.removeProductByID(id);
            return true;
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public List<ProductDto> listOfAllProductsService() {
        if (productImpRepository.listOfAllProducts().isEmpty()) {
            System.out.println("DataBase is empty");
            return Collections.emptyList();
        } else {
            System.out.println("Product DataBase:");
            List<ProductDto> allProductDtoList = new ArrayList<>();
            for (ProductEntity value : productImpRepository.listOfAllProducts()) {
                allProductDtoList.add(productMapper.productToDto(value));
            }
            return allProductDtoList;
        }
    }

    @Override
    public ProductDto findProductByID(Long id) throws ProductNotFoundException {
        ProductEntity productEntity = productImpRepository.findProductByID(id);
        if (productEntity != null) {
            return productMapper.productToDto(productEntity);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public ProductDto updateProductService(Long id, ProductDto updatedProductDto) throws ProductNotFoundException {
        if (productImpRepository.existsById(id)) {
            validationService.validate(updatedProductDto);
            ProductEntity updatedProductEntity = productMapper.productToEntity(updatedProductDto);
            return productMapper.productToDto(productImpRepository.updateProduct(id, updatedProductEntity));
        } else {
            throw new ProductNotFoundException(id);
        }
    }
}
