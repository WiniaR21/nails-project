package com.winiardev.nails.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.winiardev.nails.order.controller.request.RequestConfirmOrder;
import com.winiardev.nails.order.dto.DtoOrder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Controller interface for managing orders.
 * 
 * @author winiar.dev
 */
@Validated
public interface IControllerOrder {

    /**
     * Fetches all orders associated with the customer's email address.
     * 
     * @param servlet HttpServletRequest object to extract the customer's email.
     * @return ResponseEntity containing a list of orders related to the customer.
     */
    @Secured({ "CUSTOMER", "ADMIN" })
    @GetMapping(path = "/order/fetch/my")
    ResponseEntity<List<DtoOrder>> fetchAllMyOrders(HttpServletRequest servlet);

    /**
     * Fetches all orders in the system.
     * 
     * @return ResponseEntity containing a list of all orders.
     */
    @Secured({ "ADMIN" })
    @GetMapping(path = "/order/fetch/all")
    ResponseEntity<List<DtoOrder>> fetchAllOrders();

    /**
     * Fetches all confirmed orders that have not been marked as done.
     * 
     * @return ResponseEntity containing a list of confirmed but not done orders.
     */
    @Secured({ "ADMIN" })
    @GetMapping(path = "/order/fetch/all/confirmed-not-done")
    ResponseEntity<List<DtoOrder>> fetchAllConfirmedNotDoneOrders();

    /**
     * Fetches all confirmed and completed orders.
     * 
     * @return ResponseEntity containing a list of confirmed and done orders.
     */
    @Secured({ "ADMIN" })
    @GetMapping(path = "/order/fetch/all/done")
    ResponseEntity<List<DtoOrder>> fetchAllDoneOrders();

    /**
     * Fetches all orders that have not been confirmed.
     * 
     * @return ResponseEntity containing a list of orders that are not confirmed.
     */
    @Secured({ "ADMIN" })
    @GetMapping(path = "/order/fetch/all/not-confirmed")
    ResponseEntity<List<DtoOrder>> fetchAllNotConfirmedOrders();

    /**
     * Fetches a specific order by its ID.
     * 
     * @param orderId the ID of the order to fetch.
     * @return ResponseEntity containing the order details.
     */
    @Secured({ "ADMIN" })
    @GetMapping(path = "/order/fetch/by-id")
    ResponseEntity<DtoOrder> fetchOrderById(@NotNull @RequestParam String orderId);

    /**
     * Creates a new order for a specific task.
     * 
     * @param taskId  the ID of the task to be ordered.
     * @param servlet HttpServletRequest object to extract the customer's details.
     * @return ResponseEntity indicating the result of the operation.
     */
    @Secured({ "CUSTOMER", "ADMIN" })
    @PostMapping(path = "/order/make")
    ResponseEntity<Void> makeOrder(@NotNull @RequestParam String taskId, HttpServletRequest servlet);

    /**
     * Confirms an order.
     * 
     * @param request the request object containing order confirmation details.
     * @return ResponseEntity indicating the result of the operation.
     */
    @Secured({ "ADMIN" })
    @PutMapping(path = "/order/confirm")
    ResponseEntity<Void> confirmOrder(@Valid @RequestBody RequestConfirmOrder request);

    /**
     * Marks an order as done.
     * 
     * @param orderId the ID of the order to be marked as done.
     * @return ResponseEntity indicating the result of the operation.
     */
    @Secured({ "ADMIN" })
    @PutMapping(path = "/order/done")
    ResponseEntity<Void> markOrderAsDone(@NotNull @RequestParam String orderId);
}