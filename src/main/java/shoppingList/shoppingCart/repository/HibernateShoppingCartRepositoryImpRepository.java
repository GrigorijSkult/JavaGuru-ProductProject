package shoppingList.shoppingCart.repository;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql_Hibernate")
@Transactional
public class HibernateShoppingCartRepositoryImpRepository implements ShoppingCartRepository {

    private final SessionFactory sessionFactory;

    HibernateShoppingCartRepositoryImpRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCartEntity addShoppingCart(ShoppingCartEntity newShoppingCart) {
        sessionFactory.getCurrentSession().save(newShoppingCart);
        return newShoppingCart;
    }

    @Override
    public boolean removeShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> shoppingCart = findShoppingCartById(id);
        if (findShoppingCartById(id).isPresent()) {
            sessionFactory.getCurrentSession().delete(shoppingCart.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<ShoppingCartEntity> findShoppingCartById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(ShoppingCartEntity.class, id));
    }

    @Override
    public List<ShoppingCartEntity> listOfAllShoppingCarts() {
        return sessionFactory.getCurrentSession().createCriteria(ShoppingCartEntity.class)
                .list();
    }

    @Override
    public ShoppingCartEntity updateShoppingCart(ShoppingCartEntity updatedShoppingCart) {
        sessionFactory.getCurrentSession().update(updatedShoppingCart);
        return updatedShoppingCart;
    }
}
