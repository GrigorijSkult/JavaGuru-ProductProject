package shoppingList.product.dto;

import shoppingList.product.domain.ProductCategory;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal regularPrice;
    private ProductCategory category;
    private BigDecimal discount;
    private String description;
    private BigDecimal actualPrice;
    private Set<ShoppingCartEntity> shopping_cart;

    public Set<ShoppingCartEntity> getShopping_cart() {
        return shopping_cart;
    }

    public void setShopping_cart(Set<ShoppingCartEntity> shopping_cart) {
        this.shopping_cart = shopping_cart;
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

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(regularPrice, that.regularPrice) &&
                category == that.category &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(actualPrice, that.actualPrice) &&
                Objects.equals(shopping_cart, that.shopping_cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, regularPrice, category, discount, description, actualPrice, shopping_cart);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regularPrice=" + regularPrice +
                ", category=" + category +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", actualPrice=" + actualPrice +
                ", shopping_cart=" + shopping_cart +
                '}';
    }
}
