package com.winiardev.nails.order.controller.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestConfirmOrder {

    @NotNull(message = "Order time can not be null")
    private LocalDateTime time;

    @NotNull(message = "OrderId time can not be null")
    private String orderId;
}
