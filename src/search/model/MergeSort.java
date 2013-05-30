package search.model;

import java.util.List;

/**
  * User: Ding
 * Date: 5/29/13
 * Time: 4:08 PM
  */
public class MergeSort {
    public void sort (List<Integer> numbers) {
        sort_itera(0, numbers.size() - 1, numbers);
    }

    private void sort_itera(int low, int high, List<Integer> numbers) {
        if(low < high) {
            int mid = (low + high) / 2;
            sort_itera(low, mid, numbers);
            sort_itera(mid + 1,high, numbers);
            merge(low,mid,high,numbers);
        }
    }

    private void merge(int low, int mid, int high, List<Integer> numbers) {
        Integer[] u = new Integer[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i<=mid && j<=high) {
            if (numbers.get(i) < numbers.get(j)) {
                u[k] = numbers.get(i);
                i++;
            }
            else {
                u[k] = numbers.get(j);
                j++;
            }
            k++;
        }
        if(i > mid) {
            while (j <= high) {
                u[k] = numbers.get(j);
                k++;
                j++;
            }
        }
        else {
            while (i <= mid) {
                u[k] = numbers.get(i);
                k++;
                i++;
            }
        }

        for (int m = low; m <= high; m++) {
            numbers.set(m,u[m - low]);
        }
    }
}