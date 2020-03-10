package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    public BigDecimal value;
    public String currency;

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money(BigDecimal value) {
        this.value = value;
        this.currency = null;
    }

    public Money() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Money other = (Money) obj;

        return Objects.equals(value, other.value) && Objects.equals(currency, other.currency);
    }
}