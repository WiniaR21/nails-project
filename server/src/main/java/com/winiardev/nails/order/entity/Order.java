package com.winiardev.nails.order.entity;

import java.time.LocalDateTime;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.task.entity.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "taskId")
    private String taskId;

    @Embedded
    private Email email;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "confirmed")
    private boolean confirmed;

    @Column(name = "done")
    private boolean done;

    public Order(Task task, Email email) {
        this.taskId = task.getTaskId();
        this.email = email;
        createdAt = LocalDateTime.now();
        time = null;
        confirmed = false;
        done = false;
    }
}
