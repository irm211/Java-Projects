package P3.RadixSort;

import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    private static final int ASCENDING_ORDER = 1;

    private static void radixSort(int[] nums, boolean ascending) {
        // TO DO:
        int maxNo = nums[0], maxDigitsNo = 0;
        ArrayDeque<Integer>[] buckets = new ArrayDeque[10];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxNo) {
                maxNo = nums[i];
            }
        }
        while (maxNo != 0) {
            maxNo /= 10;
            maxDigitsNo++;
        }

        for (int i = 0; i < 10; i++) {
            buckets[i] = new ArrayDeque<>();
        }

        for (int digit = 0; digit < maxDigitsNo; digit++) {
            for (int num : nums) {
                int digitValue = RadixSortUtils.getDigitAtIndexOfFromEnd(num, digit);
                buckets[digitValue].add(num);
            }
            int index = 0;
            for (ArrayDeque<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    nums[index++] = bucket.poll();
                }
            }
        }
        if (!ascending) {
            reverseArray(nums);
        }
    }

    private static void reverseArray (int[] nums){
        int start = 0, end = nums.length - 1;
        while (start < end){
            int aux = nums[start];
            nums[start]= nums[end];
            nums[end] = aux;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 1 to sort the numbers in an ascending order. For a descending order, press any other number");
        int order = scanner.nextInt();
        System.out.println("Insert the number of numbers you want to sort");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            System.out.println("Insert a number > 0:");
            nums[i] = scanner.nextInt();
        }

        boolean ascending = (order == ASCENDING_ORDER);

        radixSort(nums, ascending);

        System.out.println(Arrays.toString(nums));
    }
}
