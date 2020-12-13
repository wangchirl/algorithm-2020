package mine;

import class02_03.Code01_ReverseList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shadow
 * @create 2020-12-06
 * @description
 */
public class Links {


	// 8、给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
	//【要求】
	//如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。

	// 7、特殊链表结构，复制链表
	/**
	 * Node {
	 *     int value;
	 *     Node next;
	 *     Node rand;
	 * }
	 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
	 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
	 * 【要求】
	 * 时间复杂度O(N)，额外空间复杂度O(1)
	 */
	// Map 哈希法
	public static SNode copyNode(SNode head) {


		return null;
	}




	static class SNode {
		int value;
		Node next;
		Node rand;
		public SNode(int v) {this.value = v;}
	}

	// 6、将单链表按值划分为左边小、中间相等、右边大的形式
	// partition
	// 6.1 转为数组 - 荷兰国旗划分问题
	// 6.2 三个区，三个头尾结点相连 - 考虑边界问题 左边无，中间无，右边无
	public static Node listPartition1(Node head,int pivot) {
		if(head == null) {
			return head;
		}
		Node cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		// 转数组
		Node[] help = new Node[i];
		cur = head;
		for (int j = 0; j < help.length; j++) {
			help[j] = cur;
			cur = cur.next;
		}
		// 荷兰国旗划分
		partition(help, pivot);
		// 再将链表连接起来
		for (int j = 1; j < help.length; j++) {
			help[j-1].next = help[j];
		}
		// 尾部指向null
		help[help.length - 1].next = null;
		// 返回链表头部
		return help[0];
	}

	private static void partition(Node[] arr, int pivot) {
		int L = -1;
		int R = arr.length;
		int index = 0;
		while (index < R) {
			if(arr[index].value > pivot) { // 右扩
				swap(arr,index,--R);
			}else if(arr[index].value < pivot) { // 左扩
				swap(arr,index++,++L);
			}else {
				index++;
			}
		}
	}

	private static void swap(Node[] arr,int i,int j) {
		Node temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


	// 三区域划分
	public static Node listPartition2(Node head,int pivot) {
		// 定义三个区域的指针
		Node sH = null;
		Node sT = null;
		Node eH = null;
		Node eT = null;
		Node bH = null;
		Node bT = null;
		Node next = null;
		while (head != null) {
			// 先记录head的next
			next = head.next;
			// 将结点的next值置空
			head.next = null;
			// 比较判断
			if(head.value < pivot) { // 小于区域
				if(sH == null){
					sH = head;
					sT = head;
				}else {
					sT.next = head; // 尾部追加 , 尾部后移
					sT = head;
				}
			}else if(head.value == pivot) { // 等于区域
				if(eH == null) {
					eH = head;
					eT = head;
				}else{
					eT.next = head;
					eT = head;
				}
			}else { // 大于区域
				if(bH == null) {
					bH = head;
					bT = head;
				}else {
					bT.next = head;
					bT = head;
				}
			}
			head = next; // 下一个元素
		}
		// 小于区域的尾连接等于区域的头，等于区域的尾连接大于区域的头
		if(sT != null) {
			sT.next = eH; // 小尾接中头
			eT = eT == null ? sT : eT; // 中尾为空则用小尾顶上
		}
		if(eT != null) {
			eT.next = bH;
		}
		return sH != null ? sH : (eH != null ? eH : bH);
	}


	// 5、快慢指针问题
	// 5.1 输入链表头结点，奇数长度返回中点，偶数长度返回上中点
	// 5.2 输入链表头结点，奇数长度返回中点，偶数长度返回下中点
	// 5.3 输入链表头结点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	// 5.4 输入链表头结点，奇数长度返回中点前一个，偶数长度返回下中点前一个

	// (node.size() - 1) / 2
	public static Node midOrUpMidNode(Node head) {
		// 2个结点内都是返回头结点
		if(head == null || head.next == null || head.next.next == null) {
			return head;
		}
		// 三个结点即以上
		Node slow = head.next; // 指向当前结点的上一个结点
		Node fast = head.next.next; // 指向当前结点
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next; // 慢指针一次走一个，快指针一次走2个，要保证不为null
			fast = fast.next.next;
		}
		return slow;
	}

	// node.size() / 2
	public static Node midOrDownMidNode(Node head) {
		// 一个以内就是自己
		if(head == null || head.next == null) {
			return head;
		}
		// 两个即以上
		Node slow = head.next; // 指向当前结点
		Node fast = head.next; // 指向当前结点
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// (node.size() - 3) / 2
	public static Node midOrUpMidPreNode(Node head) {
		// 2个结点内都是自己
		if(head == null || head.next == null || head.next.next == null) {
			return head;
		}
		Node slow = head; // 指向上一个结点
		Node fast = head.next.next; // 指向当前结点下一个节点
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// (node.size() - 2) / 2
	public static Node midOrDownMidPreNode(Node head) {
		// 一个结点内 指向自己
		if(head == null || head.next == null) {
			return head;
		}
		Node slow = head; // 指向前一个结点
		Node fast = head.next; // 指向当前结点
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}






	// 4、给定链表头结点，判断链表是否回文结构
	// 4.1 栈方式
	// 4.2 修改原链表
	public static boolean isPalindromeList1(Node head) {
		Stack<Node> stack = new Stack<>();
		Node cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		while (head != null) {
			if(head.value != stack.pop().value){
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// Node1 -> Node2 -> Node3 -> Node4
	// 折半入栈  Node3 -> Node4
	public static boolean isPalindromeList2(Node head) {
		if(head == null || head.next == null){
			return true;
		}
		// 快慢指针，快速找到中点位置
		Node right = head.next;
		Node cur = head;
		while (cur.next != null && cur.next.next != null){
			right = right.next;
			cur = cur.next.next;
		}
		Stack<Node> stack = new Stack<>();
		while (right != null){
			stack.push(right);
			right = right.next;
		}
		while (!stack.isEmpty()) {
			if(stack.pop().value != head.value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// Node1 -> Node2 -> Node3 -> Node4
	// Node1 -> Node2 -> null
	// 			Node2 <- Node3 <- Node4
	// TODO 改变原链表 - 记得最后要改回来
	public static boolean isPalindromeList3(Node head) {
		return false;
	}

	// 3、能不能不给单链表的头节点，只给想要删除的节点，就能做到在链表上把这个点删掉？
	public static void deleteSelf(Node node) {
		if(node == null || node.next == null) {
			return;
		}
		node.value = node.next.value;
		node.next = node.next.next;
	}


	// 2、删除给定的值
	// 考虑头结点的问题，删除到不是给定值的位置
	public static void deleteGiveValue(Node head,int V) {
		// 找到第一个不是 V 的结点
		while (head != null) {
			if(head.value != V){
				break;
			}
			head = head.next;
		}
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if(cur.value == V){ // 直接跳到下一个节点
				pre.next = cur.next;
			}else {
				pre = cur;
			}
			cur = cur.next;
		}
	}



	// 1、单链表和双链表反转
	public static Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static DoubleNode reverseDoubleList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.pre = next;
			pre = head;
			head = next;
		}
		return pre;
	}


	static class Node {
		int value;
		Node next;
		public Node(int data) {this.value = data;}
	}
	
	static class DoubleNode {
		int value;
		DoubleNode pre;
		DoubleNode next;
		public DoubleNode(int value){this.value = value;}
	}

	// 集合实现链表反转
	public static Node testReverseLinkedList(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		list.get(0).next = null;
		int N = list.size();
		for (int i = 1; i < N; i++) {
			list.get(i).next = list.get(i - 1);
		}
		return list.get(N - 1);
	}

	// for test
	public static Node generateRandomLinkedList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		Node head = new Node((int) (Math.random() * (value + 1)));
		Node pre = head;
		while (size != 0) {
			Node cur = new Node((int) (Math.random() * (value + 1)));
			pre.next = cur;
			pre = cur;
			size--;
		}
		return head;
	}

	// for test
	public static DoubleNode generateRandomDoubleList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
		DoubleNode pre = head;
		while (size != 0) {
			DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
			pre.next = cur;
			cur.pre = pre;
			pre = cur;
			size--;
		}
		return head;
	}

	// for test
	public static List<Integer> getLinkedListOriginOrder(Node head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.value);
			head = head.next;
		}
		return ans;
	}

	// for test
	public static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
		for (int i = origin.size() - 1; i >= 0; i--) {
			if (!origin.get(i).equals(head.value)) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// for test
	public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.value);
			head = head.next;
		}
		return ans;
	}

	// for test
	public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
		DoubleNode end = null;
		for (int i = origin.size() - 1; i >= 0; i--) {
			if (!origin.get(i).equals(head.value)) {
				return false;
			}
			end = head;
			head = head.next;
		}
		for (int i = 0; i < origin.size(); i++) {
			if (!origin.get(i).equals(end.value)) {
				return false;
			}
			end = end.pre;
		}
		return true;
	}

	// for test
	public static void main(String[] args) {
		int len = 50;
		int value = 100;
		int testTime = 100000;
		System.out.println("test begin!");
		for (int i = 0; i < testTime; i++) {
			Node node1 = generateRandomLinkedList(len, value);
			List<Integer> list1 = getLinkedListOriginOrder(node1);
			node1 = reverseList(node1);
			if (!checkLinkedListReverse(list1, node1)) {
				System.out.println("Oops1!");
			}

			Node node2 = generateRandomLinkedList(len, value);
			List<Integer> list2 = getLinkedListOriginOrder(node2);
			node2 = testReverseLinkedList(node2);
			if (!checkLinkedListReverse(list2, node2)) {
				System.out.println("Oops2!");
			}

			DoubleNode node3 = generateRandomDoubleList(len, value);
			List<Integer> list3 = getDoubleListOriginOrder(node3);
			node3 = reverseDoubleList(node3);
			if (!checkDoubleListReverse(list3, node3)) {
				System.out.println("Oops3!");
			}

			DoubleNode node4 = generateRandomDoubleList(len, value);
			List<Integer> list4 = getDoubleListOriginOrder(node4);
			node4 = reverseDoubleList(node4);
			if (!checkDoubleListReverse(list4, node4)) {
				System.out.println("Oops4!");
			}

		}
		System.out.println("test finish!");



		Node head = new Node(9);
		head.next = new Node(11);
		head.next.next = new Node(2);
		head.next.next.next = new Node(4);
		Node node = head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(1);

		deleteGiveValue(head,4);
		deleteSelf(node);
		Node cur = head;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}

		System.out.println();

		cur = head;
//		cur = listPartition1(cur,9);
		cur = listPartition2(cur,9);
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}


	}

}
