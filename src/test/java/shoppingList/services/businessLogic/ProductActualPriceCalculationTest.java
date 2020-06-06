package shoppingList.services.businessLogic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.dto.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductActualPriceCalculationTest {

    @InjectMocks
    ProductDto victim;

    @Test
    public void calculateProductActualPriceNoDiscount() {
        victim.setProductRegularPrice(BigDecimal.valueOf(77.06));
        victim.setProductDiscount(BigDecimal.valueOf(0.00));
        victim.setProductActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(77.060).setScale(3, RoundingMode.HALF_UP), victim.getProductActualPrice());
    }

    @Test
    public void calculateProductActualPriceIntNumbers() {
        victim.setProductRegularPrice(BigDecimal.valueOf(77));
        victim.setProductDiscount(BigDecimal.valueOf(36));
        victim.setProductActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(49.280).setScale(3, RoundingMode.HALF_UP), victim.getProductActualPrice());
    }

    @Test
    public void calculateProductActualPriceDoubleNumbers() {
        victim.setProductRegularPrice(BigDecimal.valueOf(22.46));
        victim.setProductDiscount(BigDecimal.valueOf(18.96));
        victim.setProductActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(18.202).setScale(3, RoundingMode.HALF_UP), victim.getProductActualPrice());
    }
}