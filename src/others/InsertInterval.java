package others;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class InsertInterval {
    InsertInterval insertInterval;

    @BeforeEach
    public void init() {
        insertInterval = new InsertInterval();
    }

    @Test
    public void firstTest() {
        int[][] input = new int[][]{{1, 3}, {6, 9}};
        int[] newInterval = new int[]{2, 5};
        int[][] result = insertInterval.insert(input, newInterval);
        for (int[] data : result)
            System.out.println(data[0] + "-" + data[1]);
    }

    @Test
    public void secondTest() {
        int[][] input = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = new int[]{4, 8};
        int[][] result = insertInterval.insert(input, newInterval);
        for (int[] data : result)
            System.out.println(data[0] + "-" + data[1]);
    }

    @Test
    public void thirdTest() {
        int[][] input = new int[][]{};
        int[] newInterval = new int[]{5, 7};
        int[][] result = insertInterval.insert(input, newInterval);
        for (int[] data : result)
            System.out.println(data[0] + "-" + data[1]);
    }

    @Test
    public void fourthTest() {
        int[][] input = new int[][]{{1, 5}};
        int[] newInterval = new int[]{5, 7};
        int[][] result = insertInterval.insert(input, newInterval);
        for (int[] data : result)
            System.out.println(data[0] + "-" + data[1]);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)
            return new int[][]{{newInterval[0], newInterval[1]}};
        if (newInterval.length == 0) return intervals;
        Deque<int[]> stack = new ArrayDeque<>();

        boolean inserted = false;
        for (int i = 0; i < intervals.length; i++) {
            int[] data = intervals[i];
            if (!inserted) {
                if (data[1] >= newInterval[0]) {
                    stack.push(data);
                    inserted = true;
                    mergeAndInsert(stack, newInterval);
                } else {
                    mergeAndInsert(stack, data);
                }
            } else {
                mergeAndInsert(stack, data);
            }
        }

        System.out.println(stack);
        int[][] result = new int[stack.size()][2];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.poll();
        }
        return result;
    }

    private void mergeAndInsert(Deque<int[]> stack, int[] newInterval) {
        if (!stack.isEmpty()) {
            int[] peeked = stack.peek();
            if (peeked[1] >= newInterval[0]) {
                int[] newData = new int[]{Math.min(peeked[0], newInterval[0]),
                        Math.max(peeked[1], newInterval[1])};
                stack.poll();
                stack.push(newData);
            } else {
                stack.push(newInterval);
            }
        } else {
            stack.push(newInterval);
        }
    }
}
