package test;

public class BitSetTest
{

    public static void main(String[] args) {
        System.out.println(missingNumberInByBitSet(new int[] {0, 1, 2, 3, 4, 6 }));
    }

    /**
     * 从0到n之间取出n个不同的数，找出漏掉的那个。
     * 
     * 注意：你的算法应当具有线性的时间复杂度。你能实现只占用常数额外空间复杂度的算法吗？
     * 
     * @param array
     * @return
     */
    public static int missingNumberInByBitSet(int[] array) {
        int bitSet = 0;
        for (int element : array) {
            bitSet |= 1 << element;
        }

        for (int i = 0; i < array.length; i++) {
            if ((bitSet & 1 << i) == 0) {
                return i;
            }
        }

        return 0;
    }
}
