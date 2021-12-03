package com.reactive.batches;

import com.reactive.ReactiveUtil;
import lombok.Data;

/**
 * @author shashshe
 */
@Data
public class PurchaseOrder {
    private String item;
    private String category;
    private double price;
    private int quantity;

    public PurchaseOrder() {
        this.item = ReactiveUtil.FAKER().commerce().productName();
        this.category = ReactiveUtil.FAKER().commerce().department();
        this.price = Double.parseDouble(ReactiveUtil.FAKER().commerce().price());
        this.quantity = ReactiveUtil.FAKER().random().nextInt(1, 10);

    }

}
