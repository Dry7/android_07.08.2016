package com.dry7.a07082016.services;

public class ProductDataService {
    private final static ProductDataService INSTANCE = new ProductDataService();

    private static final String URL = "http://api.gifts48.ru/";

    public ProductDataService() {
    }

    public static ProductDataService getInstance()
    {
        return INSTANCE;
    }

}
