package com.winiardev.nails.order.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winiardev.nails.order.controller.IControllerOrder;
import com.winiardev.nails.order.controller.request.RequestConfirmOrder;
import com.winiardev.nails.order.dto.DtoOrder;
import com.winiardev.nails.order.service.IServiceOrder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class ImplControllerOrder implements IControllerOrder {

    private final IServiceOrder iOrder;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DtoOrder>> fetchAllMyOrders(HttpServletRequest servlet) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iOrder.fetchAllByEmail(servlet));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DtoOrder>> fetchAllOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iOrder.fetchAllOrders());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DtoOrder>> fetchAllConfirmedNotDoneOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iOrder.fetchAllConfirmedNotDoneOrders());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DtoOrder>> fetchAllDoneOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iOrder.fetchAllDoneOrders());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DtoOrder>> fetchAllNotConfirmedOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iOrder.fetchAllNotConfirmedOrders());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<DtoOrder> fetchOrderById(@NotNull String orderId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iOrder.fetchOrderById(orderId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> makeOrder(@NotNull String taskId, HttpServletRequest servlet) {
        iOrder.makeOrder(taskId, servlet);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> confirmOrder(@Valid RequestConfirmOrder request) {
        iOrder.confirmOrder(request);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> markOrderAsDone(@NotNull String orderId) {
        iOrder.markOrderAsDone(orderId);
        return ResponseEntity.noContent().build();
    }

}
