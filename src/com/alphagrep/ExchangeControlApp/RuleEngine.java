package com.alphagrep.ExchangeControlApp;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {

    List<RuleI<OrderRequest, OrderResponse>> rules;

    public RuleEngine() {
        rules = new ArrayList<>();
    }

    public OrderResponse rule(OrderRequest order) {
        return rules.stream()
                .filter(rule -> rule.matches(order))
                .map(rule -> rule.process(order))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Matching rule found"));
    }


    public RuleEngine registerRule(RuleI<OrderRequest, OrderResponse> rule) {
        rules.add(rule);
        return this;
    }


}
