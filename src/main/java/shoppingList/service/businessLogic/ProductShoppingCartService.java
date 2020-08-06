package shoppingList.service.businessLogic;

import org.springframework.stereotype.Service;
import shoppingList.product.domain.ProductEntity;
import shoppingList.product.mappers.ProductMapper;
import shoppingList.product.services.businessLogic.ProductService;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.service.businessLogic.ShoppingCartService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    public void addProductEntityToShoppingCart(Long shoppingCartId, Long productEntityId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartService.findShoppingCartByID(shoppingCartId);
        ProductEntity productEntity = productMapper.productToEntity(productService.findProductByID(productEntityId));
        //variant with changing in productEntity
        Set<ShoppingCartEntity> s = new HashSet<>(Collections.emptySet());
//        Optional<Set<ShoppingCartEntity>> shopping_cart = Optional.ofNullable(productEntity.getShopping_cart());
//        if (shopping_cart.isPresent()){
//            s.addAll(productEntity.getShopping_cart());
//        }
        s.add(shoppingCartEntity);
        productEntity.setShopping_cart(s);
        productService.updateProductService(productEntityId, productEntity);


        shoppingCartEntity.getProducts().add(productEntity);
        shoppingCartService.updatedShoppingCartService(shoppingCartId, shoppingCartEntity);

        //variant with changing in shoppingCartEntity - does not has problem with DTO
//        shoppingCartEntity.getProducts().add(productEntity);
//        shoppingCartService.updatedShoppingCartService(shoppingCartId, shoppingCartEntity);
    }

/*    public void addProductEntityToShoppingCart(Long shoppingCartId, Long productEntityId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartService.findShoppingCartByID(shoppingCartId);
        ProductEntity productEntity = productMapper.productToEntity(productService.findProductByID(productEntityId));
        //variant with changing in productEntity
        productEntity.setShopping_cart(shoppingCartEntity);
        productService.updateProductService(productEntityId, productEntity);
        //variant with changing in shoppingCartEntity - does not has problem with DTO
        shoppingCartEntity.getProducts().add(productEntity);
        shoppingCartService.updatedShoppingCartService(shoppingCartId, shoppingCartEntity);
    }*/
}
