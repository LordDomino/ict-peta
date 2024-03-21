import java.util.ArrayList;

public class Utils {
    public <T> int indexOf(ArrayList<T> arr, T val) {
        for (int i = 0; i < arr.size(); i++) {
            T retrieved = arr.get(i);
            if (retrieved.equals(val)) {
                return i;
            }
        }
    }
}
