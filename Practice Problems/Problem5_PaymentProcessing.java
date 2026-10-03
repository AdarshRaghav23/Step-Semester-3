// Question 5: Payment Processing for a Shopping System

import java.util.ArrayList;
import java.util.List;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        // Simulating successful credit card payment
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        // Simulating failed PayPal payment
        return false;
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }
}

class ShopProduct {
    private String name;
    private double price;

    public ShopProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private ShopProduct product;
    private int quantity;

    public OrderItem(ShopProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

class ShoppingCustomer {
    private String name;

    public ShoppingCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Order {
    private ShoppingCustomer customer;
    private List<OrderItem> items = new ArrayList<OrderItem>();
    private String status;

    public Order(ShoppingCustomer customer) {
        this.customer = customer;
        this.status = "Pending";
        System.out.println("Order created for " + customer.getName() + ".");
    }

    public ShoppingCustomer getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public void addItem(ShopProduct product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotalAmount() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void pay(PaymentMethod paymentMethod, String methodName) {
        if (isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via " + methodName + " for Order " + customer.getName().replace("Customer ", "") + ".");
        boolean success = paymentMethod.processPayment(getTotalAmount());

        if (success) {
            this.status = "Paid";
            System.out.println("Payment for Order " + customer.getName().replace("Customer ", "") + " successful. Order status: " + status + ".");
        } else {
            System.out.println("Payment for Order " + customer.getName().replace("Customer ", "") + " failed. Order status: " + status + ".");
        }
    }
}

public class Problem5_PaymentProcessing {
    public static void main(String[] args) {
        ShopProduct prodA = new ShopProduct("Product A", 10.0);
        ShopProduct prodB = new ShopProduct("Product B", 20.0);
        ShopProduct prodC = new ShopProduct("Product C", 15.0);

        // Customer X setup
        ShoppingCustomer customerX = new ShoppingCustomer("Customer X");
        Order orderX = new Order(customerX);
        orderX.addItem(prodA, 2);
        orderX.addItem(prodB, 1);
        orderX.pay(new CreditCardPayment(), "Credit Card");

        // Customer Y setup (empty order)
        ShoppingCustomer customerY = new ShoppingCustomer("Customer Y");
        Order orderY = new Order(customerY);
        orderY.pay(new CreditCardPayment(), "Credit Card");

        // Customer Z setup
        ShoppingCustomer customerZ = new ShoppingCustomer("Customer Z");
        Order orderZ = new Order(customerZ);
        orderZ.addItem(prodC, 1);
        orderZ.pay(new PayPalPayment(), "PayPal");
    }
}