package Commands;
import java.io.Serializable;

public class Authorization implements Serializable {
    public Authorization(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;
    private static final long serialVersionUID = 1L;
}
