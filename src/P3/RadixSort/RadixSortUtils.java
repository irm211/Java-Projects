package P3.RadixSort;

public class RadixSortUtils {

    static int getDigitAtIndexOfFromEnd(int num, int index) {
        int shiftedNum = num / powerOf10(index);
        return shiftedNum % 10;
    }
    static int powerOf10(int index) {
        int result = 1;
        for (int i = 0; i < index; i++){
            result *= 10;
        }
        return result;
    }
}
