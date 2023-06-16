package com.example.csharing.domain;

import java.util.List;

public class Sort {
    public List<CourseBoard> sortBoardByScore(List<CourseBoard> list, int start, int end) {
        if (end > start) {
            int pivot = partition(list, start, end);
            sortBoardByScore(list, start, pivot - 1);
            sortBoardByScore(list, pivot + 1, end);
        }
        return list;
    }

    public int partition(List<CourseBoard> list, int start, int end) {
        int pivot = list.get(start).getSize();
        int left = start + 1;
        int right = end;
        boolean done = false;
        while (done == false) {
            while (left <= right && list.get(left).getSize() >= pivot) {
                left++;
            }
            while (left <= right && list.get(right).getSize() <= pivot) {
                right--;
            }
            if (left > right) {
                done = true;
            } else {
                swap(list, left, right);
            }
        }
        swap(list, start, right);
        return right;
    }

    public void swap(List<CourseBoard> list, int x, int y) {
        CourseBoard temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }

}
