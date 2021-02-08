package com.experis.okko.icloneapp.dao;

import com.experis.okko.icloneapp.models.Customer;
import com.experis.okko.icloneapp.models.CustomerInvoiceTotalWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * Repository for Customers.
 */

public class CustomerRepository {
    //connection string to database
    private String URL = DbProvider.CONNECTION_URL;
    private Connection dbConnection = null;

    //selecting all customers in the database and returns them in an ArrayList
    public ArrayList <Customer> selectAllCustomer() {
       ArrayList <Customer> customers = new ArrayList <Customer>();
        try {
            dbConnection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("SELECT FirstName,LastName,CustomerId, PostalCode," +
                            " Country, Phone, Email FROM Customer");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getInt("CustomerId"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Country"),
                                resultSet.getString("Email"),
                                resultSet.getString("Phone")
                        ));
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                dbConnection.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    //Selects Customer object with id that is given by param. Returns a null customer if id isn't in database.
    public Customer selectCustomerById(String customerId) {
        Customer customer = new Customer();
        try {

            dbConnection = DriverManager.getConnection(URL);
            // SQL query
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("SELECT FirstName,LastName,CustomerId, PostalCode, " +
                            "Country, Phone, Email FROM Customer WHERE CustomerId = ?");
            preparedStatement.setString(1, customerId);
            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();
            //set values to constructed customer object
            customer.setFirstName(resultSet.getString("FirstName"));
            customer.setLastName(resultSet.getString("LastName"));
            customer.setCustomerId(resultSet.getInt("CustomerId"));
            customer.setPostalCode(resultSet.getString("PostalCode"));
            customer.setCountry(resultSet.getString("Country"));
            customer.setEmail(resultSet.getString("Email"));
            customer.setPhoneNumber(resultSet.getString("Phone"));

        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                dbConnection.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return customer;
    }

    //Selects all customers and their invoice total ordered in descending order.
    public ArrayList <CustomerInvoiceTotalWrapper> selectAllCustomerByInvoiceTotal() {
        ArrayList <CustomerInvoiceTotalWrapper> customersInvoiceTotal = new ArrayList <>();
        try {
            dbConnection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("SELECT FirstName, LastName, Customer.CustomerId as CustomerId, PostalCode, " +
                            "Country, Phone, Email, SUM(TOTAL) AS TOTALSUM FROM Customer, Invoice " +
                            "WHERE Customer.CustomerId = Invoice.CustomerId " +
                            "GROUP BY Invoice.CustomerId ORDER BY TOTALSUM DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer;
                //wrapper class for invoice total and Customer object
                CustomerInvoiceTotalWrapper customerWithTotal;
                customer = new Customer(
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Country"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone")
                );
                customerWithTotal = new CustomerInvoiceTotalWrapper(customer, resultSet.getDouble("TOTALSUM"));
                customersInvoiceTotal.add(customerWithTotal);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                dbConnection.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            return customersInvoiceTotal;
        }
    }

    //Adds customer object to database. CustomerId is auto generated. Returns customer object with auto generated PK id.
    //Returns null if customer couldn't be added
    public Customer addCustomer(Customer customer) {

        try {
            dbConnection = DriverManager.getConnection(URL);

            //SQL query for adding new user
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("INSERT INTO Customer(firstName,lastName," +
                            "postalCode,country,email,phone) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPostalCode());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getPhoneNumber());

            //execute the statement
            //get the newly created customers customerId and set it to user object that the method returns.
            preparedStatement.executeUpdate();
            ResultSet keySet = preparedStatement.getGeneratedKeys();
            if(keySet.next()) {
                int customerId  = keySet.getInt(1);
                customer.setCustomerId(customerId);
            }

        } catch (Exception exception) {
            System.out.println(exception.toString());
            return null;
        } finally {
            try {
                dbConnection.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
                return null;
            }
        }
        return customer;
    }

    //Updates customers data fields
    public Boolean updateCustomer(Customer customer) {
        boolean success = false;
        try {

            dbConnection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("UPDATE Customer SET FirstName = ?, LastName = ?, " +
                            " PostalCode = ?, Country = ?, Email = ?, Phone = ? WHERE CustomerId =?");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPostalCode());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getPhoneNumber());
            preparedStatement.setInt(7, customer.getCustomerId());
            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                dbConnection.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    //Selects customers favourite genre(s). CustomerId is given as param.
    public ArrayList <String> selectCustomerFavGenre(String customerId) {
        ArrayList <String> genres = new ArrayList<>();
        try {
            dbConnection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("""
                            WITH t1
                                     AS(
                                    SELECT Count(i.invoiceid) Purchases,
                                           c.customerid,
                                           g.Name,
                                           g.genreid
                                    FROM   invoice i
                                               JOIN customer c
                                                    ON i.customerid = c.customerid
                                               JOIN invoiceline il
                                                    ON il.invoiceid = i.invoiceid
                                               JOIN track t
                                                    ON t.trackid = il.trackid
                                               JOIN genre g
                                                    ON t.genreid = g.genreid
                                    WHERE  c.customerid = ?
                                    GROUP  BY g.genreid, c.customerid)

                            SELECT Name
                            FROM t1
                            WHERE Purchases = (select MAX(Purchases) from t1);""");

            preparedStatement.setString(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String genre = (resultSet.getString("Name"));
                genres.add(genre);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                dbConnection.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        return genres;
    }

    //Selects all countries with customer count. Returns them in descending order in an linkedhashmap <String,Integer>.
    public LinkedHashMap <String,Integer> selectCountriesByCustomerCount() {
        LinkedHashMap<String, Integer> countries = new LinkedHashMap<>();
        try {
            dbConnection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("SELECT Country, count(Country) AS CustomerCount FROM Customer" +
                            " GROUP BY Country ORDER BY count(Country) DESC");

            ResultSet resultSet = preparedStatement.executeQuery();
            //iterate result set and add the results in linkedhashmap
            while (resultSet.next()) {
                String country = resultSet.getString("Country");
                int customerCount = resultSet.getInt("CustomerCount");
                countries.put(country, customerCount);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                dbConnection.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        return countries;
    }
}