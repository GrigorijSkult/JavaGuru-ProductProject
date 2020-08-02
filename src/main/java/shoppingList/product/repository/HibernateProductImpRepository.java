package shoppingList.product.repository;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shoppingList.product.domain.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql_Hibernate")
@Transactional
public class HibernateProductImpRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    HibernateProductImpRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProductEntity addProduct(ProductEntity newProduct) {
        sessionFactory.getCurrentSession().save(newProduct);
        return newProduct;
    }

    @Override
    public void removeProductByID(Long id) {
        sessionFactory.getCurrentSession().delete(findProductByID(id));
    }

    @Override
    public List<ProductEntity> listOfAllProducts() {
        return sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .list();
    }

    @Override
    public Optional<ProductEntity> findProductByID(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(ProductEntity.class, id));
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        sessionFactory.getCurrentSession().update(updatedProduct);
        return updatedProduct;
    }

    @Override
    public Optional<ProductEntity> findProductByName(String name) {
        ProductEntity productEntity = (ProductEntity) sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        return Optional.ofNullable(productEntity);
    }

    @Override
    public boolean existsById(Long id) {
        return sessionFactory.getCurrentSession().find(ProductEntity.class, id) != null;
    }
}
