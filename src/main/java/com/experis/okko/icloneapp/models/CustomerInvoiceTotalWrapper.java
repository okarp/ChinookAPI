package com.experis.okko.icloneapp.models;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * Data access class wrapping Customers with their invoice total (amount spent). *
 */

public class CustomerInvoiceTotalWrapper {
    private Customer customer;
    private double invoiceTotal;

    public CustomerInvoiceTotalWrapper(Customer customer, double invoiceTotal) {
        this.customer = customer;
        this.invoiceTotal = invoiceTotal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(int invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }
}
