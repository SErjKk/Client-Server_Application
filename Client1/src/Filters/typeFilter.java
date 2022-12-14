package Filters;
import Collection.VehicleType;
import java.io.Serializable;

public class typeFilter implements Serializable {
    public typeFilter(String login, String password, VehicleType type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    private static final long serialVersionUID = 26L;
    private VehicleType type;
    private String login;
    private String password;
}