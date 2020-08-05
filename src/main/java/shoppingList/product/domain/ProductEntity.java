package shoppingList.product.domain;

import shoppingList.shoppingCart.domain.ShoppingCartEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
//@Table(name = "products2")//ManyToMany
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "regular_price")
    private BigDecimal regularPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProductCategory category;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "description")
    private String description;

    //<<<
    //---ProductEntity ManyToOne - ShoppingCartEntity OneToMany---
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", insertable = false, updatable = false)
    private ShoppingCartEntity shoppingCartEntity;

    //---ManyToMany---
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "products_in_shopping_carts",
//            joinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id"))
//    private Set<ShoppingCartEntity> shoppingCarts;
    //>>>

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, BigDecimal regularPrice, ProductCategory category,
                         BigDecimal discount, String description) {
        this.id = id;
        this.name = name;
        this.regularPrice = regularPrice;
        this.category = category;
        this.discount = discount;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShoppingCartEntity getShoppingCartEntity() {
        return shoppingCartEntity;
    }

    public void setShoppingCartEntity(ShoppingCartEntity shoppingCartEntity) {
        this.shoppingCartEntity = shoppingCartEntity;
    }

//    public Set<ShoppingCartEntity> getShoppingCarts() {
//        return shoppingCarts;
//    }
//
//    public void setShoppingCarts(Set<ShoppingCartEntity> shoppingCarts) {
//        this.shoppingCarts = shoppingCarts;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(regularPrice, that.regularPrice) &&
                category == that.category &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, regularPrice, category, discount, description);
    }

    @Override
    public String toString() {
        return "ID = " + id +
                ", name='" + name + '\'' +
                ", regularPrice=" + regularPrice +
                ", category=" + category +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                '}';
    }
}
