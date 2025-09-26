package com.example.listeners;

import com.example.events.ProductCreatedEvent;
import com.example.events.ProductDeletedEvent;
import com.example.events.ProductUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    private static final Logger log = LoggerFactory.getLogger(ProductEventListener.class);

    @Async
    @EventListener
    public void onCreated(ProductCreatedEvent e) {
        log.info("[EVENT] ProductCreated -> id={}, name={}",
                e.product().getId(), e.product().getName());
        // Simula trabajo en background: email, auditoría, etc.
    }

    @Async
    @EventListener
    public void onUpdated(ProductUpdatedEvent e) {
        log.info("[EVENT] ProductUpdated -> id={}, stock={}",
                e.product().getId(), e.product().getStock());
    }

    @Async
    @EventListener
    public void onDeleted(ProductDeletedEvent e) {
        log.info("[EVENT] ProductDeleted -> id={}", e.productId());
    }
}

