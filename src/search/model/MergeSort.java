package search.model;

import java.util.ArrayList;
import java.util.List;

/**
  * User: Ding
 * Date: 5/29/13
 * Time: 4:08 PM
  */
public class MergeSort {
    public <T extends Comparable<T>> void sort (List<T> numbers) {
        sort_itera(0, numbers.size() - 1, numbers);
    }

    private <T extends Comparable<T>> void sort_itera(int low, int high, List<T> numbers) {
        if(low < high) {
            int mid = (low + high) / 2;
            sort_itera(low, mid, numbers);
            sort_itera(mid + 1,high, numbers);
            merge(low,mid,high,numbers);
        }
    }

    private <T extends Comparable<T>> void merge(int low, int mid, int high, List<T> numbers) {
        List<T> u = new ArrayList<T>();
        int i = low;
        int j = mid + 1;
        while (i<=mid && j<=high) {
            if (numbers.get(i).compareTo(numbers.get(j)) < 0) {
                u.add(numbers.get(i));
                i++;
            }
            else {
                u.add(numbers.get(j));
                j++;
            }
        }
        if(i > mid) {
            while (j <= high) {
                u.add(numbers.get(j));
                j++;
            }
        }
        else {
            while (i <= mid) {
                u.add(numbers.get(i));
                i++;
            }
        }

        for (int m = low; m <= high; m++) {
            numbers.set(m,u.get(m - low));
        }
    }
}