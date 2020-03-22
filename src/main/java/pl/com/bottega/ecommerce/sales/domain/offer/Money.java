package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private BigDecimal value;
    private String currency;

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money(BigDecimal value) {
        this.value = value;
        this.currency = null;
    }

    public Money() {}

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add (Money value) {
        return new Money(this.value.add(value.getValue()));
    }
    public Money subtract (Money value) {
        return new Money(this.value.subtract(value.getValue()));
    }
    public Money multiply (int value) {
        return new Money(this.value.multiply(new BigDecimal(value)));
    }


    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

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