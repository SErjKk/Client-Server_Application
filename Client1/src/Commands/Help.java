package Commands;
import java.io.Serializable;

public class Help implements Serializable {
    public Help(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;
    private static final long serialVersionUID = 13L;
}
