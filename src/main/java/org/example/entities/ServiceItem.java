package org.example.entities;

import java.util.Map;

public record ServiceItem(
        String name,
        Map<String,Double> price
) { }
