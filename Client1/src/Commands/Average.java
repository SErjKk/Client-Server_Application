package Commands;
import java.io.Serializable;

public class Average implements Serializable {
    public Average(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;
    private static final long serialVersionUID = 10L;
}
