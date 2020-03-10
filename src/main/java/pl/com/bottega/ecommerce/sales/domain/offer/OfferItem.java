/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

// TODO

public class OfferItem {
    // product TODO do innej klasy
    private String productId;

    private Money productPrice;

    private String productName;

    private Date productSnapshotDate;

    private String productType;
    // TODO dotąd

    private int quantity;

    private Money totalCost;

    private Discount discount;

    public OfferItem(String productId, Money productPrice, String productName, Date productSnapshotDate, String productType,
            int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null);
    }

    public OfferItem(String productId, Money productPrice, String productName, Date productSnapshotDate, String productType,
            int quantity, Discount discount) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productSnapshotDate = productSnapshotDate;
        this.productType = productType;

        this.quantity = quantity;
        this.discount = discount;

        this.totalCost.value = productPrice.value.multiply(new BigDecimal(quantity)).subtract(discount.getValue().value);
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice.value;
    }

    public String getProductName() {
        return productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getTotalCost() {
        return totalCost.value;
    }

    public String getTotalCostCurrency() {
        return totalCost.currency;
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount, productId, productName, productPrice, productSnapshotDate, productType,
                quantity, totalCost);
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
        OfferItem other = (OfferItem) obj;
        return Objects.equals(discount, other.discount)
               && Objects.equals(productId, other.productId)
               && Objects.equals(productName, other.productName)
               && Objects.equals(productPrice, other.productPrice)
               && Objects.equals(productSnapshotDate, other.productSnapshotDate)
               && Objects.equals(productType, other.productType)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }


    /**
     *
     * @param other
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (productPrice == null) {
            if (other.productPrice != null) {
                return false;
            }
        } else if (!productPrice.equals(other.productPrice)) {
            return false;
        }
        if (productName == null) {
            if (other.productName != null) {
                return false;
            }
        } else if (!productName.equals(other.productName)) {
            return false;
        }

        if (productId == null) {
            if (other.productId != null) {
                return false;
            }
        } else if (!productId.equals(other.productId)) {
            return false;
        }
        if (productType == null) {
            if (other.productType != null) {
                return false;
            }
        } else if (!productType.equals(other.productType)) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.value.compareTo(other.totalCost.value) > 0) {
            max = totalCost.value;
            min = other.totalCost.value;
        } else {
            max = other.totalCost.value;
            min = totalCost.value;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
