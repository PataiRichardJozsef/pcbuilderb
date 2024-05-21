package com.uni.pcbuilder2.util;

public class PriceUtil {

    public static Integer calculateComponentPrice(Integer realBudget, Float cpuPercentile) {
        return (int) (realBudget * cpuPercentile);
    }

    public static Integer calculateRealBudget(Integer budget, Boolean includeGadgets, Boolean isPrebuilt) {
        Integer realBudget = budget;
        if (includeGadgets) {
            realBudget = realBudget - 200;
        }

        if (isPrebuilt) {
            realBudget = realBudget - 100;
        }

        return realBudget;
    }
}
