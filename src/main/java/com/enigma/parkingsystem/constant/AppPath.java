package com.enigma.parkingsystem.constant;

public class AppPath {
    //Path parent
    public final static String AUTH = "/api/v1/auth";
    public final static String CUSTOMER = "/customers";
    public final static String CAR = "/cars";
    public final static String PARKING_LOT = "/parking-lots";
    public final static String PARKING_SPOT = "/parking-spots";
    public final static String TRANSACTION = "/transactions";

    //Path Sub Parent

    public final static  String REGISTER = "/register";
    public final static  String LOGIN = "/login";
    public final static  String GET_BY_ID = "/{id}";
    public final static String DELETED_BY_ID = "/{id}";
}
