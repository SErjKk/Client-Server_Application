package Collection;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentSkipListSet;

public class VehicleCollection implements Serializable {

    //Статические переменные
    private static ConcurrentSkipListSet<Vehicle> collection = new ConcurrentSkipListSet<>();
    private static LocalDateTime creationDate = LocalDateTime.now();
    private static final long serialVersionUID = 6L;

    //Вернуть размер или коллекцию целиком
    public static int getSize() {
        return collection.size();
    }
    public static ConcurrentSkipListSet<Vehicle> getCollection() {
        return collection;
    }
}