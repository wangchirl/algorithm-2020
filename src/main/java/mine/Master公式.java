package mine;

/**
 * @author shadow
 * @create 2020-12-06
 * @description
 */
public class Master公式 {

	/**
	 * T(N)=a*T(N/b) +O(N^d)
	 *
	 * log(b,a) < d => O(N^d)
	 * log(b,a) > d => O(N^log(b,a))
	 * log(b,a) = d => O(N^d * logN)
	 */

	/**
	 * 比较器
	 *
	 * Comparator
	 * @Overide
	 * public int compare(T o1,T o2)
	 *
	 * 返回负数的情况，o1 排在 o2 前面
	 * 返回整数的情况，o2 排在 o1 前面
	 * 返回0的情况，o1和o2无所谓谁前
	 */
}
