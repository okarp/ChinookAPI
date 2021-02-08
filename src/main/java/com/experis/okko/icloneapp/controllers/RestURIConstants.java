package com.experis.okko.icloneapp.controllers;
/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * RestURIConstants
 * Rest URI's are held in this class.
 */

public class RestURIConstants {
    public static final String GET_CUSTOMERS = "api/rest/customers";
    public static final String GET_CUSTOMER_BY_ID = "api/rest/customers/{id}";
    public static final String UPDATE_CUSTOMER = "api/rest/customers/{id}";
    public static final String CREATE_CUSTOMER = "api/rest/customers";
    public static final String GET_COUNTRIES_BY_CUSTOMER_COUNT = "api/rest/country/total/customers";
    public static final String GET_CUSTOMERS_BY_INVOICE_TOTAL = "api/rest/customers/invoice/total";
    public static final String GET_CUSTOMER_FAV_GENRE = "api/rest/customers/{id}/popular/genre";
}
