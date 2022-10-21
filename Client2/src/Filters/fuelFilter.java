package Filters;

import Collection.FuelType;
import Collection.VehicleType;

import java.io.Serializable;

public class fuelFilter implements Serializable {
    public fuelFilter(String login, String password, FuelType fuel) {
        this.login = login;
        this.password = password;
        this.fuel = fuel;
    }

    private static final long serialVersionUID = 30L;
    private FuelType fuel;
    private String login;
    private String password;
}