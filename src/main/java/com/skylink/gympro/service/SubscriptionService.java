package com.skylink.gympro.service;

import com.skylink.gympro.model.Subscription;

import java.util.List;
public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);
    Subscription getSubscriptionById(Long id);
    List<Subscription> getAllSubscriptions();

    Subscription updateSubscription(Subscription subscription);
    void deleteSubscription(Subscription subscription);
}