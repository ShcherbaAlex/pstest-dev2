package com.peterservice.pstest.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey.Shilov on 18.11.2015.
 */
public class Customers {
    private List<Customer> customers;

    public Customers(){
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer getCustomer(Long customerId) {
        for (Customer customer: customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }

        return null;
    }
}
