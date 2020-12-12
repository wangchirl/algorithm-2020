package mine;


import java.util.Arrays;

/**
 * @author shadow
 * @create 2020-12-06
 * @description
 */
public class Sorts {


	// 10、基数排序
	// 按位入桶：个十百千万...
	// 求出最大的位 digit
	// 从个位开始入桶，依次十位，百位...
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		radixSort(arr, 0, arr.length - 1, maxbits(arr));
	}

	private static void radixSort(int[] arr, int L, int R, int digit) {
		int[] help = new int[R - L + 1];
		// 按位放入
		for (int d = 1; d <= digit; d++) {
			// 每次准备 10 个桶 0...9
			int[] count = new int[10];
			// 个十百千万...依次放入
			for (int i = L; i <= R; i++) {
				int j = getDigit(arr[i], d);
				count[j]++;
			}
			// 统计前面一共多少个
			for (int i = 1; i < count.length; i++) {
				count[i] = count[i] + count[i - 1];
			}
			// 从后依次往前放入辅助数组中
			for (int i = R; i >= L; i--) {
				int j = getDigit(arr[i], d);
				help[count[j] - 1] = arr[i];
				count[j]--;
			}
			// 放回原数组
			for (int i = L, j = 0; i <= R; i++, j++) {
				arr[i] = help[j];
			}
		}
	}

	// 取得（个十百千万...）位上的数
	private static int getDigit(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}

	private static int maxbits(int[] arr) {
		// 先找到最大值
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		// 求 位数
		int res = 0;
		while (max != 0) {
			res++;
			max /= 10;
		}
		return res;
	}


	// 9、计数排序
	// 桶排序，找到最大的值max,创建 max+1个桶，遍历元素
	public static void countSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 找到最大值
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(arr[i], max);
		}
		// 创建 max + 1 个桶
		int[] bucket = new int[max + 1];
		// 统计元素个数
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		// 重新生成 arr
		for (int i = 0, j = 0; j < bucket.length; j++) {
			while (bucket[j]-- > 0) { // 每个桶里面有几个元素依次存入到arr
				arr[i++] = j;
			}
		}
	}


	// 8、堆排序 - heapInsert / heapify
	// 堆结构的特性
	// i位置父节点位置：(i-1)/2
	// i 位置左孩子位置：2*i + 1
	// i 位置右孩子位置：2*i + 2
	// 大顶堆、小顶堆
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 创建大顶堆
		/*for (int i = 0; i < arr.length; i++) {
			heapInsert(arr, i);
		}*/
		// 下沉式创建堆，时间复杂度低于 heapInsert
		for (int i = arr.length - 1; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}
		// 依次将堆顶的元素丢到数组最后
		int heapSize = arr.length;
		swap(arr, 0, --heapSize);
		while (heapSize > 0) {
			heapify(arr, 0, heapSize);
			swap(arr, 0, --heapSize);
		}
	}

	// 插入元素，与父节点进行比较，从头创建大顶堆
	private static void heapInsert(int[] arr, int index) {
		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	// 下沉式创建堆，与子节点比较，
	private static void heapify(int[] arr, int index, int heapSize) {
		int leftIndex = 2 * index + 1;
		while (leftIndex < heapSize) {
			// 两个孩子中，谁的值大，把下标给largest
			// 1）只有左孩子，left -> largest
			// 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
			// 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
			int largest = leftIndex + 1 < heapSize && arr[leftIndex + 1] > arr[leftIndex] ? leftIndex + 1 : leftIndex;
			// 与左右孩子中最大的一个进行比较，谁大取谁
			largest = arr[index] > arr[largest] ? index : largest;
			if (index == largest) {
				break;
			}
			swap(arr, largest, index);
			index = largest;
			leftIndex = 2 * index + 1;
		}
	}


	// 7、快速排序3.0 - 与2.0不一样的地方是选择比较的元素是随机出来的
	// 在2.0基础上，R位置的元素随机选择L...R中的元素，交换到R位置
	public static void quickSort3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process3(arr, 0, arr.length - 1);
	}

	private static void process3(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		// 随机位置放到 R 位置
		swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] M = partition3(arr, L, R);
		process3(arr, L, M[0] - 1);
		process3(arr, M[0] + 1, R);
	}

	private static int[] partition3(int[] arr, int L, int R) {
		if (L > R) {
			return new int[]{-1, -1};
		}
		if (L == R) {
			return new int[]{L, R};
		}
		int leftIndex = L - 1;
		int rightIndex = R;
		int index = L;
		while (index < rightIndex) {
			if (arr[index] < arr[R]) {
				swap(arr, index++, ++leftIndex);
			} else if (arr[index] > arr[R]) {
				swap(arr, index, --rightIndex);
			} else {
				index++;
			}
		}
		swap(arr, rightIndex, R);
		return new int[]{leftIndex + 1, rightIndex};
	}


	// 6、快速排序2.0 - 每次排序好一组一样的元素 - 荷兰国旗划分
	// 经典荷兰国旗划分问题
	// 1.0每次partition只是找到一个元素位置排好序，2.0则是找到同一批相同值的左右边界并排好序
	// 左边界 L-1，右边界 R
	// 小于X时，左扩，大于X时右扩,直到 L == R 为止
	// <X...=X...>X..
	public static void quickSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process2(arr, 0, arr.length - 1);
	}

	private static void process2(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		int[] M = partition2(arr, L, R); // 找到相同值的元素排好序后的左右左边位置
		process2(arr, L, M[0] - 1); // 左边继续
		process2(arr, M[1] + 1, R); // 右边继续
	}

	private static int[] partition2(int[] arr, int L, int R) {
		if (L > R) {
			return new int[]{-1, -1};
		}
		if (L == R) {
			return new int[]{L, R};
		}
		int leftIndex = L - 1;
		int rightIndex = R;
		int index = L;
		while (index < rightIndex) { // 两边同时扩，注意右边界
			if (arr[index] < arr[R]) { // 小于X，左边界往右扩 1 并将当前元素与其交换，位置往前移动
				swap(arr, index++, ++leftIndex);
			} else if (arr[index] > arr[R]) {// 大于X，右边界往左扩1，当前元素与其交换，位置不变，因为交换过来的元素还没有看过
				swap(arr, index, --rightIndex);
			} else {// 相等则只需往前移动
				index++;
			}
		}
		// 处理最后一个元素 R
		swap(arr, rightIndex, R);
		return new int[]{leftIndex + 1, rightIndex};
	}


	// 5、快速排序1.0 - 每次排序好一个元素
	// 区间划分
	// L...R 想有序，先根据给定的元素X进行划分，小于X的放左边，大于X的放右边
	// 左边以 L-1 为界，小于X时，界++ 并交换，index++；否则index++
	// 最后将X进行交换位置
	// 每次划分完后，以X为分割的已整体有序，若X是排序的元素，则找到X的位置已经排好序
	// 1.0版本，每次划分完排好序一个元素
	// <X...>X...
	public static void quickSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process1(arr, 0, arr.length - 1);
	}

	private static void process1(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		int M = partition1(arr, L, R); // 找到中点位置，即排好序的位置
		process1(arr, L, M - 1); // 左边继续
		process1(arr, M + 1, R); //右边继续
	}

	private static int partition1(int[] arr, int L, int R) {
		if (L > R) {
			return -1;
		}
		if (L == R) {
			return L;
		}
		int leftIndex = L - 1; // 左边界
		int index = L;
		while (index < R) {
			if (arr[index] < arr[R]) {// 以最后一个元素为基准进行比较
				swap(arr, index, ++leftIndex); // 当前元素小于则左边界扩 1
			}
			index++;
		}
		// 最后交换R的位置
		swap(arr, ++leftIndex, R);
		return leftIndex; // 基准元素的位置
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


	// 4、归并排序
	// 分治思想
	// L...R 想有序，L...M...R => 划分，直到左右剩下一个元素，那么这一个元素肯定是有序的
	// merge时两两元素进行比较，创建辅助数组，谁小先拷贝谁到辅助数组中
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	private static void process(int[] arr, int L, int R) {
		if (L == R) {
			return;
		}
		int M = L + ((R - L) >> 1); // 找到中点
		process(arr, L, M); // 左边划分
		process(arr, M + 1, R); // 右边划分
		merge(arr, L, M, R); // 合并
	}

	private static void merge(int[] arr, int L, int M, int R) {
		int[] help = new int[R - L + 1]; // 辅助数组
		int index = 0; // 辅助数组的下标
		int l = L;// 数组左边下标
		int r = M + 1; // 数组右边下标
		// 谁小先拷贝谁
		while (l <= M && r <= R) { // 保证不越界，谁小先拷贝谁
			help[index++] = arr[l] < arr[r] ? arr[l++] : arr[r++];
		}
		// 左右某一边越界后直接拷贝剩下的
		while (l <= M) {
			help[index++] = arr[l++];
		}
		while (r <= R) {
			help[index++] = arr[r++];
		}
		// 拷贝回原数组
		for (int i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
	}


	// 3、插入排序
	// 假设 0 的位置是排好序的
	// 从第二个元素开始比较，与前面的排好序的进行比较，从后往前比较，当前元素小则交换
	// 类比打牌
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 从第二个元素开始比较，默认第一个元素有序
		for (int i = 1; i < arr.length; i++) {
			// 依次与有序的元素进行比较，从后往前
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					/*int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;*/
				}
			}
		}
	}


	// 2、冒泡排序
	// 相邻的元素依次比较，大的往后交换
	// 每一轮都会找到一个元素排序好
	// 每一轮则少一次比较
	// 最后一个元素无需比较
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 最后一个元素无需比较
		for (int i = 0; i < arr.length - 1; i++) {
			// 每一轮则少一次 , 第 i 轮 则少比较 i 轮
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) { // 前面的大，则交换
					swap(arr, j, j + 1);
					/*int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;*/
				}
			}
		}
	}


	// 1、选择排序
	// 设定当前要比较的元素为最小下标
	// 依次与下一个后序的元素进行对比，找到更小的则替换下标
	// 全部找完后，将当前位置与最小下标位置进行交换
	public static void selectSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			// 下一个元素开始找
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) { // 比最新的元素还小，则替换最小值下标
					minIndex = j;
				}
			}
			swap(arr, i, minIndex);
			/*int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;*/
		}
	}


	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		// Math.random()   [0,1)
		// Math.random() * N  [0,N)
		// (int)(Math.random() * N)  [0, N-1]
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			// [-? , +?]
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] generateRandomArray1(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
//			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr1 = generateRandomArray1(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			radixSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

//		int[] arr = generateRandomArray(maxSize, maxValue);
		int[] arr = generateRandomArray1(maxSize, maxValue);
		printArray(arr);
		radixSort(arr);
		printArray(arr);
	}


}
