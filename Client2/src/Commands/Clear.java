package Commands;
import java.io.Serializable;

public class Clear implements Serializable {
    public Clear(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;
    private static final long serialVersionUID = 9L;
}
