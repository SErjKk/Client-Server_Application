package Commands;
import java.io.Serializable;

public class Registration implements Serializable {
    public Registration(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;
    private static final long serialVersionUID = 2L;
}
