package Filters;
import java.io.Serializable;

public class yFilter implements Serializable {
    public yFilter(String login, String password, String condition, float y) {
        this.login = login;
        this.password = password;
        this.y = y;
        this.condition = condition;
    }

    private static final long serialVersionUID = 22L;
    private float y;
    private String login;
    private String password;
    private String condition;
}