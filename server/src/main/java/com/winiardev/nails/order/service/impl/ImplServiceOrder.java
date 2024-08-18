package com.winiardev.nails.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.exception.exceptions.ResourceNotFoundException;
import com.winiardev.nails.order.controller.request.RequestConfirmOrder;
import com.winiardev.nails.order.dto.DtoOrder;
import com.winiardev.nails.order.entity.Order;
import com.winiardev.nails.order.repo.RepoOrder;
import com.winiardev.nails.order.service.IServiceOrder;
import com.winiardev.nails.security.service.IServiceJwt;
import com.winiardev.nails.task.entity.Task;
import com.winiardev.nails.task.repo.RepoTask;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplServiceOrder implements IServiceOrder {

    private final RepoOrder repoOrder;
    private final RepoTask repoTask;

    private final IServiceJwt jwtService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoOrder> fetchAllByEmail(HttpServletRequest servlet) {
        Email email = jwtService.extractEmailFromServlet(servlet);
        List<DtoOrder> dtoOrders = new ArrayList<>();
        repoOrder.findAllByEmail(email).forEach(orded -> {
            dtoOrders.add(new DtoOrder(orded));
        });
        return dtoOrders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoOrder> fetchAllOrders() {
        List<DtoOrder> dtoOrders = new ArrayList<>();
        repoOrder.findAll().forEach(order -> {
            dtoOrders.add(new DtoOrder(order));
        });
        return dtoOrders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoOrder> fetchAllConfirmedNotDoneOrders() {
        List<DtoOrder> dtoOrders = new ArrayList<>();
        repoOrder.findAllConfirmedNotDoneOrders().forEach(order -> {
            dtoOrders.add(new DtoOrder(order));
        });
        return dtoOrders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoOrder> fetchAllDoneOrders() {
        List<DtoOrder> dtoOrders = new ArrayList<>();
        repoOrder.findAllDoneOrders().forEach(order -> {
            dtoOrders.add(new DtoOrder(order));
        });
        return dtoOrders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoOrder> fetchAllNotConfirmedOrders() {
        List<DtoOrder> dtoOrders = new ArrayList<>();
        repoOrder.findAllNotConfirmedOrders().forEach(order -> {
            dtoOrders.add(new DtoOrder(order));
        });
        return dtoOrders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DtoOrder fetchOrderById(String orderId) {
        return new DtoOrder(repoOrder.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("order", "orderId", orderId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeOrder(String taskId, HttpServletRequest servlet) {
        Task task = repoTask.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("task", "taskId", taskId));
        Email email = jwtService.extractEmailFromServlet(servlet);

        repoOrder.save(new Order(task, email));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void confirmOrder(RequestConfirmOrder request) {
        Order order = repoOrder.findById(request.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("order", "orderId", request.getOrderId()));

        order.setTime(request.getTime());
        order.setConfirmed(true);

        repoOrder.save(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markOrderAsDone(String orderId) {
        Order order = repoOrder.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("order", "orderId", orderId));

        order.setDone(true);
        repoOrder.save(order);
    }

}
