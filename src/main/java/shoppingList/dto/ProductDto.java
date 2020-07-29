package shoppingList.dto;

import shoppingList.domain.ProductCategory;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal regularPrice;
    private ProductCategory category;
    private BigDecimal discount;
    private String description;
    private BigDecimal actualPrice;

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
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, regularPrice, category, discount, description);
    }

    @Override
    public String toString() {
        return "ID = " + id +
                ", Product Name= '" + name + '\'' +
                ", Regular price= " + regularPrice + " EUR" +
                ", Product category= '" + category + '\'' +
                ", Discount= " + discount + " %" +
                ", Actual price= " + actualPrice + " EUR" +
                ", Description= '" + description +
                '\'' + ";";
    }
}
