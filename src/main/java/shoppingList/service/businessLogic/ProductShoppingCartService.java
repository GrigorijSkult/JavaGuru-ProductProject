package shoppingList.service.businessLogic;

import org.springframework.stereotype.Service;
import shoppingList.product.domain.ProductEntity;
import shoppingList.product.mappers.ProductMapper;
import shoppingList.product.services.businessLogic.ProductService;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.service.businessLogic.ShoppingCartService;

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

        shoppingCartEntity.getProducts().add(productEntity);
        shoppingCartService.updatedShoppingCartService(shoppingCartId, shoppingCartEntity);

        productEntity.getShopping_cart().add(shoppingCartEntity);//NullPointExeption
        productService.updateProductService(productEntityId, productEntity);

        //---ProductEntity ManyToOne - ShoppingCartEntity OneToMany---
        //variant with changing in productEntity
//        productEntity.setShopping_cart(shoppingCartEntity);
//        productService.updateProductService(productEntityId, productEntity);
        //variant with changing in shoppingCartEntity - does not has problem with DTO
//        shoppingCartEntity.getProducts().add(productEntity);
//        shoppingCartService.updatedShoppingCartService(shoppingCartId, shoppingCartEntity);
    }
}
