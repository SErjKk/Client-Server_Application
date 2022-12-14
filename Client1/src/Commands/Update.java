package Commands;
import Collection.Vehicle;
import java.io.Serializable;

public class Update implements Serializable {
    public Update(String login, String password, Vehicle vehicle) {
        this.login = login;
        this.password = password;
        this.vehicle = vehicle;
    }

    private static final long serialVersionUID = 5L;
    private Vehicle vehicle;
    private String login;
    private String password;
}