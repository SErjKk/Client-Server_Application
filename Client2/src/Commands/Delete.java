package Commands;
import java.io.Serializable;

public class Delete implements Serializable {
    public Delete(String login, String password, int id) {
        this.login = login;
        this.password = password;
        this.id = id;
    }

    private static final long serialVersionUID = 4L;
    private int id;
    private String login;
    private String password;
}