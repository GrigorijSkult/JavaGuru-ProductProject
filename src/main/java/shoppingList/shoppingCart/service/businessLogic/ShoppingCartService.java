package shoppingList.shoppingCart.service.businessLogic;

import org.springframework.stereotype.Service;
import shoppingList.product.services.validations.exception.ProductNotFoundException;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.repository.ShoppingCartRepository;
import shoppingList.shoppingCart.service.businessLogic.exception.ShoppingCartNotFoundException;

import java.util.Collections;
import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCartEntity addShoppingCartService(ShoppingCartEntity newShoppingCart) {
        return shoppingCartRepository.addShoppingCart(newShoppingCart);
    }

    public boolean removeShoppingCartByIdService(Long id) throws ShoppingCartNotFoundException {
        if (shoppingCartRepository.findShoppingCartById(id).isPresent()) {
            shoppingCartRepository.removeShoppingCartById(id);
            return true;
        } else {
            throw new ShoppingCartNotFoundException(id);
        }
    }

    public List<ShoppingCartEntity> listOfAllShoppingCartService() {
        if (shoppingCartRepository.listOfAllShoppingCarts().isEmpty()) {
            System.out.println("Shopping Carts list is empty");
            return Collections.emptyList();
        } else {
            System.out.println("Shopping Carts list:");
            return shoppingCartRepository.listOfAllShoppingCarts();
        }
    }

    public ShoppingCartEntity findShoppingCartByID(Long id) throws ShoppingCartNotFoundException {
        return shoppingCartRepository.findShoppingCartById(id)
                .orElseThrow(() -> new ShoppingCartNotFoundException(id));
    }

    public ShoppingCartEntity updatedShoppingCartService(Long id, ShoppingCartEntity updatedShoppingCart) throws ProductNotFoundException {
        if (shoppingCartRepository.findShoppingCartById(id).isPresent()) {
            shoppingCartRepository.updateShoppingCart(updatedShoppingCart);
            return updatedShoppingCart;
        } else {
            throw new ShoppingCartNotFoundException(id);
        }
    }
}
