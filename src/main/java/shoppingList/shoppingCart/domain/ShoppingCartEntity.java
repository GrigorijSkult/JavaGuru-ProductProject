package shoppingList.shoppingCart.domain;

import org.springframework.transaction.annotation.Transactional;
import shoppingList.product.domain.ProductEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    //---ProductEntity ManyToOne - ShoppingCartEntity OneToMany---
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shopping_cart")
//    private Set<ProductEntity> products;
    //variant with @JoinColumn
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "shopping_cart_id")
//    private Set<ProductEntity> products;

    //  ---ManyToMany---
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "shopping_cart")
    private Set<ProductEntity> products;

       //>>>

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartEntity that = (ShoppingCartEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, products);
    }

    @Transactional
    @Override
    public String toString() {
        return "ShoppingCartEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
//                ", products=" + products +
                '}';
    }
}
