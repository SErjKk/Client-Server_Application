package Collection;
import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 8L;

    public Coordinates(Long x, float y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates() {}

    private Long x;       //координата по Ox (x != null)
    private float y;      //координата по Oy

    public Long getX() {
        return x;
    }
    public void setX(Long x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
}