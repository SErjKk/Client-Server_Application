package Filters;
import java.io.Serializable;

public class IdFilter implements Serializable {
    public IdFilter(String login, String password, String condition, int id) {
        this.login = login;
        this.password = password;
        this.id = id;
        this.condition = condition;
    }

    private static final long serialVersionUID = 20L;
    private int id;
    private String login;
    private String password;
    private String condition;
}