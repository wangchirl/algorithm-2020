package mine;

import class02_03.Code01_ReverseList;

import java.util.ArrayList;
import java.util.List;

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

	// 6、将单链表按值划分为左边下、中间相等、右边大的形式
	// partition - 边界问题，左边无，中间无，右边无

	// 5、快慢指针问题
	// 5.1 输入链表头结点，奇数长度返回中点，偶数长度返回上中点
	// 5.2 输入链表头结点，奇数长度返回中点，偶数长度返回下中点
	// 5.3 输入链表头结点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	// 5.4 输入链表头结点，奇数长度返回中点前一个，偶数长度返回下中点前一个


	// 4、给定链表头结点，判断链表是否回文结构
	// 4.1 哈希表方式
	// 4.2 修改原链表


	// 3、能不能不给单链表的头节点，只给想要删除的节点，就能做到在链表上把这个点删掉？


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
//
//			DoubleNode node4 = generateRandomDoubleList(len, value);
//			List<Integer> list4 = getDoubleListOriginOrder(node4);
//			node4 = reverseDoubleList(node4);
//			if (!checkDoubleListReverse(list4, node4)) {
//				System.out.println("Oops4!");
//			}

		}
		System.out.println("test finish!");

	}

}
