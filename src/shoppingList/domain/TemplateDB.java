package shoppingList.domain;

import java.math.BigDecimal;

public interface TemplateDB {

    Long templateId = 0L;
    String templateName = null;
    BigDecimal templateRegularPrice = null;
    ProductCategory templateCategory = null;

    BigDecimal templateDiscount = null;
    String templateDescription = null;
    BigDecimal getTemplateActualPrice = null;

}
