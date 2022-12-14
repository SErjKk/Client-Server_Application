package Animation;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AppearCircle {

    private ScaleTransition tt;

    public AppearCircle(Node node) {
        tt = new ScaleTransition(Duration.seconds(1), node);
        tt.setToX(5);
        tt.setToY(5);
    }

    //Включении анимации
    public void playAnimation() {
        tt.playFromStart();
    }
}
