package com.winiardev.nails.order.dto;

import java.time.LocalDateTime;

import com.winiardev.nails.order.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DtoOrder {
    private String orderId;
    private String taskId;
    private String email;
    private LocalDateTime time;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean confirmed;
    private boolean done;

    public DtoOrder(Order order) {
        this.orderId = order.getOrderId();
        this.taskId = order.getTaskId();
        this.email = order.getEmail().email();
        this.time = order.getTime();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
        this.confirmed = order.isConfirmed();
        this.done = order.isDone();
    }
}
