package Filters;
import java.io.Serializable;

public class xFilter implements Serializable {
    public xFilter(String login, String password, String condition, long x) {
        this.login = login;
        this.password = password;
        this.x = x;
        this.condition = condition;
    }

    private static final long serialVersionUID = 21L;
    private long x;
    private String login;
    private String password;
    private String condition;
}