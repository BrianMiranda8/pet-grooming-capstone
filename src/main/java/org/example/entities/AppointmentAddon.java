package org.example.entities;

public record AppointmentAddon(
        String name,
        Double price,
        Integer quantity
) {
    @Override
    public String toString() {

        return String.format("%-5d%-30s%10.2f\n", quantity, name, price);
    }
}
