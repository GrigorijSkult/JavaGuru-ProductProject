package shoppingList.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ProductEntity {

    private Long productId;
    private String productName;
    private BigDecimal productRegularPrice;
    private ProductCategory productCategory;
    private BigDecimal productDiscount;
    private String productDescription;
    private BigDecimal productActualPrice;

    public ProductEntity(Long id, String productName, BigDecimal productRegularPrice, ProductCategory productCategory) {
        this.productId = id;
        this.productName = productName;
        this.productRegularPrice = productRegularPrice;
        this.productCategory = productCategory;
        this.productDiscount = BigDecimal.valueOf(0.00);
        this.productDescription = null;
        this.productActualPrice = productRegularPrice;
    }

    public ProductEntity(Long id, String productName, BigDecimal productRegularPrice, ProductCategory productCategory,
                         BigDecimal productDiscount, String productDescription) {
        this.productId = id;
        this.productName = productName;
        this.productRegularPrice = productRegularPrice;
        this.productCategory = productCategory;
        this.productDiscount = productDiscount;
        this.productDescription = productDescription;
        this.productActualPrice = productRegularPrice.multiply
                (BigDecimal.valueOf(1.00).subtract(productDiscount.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)));
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductRegularPrice() {
        return productRegularPrice;
    }

    public void setProductRegularPrice(BigDecimal productRegularPrice) {
        this.productRegularPrice = productRegularPrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductActualPrice() {
        return productActualPrice;
    }

    public void setProductActualPrice() {
        this.productActualPrice = productRegularPrice.multiply
                (productDiscount.divide(BigDecimal.valueOf(100.00), RoundingMode.HALF_UP));
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
                Objects.equals(productDescription, productEntity.productDescription) &&
                Objects.equals(productActualPrice, productEntity.productActualPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productRegularPrice, productCategory, productDiscount, productDescription, productActualPrice);
    }

    @Override
    public String toString() {
        return "ID = " + productId +
                ", Product Name= '" + productName + '\'' +
                ", Regular price= " + productRegularPrice + " EUR" +
                ", Product category= '" + productCategory + '\'' +
                ", Discount= " + productDiscount + " %" +
                ", Actual price= " + productActualPrice + " EUR" +
                ", Description= '" + productDescription +
                '\'' + ";";
    }
}