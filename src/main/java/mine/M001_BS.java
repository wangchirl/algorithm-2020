package mine;

/**
 * @author shadow
 * @create 2020-12-12
 * @description
 */
public class M001_BS {


	// 4、二分查找 区域最小值


	// 3、找到小于指定值的<=value最右位置
	public static int nearRight(int[] arr, int value) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int L = 0;
		int R = arr.length - 1;
		int index = -1;
		while (L <= R) {
			int M = L + ((R - L) >> 1);
			if(arr[M] <= value) { // 大于中值 右边找
				index = M;
				L = M + 1;
			}else { // 小于中值左边找
				R = M - 1;
			}
		}
		return index;
	}


	// 2、找到大于指定值的>=value最左位置
	public static int nearLeft(int[] arr, int value) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int L = 0;
		int R = arr.length - 1;
		int index = -1;
		while (L <= R) {
			int M = L + ((R - L) >> 1);
			if (arr[M] >= value) { // 小于等于中值 左边找
				index = M;
				R = M - 1;
			} else { // 右边找
				L = M + 1;
			}
		}
		return index;
	}


	// 1、二分查找判断是否存在指定值
	public static boolean isExist(int[] arr, int value) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		int L = 0;
		int R = arr.length - 1;
		while (L <= R) {
			int M = L + ((R - L) >> 1);
			if (arr[M] > value) { // 小于中值 在左边找
				R = M - 1;
			} else if (arr[M] < value) { // 大于中值 在右边找
				L = M + 1;
			} else {
				return true;
			}
		}
		return false;
	}


	public static void main(String[] args) {

	}


}
