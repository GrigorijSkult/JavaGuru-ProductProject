package shoppingList.shoppingCart.repository;

import shoppingList.shoppingCart.domain.ShoppingCartEntity;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {

    ShoppingCartEntity addShoppingCart(ShoppingCartEntity newShoppingCart);

    boolean removeShoppingCartById(Long id);

    Optional<ShoppingCartEntity> findShoppingCartById(Long id);

    List<ShoppingCartEntity> listOfAllShoppingCarts();

    ShoppingCartEntity updateShoppingCart(ShoppingCartEntity updatedShoppingCart);

}
