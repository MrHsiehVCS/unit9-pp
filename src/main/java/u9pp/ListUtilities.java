package u9pp;

import java.util.List;

public class ListUtilities {
    public static <T> void shuffleList(List<T> list) {
        // Fischer-Yates shuffle
        for(int i = 0; i < list.size(); i++) {
            swapInList(list, i, (int)(Math.random() * (list.size() - i) + i));
        }
    }
    
    public static <T> void swapInList(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
