package shoppingList.services.businessLogic;

import org.junit.Test;
import shoppingList.dto.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static junit.framework.TestCase.assertEquals;

public class ProductActualPriceCalculationTest {

    @Test
    public void calculateProductActualPriceNoDiscount() {
        ProductDto victim = new ProductDto();
        victim.setProductRegularPrice(BigDecimal.valueOf(77.06));
        victim.setProductDiscount(BigDecimal.valueOf(0.00));
        victim.setProductActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(77.060).setScale(3, RoundingMode.HALF_UP), victim.getProductActualPrice());
    }

    @Test
    public void calculateProductActualPriceIntNumbers() {
        ProductDto victim = new ProductDto();
        victim.setProductRegularPrice(BigDecimal.valueOf(77));
        victim.setProductDiscount(BigDecimal.valueOf(36));
        victim.setProductActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(49.280).setScale(3, RoundingMode.HALF_UP), victim.getProductActualPrice());
    }

    @Test
    public void calculateProductActualPriceDoubleNumbers() {
        ProductDto victim = new ProductDto();
        victim.setProductRegularPrice(BigDecimal.valueOf(22.46));
        victim.setProductDiscount(BigDecimal.valueOf(18.96));
        victim.setProductActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(18.202).setScale(3, RoundingMode.HALF_UP), victim.getProductActualPrice());
    }
}