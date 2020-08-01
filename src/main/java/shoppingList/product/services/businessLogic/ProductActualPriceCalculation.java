package shoppingList.product.services.businessLogic;

import shoppingList.product.dto.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductActualPriceCalculation {

    public BigDecimal calculateProductActualPrice(ProductDto productDto) {
        if (productDto.getDiscount().equals(BigDecimal.valueOf(0.00))) {
            return productDto.getRegularPrice().setScale(3, RoundingMode.HALF_UP);
        } else {
            return productDto.getRegularPrice().multiply(BigDecimal.valueOf(1.00).subtract
                    (productDto.getDiscount().divide(BigDecimal.valueOf(100.00)))).setScale(3, RoundingMode.HALF_UP);
        }
    }
}
