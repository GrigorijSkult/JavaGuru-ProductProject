package shoppingList.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductEntity {

    private Long id;
    private String name;
    private BigDecimal regularPrice;
    private ProductCategory category;
    private BigDecimal discount;
    private String description;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity productEntity = (ProductEntity) o;
        return Objects.equals(name, productEntity.name) &&
                Objects.equals(regularPrice, productEntity.regularPrice) &&
                category == productEntity.category &&
                Objects.equals(discount, productEntity.discount) &&
                Objects.equals(description, productEntity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regularPrice, category, discount, description);
    }

    @Override
    public String toString() {
        return "ID = " + id +
                ", Product Name= '" + name + '\'' +
                ", Regular price= " + regularPrice + " EUR" +
                ", Product category= '" + category + '\'' +
                ", Discount= " + discount + " %" +
                ", Description= '" + description +
                '\'' + ";";
    }
}
