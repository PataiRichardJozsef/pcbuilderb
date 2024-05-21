package com.uni.pcbuilder2.exception;

import lombok.Getter;

@Getter
public class NotAvailableHardwareException extends RuntimeException {

    public NotAvailableHardwareException(String type) {
         super("Hardware not available:  " + type);
    }

}
