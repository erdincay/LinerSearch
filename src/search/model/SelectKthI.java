package search.model;

import java.util.List;

/**
 * interface for all select algorithm
 * User: Ding
 * Date: 5/31/13
 * Time: 11:37 AM
 */
public interface SelectKthI {
    public <T extends Comparable<T>> T select(int keyIndex, List<T> numbers);
}
