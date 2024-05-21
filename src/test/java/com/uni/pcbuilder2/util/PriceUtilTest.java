package com.uni.pcbuilder2.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class PriceUtilTest {

    @Test
    void calculateComponentPrice() {
        var actual = PriceUtil.calculateComponentPrice(54545, 0.02F);
        assertThat(actual).isEqualTo(1090);
    }

    @Test
    void calculateRealBudget() {
        var actual = PriceUtil.calculateRealBudget(54545, true, true);
        assertThat(actual).isEqualTo(54245);
    }

}
