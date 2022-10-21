package Filters;
import java.io.Serializable;

public class ownerFilter implements Serializable {
    public ownerFilter(String login, String password, String owner) {
        this.login = login;
        this.password = password;
        this.owner = owner;
    }

    private static final long serialVersionUID = 31L;
    private String owner;
    private String login;
    private String password;
}