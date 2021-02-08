package com.experis.okko.icloneapp.controllers;

import com.experis.okko.icloneapp.dao.CustomerRepository;
import com.experis.okko.icloneapp.exceptions.BadRequestException;
import com.experis.okko.icloneapp.exceptions.ResourceNotFoundException;
import com.experis.okko.icloneapp.models.Customer;
import com.experis.okko.icloneapp.models.CustomerInvoiceTotalWrapper;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import static com.experis.okko.icloneapp.controllers.RestURIConstants.*;

/*
* Okko Partanen
* Experis Academy Task 4
*
* Rest API Controller class.
* Rest URI's are held in RestURIConstants class. Few custom exceptions are thrown if
* API requests are not valid. The exceptions have been implemented mostly for the sake of learning.
*
*/

@RestController
public class RestAPIController {

    private CustomerRepository customerRepository = new CustomerRepository();

    //get all customers from the database
    @RequestMapping(value=GET_CUSTOMERS, method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers(){
        return customerRepository.selectAllCustomer();
    }

    //get countries and customer count in each country
    @RequestMapping(value=GET_COUNTRIES_BY_CUSTOMER_COUNT, method = RequestMethod.GET)
    public LinkedHashMap<String, Integer> getCountriesWithUserCount(){
        return customerRepository.selectCountriesByCustomerCount();
    }

    //get single customer by id
    @RequestMapping(value =GET_CUSTOMER_BY_ID, method = RequestMethod.GET)
    public Customer getCustomerByPathId(@PathVariable String id){
        Customer customer = customerRepository.selectCustomerById(id);
        //throw 404 if customer is null
        if (customer.getFirstName() == null)
            throw new ResourceNotFoundException();
        else
            return customer;
    }

    //add a customer to database
    @RequestMapping(value= CREATE_CUSTOMER, method = RequestMethod.POST)
    public Customer addNewCustomer(@RequestBody Customer customer){
        Customer addedCustomer = customerRepository.addCustomer(customer);
        //throw 402 if customer couldn't be created
        if (addedCustomer == null)
            throw new BadRequestException();

        return addedCustomer;
    }

    //update a customers fields in database
    @RequestMapping(value = UPDATE_CUSTOMER, method = RequestMethod.PUT)
    public Boolean updateExistingCustomer(@PathVariable int id, @RequestBody Customer customer){
        //if parameter doesn't match body throw 402
        if (id != customer.getCustomerId())
            throw new BadRequestException();
        return customerRepository.updateCustomer(customer);
    }

    //get all customers and how much money they have spent
    @RequestMapping(value=GET_CUSTOMERS_BY_INVOICE_TOTAL, method = RequestMethod.GET)
    public ArrayList<CustomerInvoiceTotalWrapper> getCustomersByMoneySpent(){
        return customerRepository.selectAllCustomerByInvoiceTotal();
    }

    //get a customers favourite genre(s)
    @RequestMapping(value =GET_CUSTOMER_FAV_GENRE, method = RequestMethod.GET)
    public ArrayList<String> getCustomerFavGenre(@PathVariable String id){
        //throw 404 if customerid doesn't exist / can't find favourite genres for given id
        //new customers don't have favourite genres, so this is not the best practice.
        ArrayList<String> favourites = customerRepository.selectCustomerFavGenre(id);
        if (favourites.size() == 0)
            throw new ResourceNotFoundException();
        else
            return favourites;
    }
}
