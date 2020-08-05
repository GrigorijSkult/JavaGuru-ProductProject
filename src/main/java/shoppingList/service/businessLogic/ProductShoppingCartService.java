package shoppingList.service.businessLogic;

import org.springframework.stereotype.Service;
import shoppingList.product.domain.ProductEntity;
import shoppingList.product.mappers.ProductMapper;
import shoppingList.product.services.businessLogic.ProductService;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.service.businessLogic.ShoppingCartService;

@Service
public class ProductShoppingCartService {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductShoppingCartService(ShoppingCartService shoppingCartService, ProductService productService, ProductMapper productMapper) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    public ShoppingCartEntity addProductEntityToShoppingCart(Long shoppingCartId, Long productEntityId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartService.findShoppingCartByID(shoppingCartId);
        ProductEntity productEntity = productMapper.productToEntity(productService.findProductByID(productEntityId));
        shoppingCartEntity.getProducts().add(productEntity);
        return shoppingCartService.updatedShoppingCartService(shoppingCartId, shoppingCartEntity);
    }
}
