package Filters;
import java.io.Serializable;

public class capacityFilter implements Serializable {
    public capacityFilter(String login, String password, String condition, double capacity) {
        this.login = login;
        this.password = password;
        this.capacity = capacity;
        this.condition = condition;
    }

    private static final long serialVersionUID = 27L;
    private double capacity;
    private String login;
    private String password;
    private String condition;
}