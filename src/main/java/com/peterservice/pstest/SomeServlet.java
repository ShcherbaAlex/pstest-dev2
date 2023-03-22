package com.peterservice.pstest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.peterservice.pstest.common.Customer;
import com.peterservice.pstest.common.Customers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andrey.Shilov on 17.11.2015.
 */
public class SomeServlet extends HttpServlet {

    private Customers customers = new Customers();
    private ObjectMapper mapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response;

        if (req.getParameter("customerId") != null) {
            Long customerId = Long.valueOf(req.getParameter("customerId"));
            Customer customer = customers.getCustomer(customerId);
            if (customer != null) {
                if (req.getHeader("Accept").equals("application/xml")) {
                    response = xmlMapper.writeValueAsString(customer);
                } else {
                    response = mapper.writeValueAsString(customer);
                }
            } else {
                resp.setStatus(404);
                return;
            }
        } else {
            if (req.getHeader("Accept").equals("application/xml")) {
                response = xmlMapper.writeValueAsString(customers);
            } else {
                response = mapper.writeValueAsString(customers);
            }
        }

        resp.setHeader("Content-Type", req.getHeader("Accept"));
        resp.getOutputStream().write(response.getBytes("UTF-8"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuilder request = new StringBuilder();
            String s;
            while ((s = in.readLine()) != null) {
                request.append(s);
            }
            in.close();

            JsonNode json = mapper.readTree(request.toString());
            Customer customer = mapper.treeToValue(json, Customer.class);
            customer.setId(customers.getCustomers().size()+1L);
            customers.addCustomer(customer);
            resp.setStatus(201);
        } catch (Exception e){
            resp.setStatus(500);
        }
    }
}
