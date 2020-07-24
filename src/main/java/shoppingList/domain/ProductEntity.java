package shoppingList.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductEntity {

    private Long productId;
    private String productName;
    private BigDecimal productRegularPrice;
    private ProductCategory productCategory;
    private BigDecimal productDiscount;
    private String productDescription;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String productName, BigDecimal productRegularPrice, ProductCategory productCategory,
                         BigDecimal productDiscount, String productDescription) {
        this.productId = id;
        this.productName = productName;
        this.productRegularPrice = productRegularPrice;
        this.productCategory = productCategory;
        this.productDiscount = productDiscount;
        this.productDescription = productDescription;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductRegularPrice(BigDecimal productRegularPrice) {
        this.productRegularPrice = productRegularPrice;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductRegularPrice() {
        return productRegularPrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity productEntity = (ProductEntity) o;
        return Objects.equals(productName, productEntity.productName) &&
                Objects.equals(productRegularPrice, productEntity.productRegularPrice) &&
                productCategory == productEntity.productCategory &&
                Objects.equals(productDiscount, productEntity.productDiscount) &&
                Objects.equals(productDescription, productEntity.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productRegularPrice, productCategory, productDiscount, productDescription);
    }

    @Override
    public String toString() {
        return "ID = " + productId +
                ", Product Name= '" + productName + '\'' +
                ", Regular price= " + productRegularPrice + " EUR" +
                ", Product category= '" + productCategory + '\'' +
                ", Discount= " + productDiscount + " %" +
                ", Description= '" + productDescription +
                '\'' + ";";
    }
}
