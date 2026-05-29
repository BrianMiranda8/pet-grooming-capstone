package org.example.entities;

 public record ExtraItem (String name, double price, int quantity){

  @Override
  public String toString() {
       return String.format("%-5d %-30s $%-10.2f\n", quantity, name, price);

  }
 }
