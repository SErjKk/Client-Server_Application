package Commands;
import java.io.Serializable;

public class Show implements Serializable {
    public Show(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;
    private static final long serialVersionUID = 3L;
}
