package com.uni.pcbuilder2.model;

import java.util.List;

import com.uni.pcbuilder2.entity.Gadget;
import com.uni.pcbuilder2.entity.Hardware;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Setup {
    private List<Hardware> hardware;
    private List<Gadget> gadgets;
    private transient Integer totalPrice;
    private Integer moneyLeft;

    public Integer getTotalPrice() {
        var realTotalPrice = 0;
        for (Hardware h : this.hardware) {
            realTotalPrice = realTotalPrice + h.getMarketPrice();
        }

        return realTotalPrice;
    }

}
