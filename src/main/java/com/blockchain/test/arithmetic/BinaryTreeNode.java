package com.blockchain.test.arithmetic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

@AllArgsConstructor
@Data
public class BinaryTreeNode {
	private int value;
	private BinaryTreeNode leftChild;
	private BinaryTreeNode rightChild;


	public void print(BinaryTreeNode root) {
		LinkedList<BinaryTreeNode> queue = new LinkedList<>();
		queue.add(root);
		BinaryTreeNode last = root;
		BinaryTreeNode nlast = root;
		while (!queue.isEmpty()) {
			BinaryTreeNode node = queue.poll();
			if (node.getLeftChild() != null) {
				//将left添加到queue
				queue.offer(node.getLeftChild());
				nlast = queue.getLast();
			}
			if (node.getRightChild() != null) {
				//将right添加到queue
				queue.offer(node.getRightChild());
				nlast = queue.getLast();
			}
			if (node == last) {
				System.out.print(node.getValue() + "\n");
				last = nlast;
			} else {
				System.out.print(node.getValue() + " ");
			}
		}
	}


	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(1, null, null);
		BinaryTreeNode node2 = new BinaryTreeNode(2, null, null);
		BinaryTreeNode node3 = new BinaryTreeNode(3, null, null);
		BinaryTreeNode node4 = new BinaryTreeNode(4, null, null);
		BinaryTreeNode node5 = new BinaryTreeNode(5, null, null);
		BinaryTreeNode node6 = new BinaryTreeNode(6, null, null);
		BinaryTreeNode node7 = new BinaryTreeNode(7, null, null);
		BinaryTreeNode node8 = new BinaryTreeNode(8, null, null);
		BinaryTreeNode node9 = new BinaryTreeNode(9, null, null);
		BinaryTreeNode node10 = new BinaryTreeNode(10, null, null);

		root.setLeftChild(node2);
		root.setRightChild(node3);
		node2.setLeftChild(node4);
		node3.setLeftChild(node5);
		node3.setRightChild(node6);
		node5.setLeftChild(node7);
		node5.setRightChild(node8);
		node4.setLeftChild(node9);
		node4.setRightChild(node10);
		//root.print(root);
		theFirstTraversal(root);
		System.out.println("------");
		theInOrderTraversal(root);
		System.out.println("------");
		thePostOrderTraversal(root);
		System.out.println("------");

		floorTraversal(root);
		System.out.println("-----ceng");
		cengxu(root);
	}

	public static void cengxu(BinaryTreeNode root) {
		LinkedList<BinaryTreeNode> queue = new LinkedList<>();
		queue.offer(root);
		BinaryTreeNode qLast = root;
		BinaryTreeNode last = root;
		while (!queue.isEmpty()) {
			BinaryTreeNode node = queue.poll();
			if (node.getLeftChild() != null) {
				queue.offer(node.getLeftChild());
				last = node.getLeftChild();
			}
			if (node.getRightChild() != null) {
				queue.offer(node.getRightChild());
				last = node.getRightChild();
			}
			if (node == qLast) {
				System.out.println(node.getValue());
				qLast = last;
			} else {
				System.out.print(node.getValue() + "--");
			}
		}
	}


	//前序遍历
	public static void theFirstTraversal(BinaryTreeNode root) {
		System.out.print(root.getValue());
		if (root.getLeftChild() != null) {
			//System.out.print(root.getLeftChild().getValue());
			theFirstTraversal(root.getLeftChild());
		}
		if (root.getRightChild() != null) {
			//System.out.print(root.getRightChild().getValue());
			theFirstTraversal(root.getRightChild());
		}
	}

	//中序遍历
	public static void theInOrderTraversal(BinaryTreeNode root) {
		if (root.getLeftChild() != null) {
			//System.out.print(root.getLeftChild().getValue());
			theInOrderTraversal(root.getLeftChild());
		}
		System.out.print(root.getValue());
		if (root.getRightChild() != null) {
			//System.out.print(root.getRightChild().getValue());
			theInOrderTraversal(root.getRightChild());
		}
	}

	//后序遍历
	public static void thePostOrderTraversal(BinaryTreeNode root) {
		if (root.getLeftChild() != null) {
			//System.out.print(root.getLeftChild().getValue());
			thePostOrderTraversal(root.getLeftChild());
		}
		if (root.getRightChild() != null) {
			//System.out.print(root.getRightChild().getValue());
			thePostOrderTraversal(root.getRightChild());
		}
		System.out.print(root.getValue());
	}

	public static void floorTraversal(BinaryTreeNode root) {
		LinkedList<BinaryTreeNode> queue = new LinkedList<>();
		queue.offer(root);
		BinaryTreeNode queueLast = root;
		BinaryTreeNode last = root;
		while (!queue.isEmpty()) {
			BinaryTreeNode node = queue.poll();
			if (node.getLeftChild() != null) {
				queue.offer(node.getLeftChild());
				last = node.getLeftChild();
			}
			if (node.getRightChild() != null) {
				queue.offer(node.getRightChild());
				last = node.getRightChild();
			}
			if (node == queueLast) {
				System.out.println(node.getValue());
				queueLast = last;
			} else {
				System.out.print(node.getValue() + "--");
			}
		}
	}

}
