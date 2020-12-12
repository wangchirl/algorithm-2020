package mine;

import java.util.Arrays;

/**
 * @author shadow
 * @create 2020-12-06
 * @description 异或运算 ^ ：无进位相加
 * N^N = 0
 * 0^N = N
 * -N = ~N + 1
 */
public class Binary {



	// 6、打印一个 int 型的 二进制
	public static void print(int value) {
		int MAX = 31;
		for (int i = MAX; i >= 0; i--) {
			System.out.print((value & (1 << i)) == 0 ? "0" : "1");
		}
		System.out.println();
	}


	// 5、判断一个数是否 2 的次幂
	// 2的次幂，只有一个位上是 1
	// 即 最右边的 1 就是他自己
	public static boolean isPowerOfTwo(int X) {
		return (X & (-X)) == X || (X & (~X + 1)) == X;
	}

	// 4、一个数组中有两种出现了奇数次，其他数都出现偶数次，找到这2个奇数的数
	// ^ 异或后剩下的即是二者的组合
	// A ^ B != 0 则其中必有一个位上是 1，一个位上不是 1
	// 找到最右的 1 ，将二者分开
	public static int[] getOddOfTwo(int[] arr) {
		int temp = 0;
		for (int i = 0; i < arr.length; i++) {
			temp ^= arr[i]; // A ^ B
		}
		// 找到最右侧的 1
		int B = 0;
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & getRightOne(temp)) != 0) { // 这个位上是 1 和不是 1 的分开
				B ^= arr[i]; // B
			}
		}
		int A = B ^ temp;
		return new int[]{A, B};
	}


	// 3、把一个int类型的数，提取出最右侧的 1 来
	// -N = ~N + 1
	// 负数的二进制求法：正数取反 + 1 / 最右边 1 的左边取反，右边不变
	// & 运算 遇到 0 为 0
	//  0000 1010
	//  1111 0110
	//  0000 0010
	public static int getRightOne(int X) {
		return X & (-X);
	}


	// 2、一个数组中有一种数出现奇数次，其他数出现偶数次，找到此奇数次的数
	// N^N = 0 的特性
	// 交换率 N^A^N = A
	public static int getOdd(int[] arr) {
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			res ^= arr[i];
		}
		return res;
	}


	// 1、如何不用额外变量交换两个数
	// 前提是 i != j
	public static void change(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}


	public static void main(String[] args) {

		int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
		change(arr, 1, 3);
		System.out.println(Arrays.toString(arr));

		arr = new int[]{1, 1, 2, 3, 4, 3, 2};
		System.out.println(getOdd(arr));

		System.out.println(getRightOne(5));

		System.out.println(isPowerOfTwo(4));
		System.out.println(isPowerOfTwo(3));
		System.out.println(isPowerOfTwo(6));
		System.out.println(isPowerOfTwo(32));

		arr = new int[]{1, 1, 2, 3, 4, 4, 5, 2, 6, 6};
		System.out.println(Arrays.toString(getOddOfTwo(arr)));

		print(3);
	}

}
