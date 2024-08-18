package com.winiardev.nails.order.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.order.entity.Order;

@Repository
public interface RepoOrder extends JpaRepository<Order, String> {

    List<Order> findAllByEmail(Email email);

    @Query("SELECT o FROM Order o WHERE o.confirmed = true AND o.done = false")
    List<Order> findAllConfirmedNotDoneOrders();

    @Query("SELECT o FROM Order o WHERE o.done = true")
    List<Order> findAllDoneOrders();

    @Query("SELECT o FROM Order o WHERE o.confirmed = false")
    List<Order> findAllNotConfirmedOrders();
}
