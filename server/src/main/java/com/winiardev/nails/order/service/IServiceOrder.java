package com.winiardev.nails.order.service;

import java.util.List;

import com.winiardev.nails.order.controller.request.RequestConfirmOrder;
import com.winiardev.nails.order.dto.DtoOrder;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Service interface for managing orders.
 * 
 * @author winiar.dev
 */
public interface IServiceOrder {

    /**
     * Fetches all orders associated with the customer's email address.
     * 
     * @param servlet HttpServletRequest object to extract the customer's email.
     * @return a list of orders related to the customer.
     */
    List<DtoOrder> fetchAllByEmail(HttpServletRequest servlet);

    /**
     * Fetches all orders in the system.
     * 
     * @return a list of all orders.
     */
    List<DtoOrder> fetchAllOrders();

    /**
     * Fetches all confirmed orders that have not been marked as done.
     * 
     * @return a list of confirmed but not done orders.
     */
    List<DtoOrder> fetchAllConfirmedNotDoneOrders();

    /**
     * Fetches all confirmed and completed orders.
     * 
     * @return a list of confirmed and done orders.
     */
    List<DtoOrder> fetchAllDoneOrders();

    /**
     * Fetches all orders that have not been confirmed.
     * 
     * @return a list of orders that are not confirmed.
     */
    List<DtoOrder> fetchAllNotConfirmedOrders();

    /**
     * Fetches a specific order by its ID.
     * 
     * @param orderId the ID of the order to fetch.
     * @return the order details.
     */
    DtoOrder fetchOrderById(String orderId);

    /**
     * Creates a new order for a specific task.
     * 
     * @param taskId  the ID of the task to be ordered.
     * @param servlet HttpServletRequest object to extract the customer's details.
     */
    void makeOrder(String taskId, HttpServletRequest servlet);

    /**
     * Confirms an order.
     * 
     * @param request the request object containing order confirmation details.
     */
    void confirmOrder(RequestConfirmOrder request);

    /**
     * Marks an order as done.
     * 
     * @param orderId the ID of the order to be marked as done.
     */
    void markOrderAsDone(String orderId);
}