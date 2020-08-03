package shoppingList.product.domain;

import shoppingList.shoppingCart.domain.ShoppingCartEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
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

    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    //<<<

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", insertable = false, updatable = false)
    private ShoppingCartEntity shoppingCartEntity;

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

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public ShoppingCartEntity getShoppingCartEntity() {
        return shoppingCartEntity;
    }

    public void setShoppingCartEntity(ShoppingCartEntity shoppingCartEntity) {
        this.shoppingCartEntity = shoppingCartEntity;
    }

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
                Objects.equals(description, that.description) &&
                Objects.equals(shoppingCartId, that.shoppingCartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, regularPrice, category, discount, description, shoppingCartId);
    }

    @Override
    public String toString() {
        return "ID = " + id +
                ", name='" + name + '\'' +
                ", regularPrice=" + regularPrice +
                ", category=" + category +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", shoppingCartId=" + shoppingCartId +
                '}';
    }
}
