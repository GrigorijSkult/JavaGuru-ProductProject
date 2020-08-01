package shoppingList.product.services.businessLogic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.product.dto.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductActualPriceCalculationTest {

    @InjectMocks
    private ProductDto victim;

    @Test
    public void calculateProductActualPriceNoDiscount() {
        victim.setRegularPrice(BigDecimal.valueOf(77.06));
        victim.setDiscount(BigDecimal.valueOf(0.00));
        victim.setActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(77.060).setScale(3, RoundingMode.HALF_UP), victim.getActualPrice());
    }

    @Test
    public void calculateProductActualPriceIntNumbers() {
        victim.setRegularPrice(BigDecimal.valueOf(77));
        victim.setDiscount(BigDecimal.valueOf(36));
        victim.setActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(49.280).setScale(3, RoundingMode.HALF_UP), victim.getActualPrice());
    }

    @Test
    public void calculateProductActualPriceDoubleNumbers() {
        victim.setRegularPrice(BigDecimal.valueOf(22.46));
        victim.setDiscount(BigDecimal.valueOf(18.96));
        victim.setActualPrice(new ProductActualPriceCalculation().calculateProductActualPrice(victim));

        assertEquals(BigDecimal.valueOf(18.202).setScale(3, RoundingMode.HALF_UP), victim.getActualPrice());
    }
}