package Filters;
import java.io.Serializable;

public class powerFilter implements Serializable {
    public powerFilter(String login, String password, String condition, long power) {
        this.login = login;
        this.password = password;
        this.power = power;
        this.condition = condition;
    }

    private static final long serialVersionUID = 24L;
    private long power;
    private String login;
    private String password;
    private String condition;
}