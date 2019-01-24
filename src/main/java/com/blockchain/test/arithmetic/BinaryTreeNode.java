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
                //将right添加到right
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

        root.setLeftChild(node2);
        root.setRightChild(node3);
        node2.setLeftChild(node4);
        node3.setLeftChild(node5);
        node3.setRightChild(node6);
        node5.setLeftChild(node7);
        node5.setRightChild(node8);

        root.print(root);
    }
}
