package org.example.entities;

public record AppointmentAddon(
        String name,
        Double price,
        Integer quantity
) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(quantity).append(" ".repeat(5));
        sb.append(name).append(" ".repeat(10));
        sb.append(price).append(" ".repeat(5)).append("\n");

        return   sb.toString();
    }
}
