package pl.com.bottega.ecommerce.sales.domain.offer;
import java.util.Objects;

public class Discount {
    private String discountCause;
    private Money value;

    public Money getValue() {
        return value;
    }

    public String getCause() {
        return discountCause;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountCause, value);
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
        Discount other = (Discount) obj;

        return Objects.equals(value, other.getValue()) && Objects.equals(discountCause, other.getCause());
    }
}
