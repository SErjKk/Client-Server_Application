package Commands;
import java.io.Serializable;

public class Info implements Serializable {
    public Info(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;
    private static final long serialVersionUID = 8L;
}
