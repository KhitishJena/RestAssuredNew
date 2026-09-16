package com.satish.Ecommerce;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetOrderResponsePojo {
    private GetOrderDataPojo data;
    private String message;
}
