package mine;

import class07.Code04_SerializeAndReconstructTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author shadow
 * @create 2020-12-06
 * @description
 */
public class Trees {
	public static void main(String[] args) {

	}

	// 16、给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离

	// 15、给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b的最低公共祖先

	// 14、给定一棵二叉树的头节点head，返回这颗二叉树中是不是完全二叉树

	// 13、给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点


	// 12、给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小


	// 11、给定一颗二叉树的头结点，返回这颗二叉树是否搜索二叉树


	// 10、给定一颗二叉树的头结点，返回这颗二叉树是否满二叉树


	// 9、给定一颗二叉树的头结点，返回这颗二叉树是否平衡二叉树


	// 8、折纸问题
	/**
	 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，
	 * 压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
	 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
	 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
	 * 例如:N=1时，打印: down N=2时，打印: down down up
	 */

	// 7、给你二叉树中的某个节点，返回该节点的后继节点

	// 6、求二叉树最宽的层有多少个结点



	// 7、TODO 如何设计一个二叉树的打印


	// 6、TODO 多叉树如何序列化问题
	// 转二叉树 - X 的孩子放在 X的左树右边界
	/**
	 *    a
	 *  b c d
	 *
	 *    a
	 *  b
	 *    c
	 *      d
	 */




	/**
	 * 5、二叉树的序列化与反序列化
	 * 1.先序
	 * 2.后序
	 * 3.按层
	 * 分隔符、占位符
	 * <p>
	 * 1）可以用先序或者中序或者后序或者按层遍历，来实现二叉树的序列化
	 * <p>
	 * 2）用了什么方式序列化，就用什么样的方式反序列化
	 * <p>
	 * 3) 只能通过先序和后序的结果才能进行反序列化
	 * <p>
	 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
	 * 以下代码全部实现了。
	 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
	 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
	 * 比如如下两棵树
	 * __2
	 * /
	 * 1
	 * 和
	 * 1__
	 * \
	 * 2
	 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
	 */
	// 先序序列化
	public static Queue<String> preSerial(Node head) {
		Queue<String> queue = new LinkedList<>();
		preSerial(head, queue);
		return queue;
	}

	private static void preSerial(Node head, Queue<String> queue) {
		if (head == null) {
			queue.add(null);
		} else {
			queue.add(String.valueOf(head.value));
			preSerial(head.left, queue);
			preSerial(head.right, queue);
		}
	}

	// 先序反序列化 - 先序建树
	public static Node preBuildTree(Queue<String> nodes) {
		if (nodes.isEmpty()) {
			return null;
		}
		return preBuild(nodes);
	}

	private static Node preBuild(Queue<String> nodes) {
		String value = nodes.poll();
		if (value == null) {
			return null;
		}
		Node head = new Node(Integer.parseInt(value));
		head.left = preBuild(nodes);
		head.right = preBuild(nodes);
		return head;
	}

	// 后序序列化
	public static Queue<String> postSerial(Node head) {
		if (head == null) {
			return null;
		}
		Queue<String> queue = new LinkedList<>();
		postSerial(head, queue);
		return queue;
	}

	private static void postSerial(Node head, Queue<String> queue) {
		if (head == null) {
			queue.add(null);
		} else {
			postSerial(head.left, queue);
			postSerial(head.right, queue);
			queue.add(String.valueOf(head.value));
		}
	}

	// 后序建树 - 左右中 -stack> 中右左 => 与先序建树 左右先后顺序颠倒一下
	public static Node postBuildTree(Queue<String> nodes) {
		if (nodes.isEmpty()) {
			return null;
		}
		// 左右中  ->  stack(中右左)
		Stack<String> stack = new Stack<>();
		while (!nodes.isEmpty()) {
			stack.push(nodes.poll());
		}
		return postBuild(stack);
	}

	private static Node postBuild(Stack<String> nodes) {
		String value = nodes.pop();
		if (value == null) {
			return null;
		}
		Node head = new Node(Integer.parseInt(value));
		head.right = postBuild(nodes);
		head.left = postBuild(nodes);
		return head;
	}

	// 按层序列化
	public static Queue<String> levelSerial(Node head) {
		Queue<String> queue = new LinkedList<>();
		if (head == null) {
			queue.add(null);
		} else {
			queue.add(String.valueOf(head.value)); // 入队
			Queue<Node> help = new LinkedList<>();
			help.add(head);
			while (!help.isEmpty()) {
				head = help.poll();
				if (head.left != null) {
					queue.add(String.valueOf(head.left.value));
					help.add(head.left);
				} else {
					queue.add(null);
				}
				if (head.right != null) {
					queue.add(String.valueOf(head.right.value));
					help.add(head.right);
				}else {
					queue.add(null);
				}
			}
		}
		return queue;
	}

	// 按层建树
	public static Node buildByLevelQueue(Queue<String> levelList) {
		if (levelList == null || levelList.size() == 0) {
			return null;
		}
		Node head = generateNode(levelList.poll());
		Queue<Node> queue = new LinkedList<>();
		if (head != null) {
			queue.add(head);
		}
		Node node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.left = generateNode(levelList.poll());
			node.right = generateNode(levelList.poll());
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return head;
	}

	public static Node generateNode(String val) {
		if (val == null) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}

	// 4、二叉树的层次遍历 - 队列

	/**
	 * 1）其实就是宽度优先遍历，用队列
	 * <p>
	 * 2）可以通过设置flag变量的方式，来发现某一层的结束（看题目）
	 */
	// 从左到右依次入队
	public static void level(Node head) {
		if (head != null) {
			Queue<Node> queue = new LinkedList<>();
			queue.add(head);
			while (!queue.isEmpty()) {
				head = queue.poll();
				System.out.print(head.value + " ");
				if (head.left != null) {
					queue.add(head.left);
				}
				if (head.right != null) {
					queue.add(head.right);
				}
			}
		}
	}


	// 理解递归序
	public static void recursive(Node head) {
		if (head == null) {
			return;
		}
		// 1 先序
		recursive(head.left);
		// 2 中序
		recursive(head.right);
		// 3 后序
	}


	/**
	 * 先序：任何子树的处理顺序都是，先头节点、再左子树、然后右子树
	 * <p>
	 * 中序：任何子树的处理顺序都是，先左子树、再头节点、然后右子树
	 * <p>
	 * 后序：任何子树的处理顺序都是，先左子树、再右子树、然后头节点
	 * <p>
	 * 1）理解递归序
	 * <p>
	 * 2）先序、中序、后序都可以在递归序的基础上加工出来
	 * <p>
	 * 3）第一次到达一个节点就打印就是先序、第二次打印即中序、第三次即后序
	 * <p>
	 * 1）任何递归函数都可以改成非递归
	 * <p>
	 * 2）自己设计压栈的来实现
	 */
	// 3、二叉树的后序遍历
	// 左->右->头
	public static void post(Node head) {
		if (head == null) {
			return;
		}
		post(head.left);
		post(head.right);
		System.out.print(head.value + " ");
	}

	// 压入顺序先压入左,后压入右，弹出时用另外的栈接收
	public static void postByStack(Node head) {
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			Stack<Node> result = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				result.push(head); // 另外的栈接收
				if (head.left != null) { // 有左先压入左
					stack.push(head.left);
				}
				if (head.right != null) {
					stack.push(head.right);
				}
			}
			// 再弹出
			while (!result.isEmpty()) {
				System.out.print(result.pop().value + " ");
			}
		}
	}

	// 2、二叉树的中序遍历
	// 左->头->右
	public static void in(Node head) {
		if (head == null) {
			return;
		}
		in(head.left);
		System.out.print(head.value + " ");
		in(head.right);
	}

	// 这里先压入全部的左，弹出再压入右，后再压入右的全部的左
	public static void inByStack(Node head) {
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty() || head != null) {
				// 有左则一直压入
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					// 否则弹出打印压入右
					head = stack.pop();
					System.out.print(head.value + " ");
					head = head.right;
				}
			}
		}
	}

	// 1、二叉树的先序遍历
	// ->头 ->左 ->右
	// 递归实现
	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		pre(head.left);
		pre(head.right);
	}

	// 非递归实现 - 借助栈
	public static void preByStack(Node head) {
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				System.out.print(head.value + " ");
				if (head.right != null) { // 有右先压入右
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
	}

	static class Node {
		int value;
		Node left;
		Node right;

		public Node(int v) {
			this.value = v;
		}
	}

}
