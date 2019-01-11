package com.blockchain.test.demo.DataStructure;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BinaryTree {

	public static void main(String[] args) {
		//TreeNode root = new TreeNode("A")
	}

	private static void preOrderTraverse(TreeNode root){
		if (root==null){
			return;
		}
		System.out.println(root);
		preOrderTraverse(root.left);
		preOrderTraverse(root.right);

	}
}

@Data
@AllArgsConstructor
class TreeNode{
	public Object data;
	public TreeNode parent;
	public TreeNode left;
	public TreeNode right;
}
