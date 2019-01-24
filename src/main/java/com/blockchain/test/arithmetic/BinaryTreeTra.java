package com.blockchain.test.arithmetic;

import lombok.Data;

import java.util.LinkedList;

public class BinaryTreeTra {
    public Node init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
        Node J = new Node(8, null, null);
        Node H = new Node(4, null, null);
        Node G = new Node(2, null, null);
        Node F = new Node(7, null, J);
        Node E = new Node(5, H, null);
        Node D = new Node(1, null, G);
        Node C = new Node(9, F, null);
        Node B = new Node(3, D, E);
        Node A = new Node(6, B, C);
        return A;   //返回根节点
    }

    public void printNode(Node node) {
        System.out.print(node.getData());
    }

    /**
     * 先序遍历
     * @param root
     */
    public void theFirstTraversal(Node root) {
        printNode(root);
        if (root.getLeftNode() != null) {  //使用递归进行遍历左孩子
            theFirstTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null) {  //递归遍历右孩子
            theFirstTraversal(root.getRightNode());
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void theInOrderTraversal(Node root) {
        if (root.getLeftNode() != null) {
            theInOrderTraversal(root.getLeftNode());
        }
        printNode(root);
        if (root.getRightNode() != null) {
            theInOrderTraversal(root.getRightNode());
        }
    }


    /**
     * 后序遍历
     * @param root
     */
    public void thePostOrderTraversal(Node root) {
        if (root.getLeftNode() != null) {
            thePostOrderTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null) {
            thePostOrderTraversal(root.getRightNode());
        }
        printNode(root);
    }

    /**
     * 层次遍历二叉树（非递归）
     */
    public static void PrintBinaryTreeLayerUnrecur(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        Node p;
        queue.push(root);
        while (!queue.isEmpty()) {
            p = queue.removeFirst();
            System.out.print(p.data);
            if (p.leftNode != null) {
                queue.addLast(p.leftNode);
            }
            if (p.rightNode != null) {
                queue.addLast(p.rightNode);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeTra tree = new BinaryTreeTra();
        Node root = tree.init();
        PrintBinaryTreeLayerUnrecur(root);
        System.out.println();
        System.out.println("----------");
        System.out.println("先序遍历");
        tree.theFirstTraversal(root);
        System.out.println("");
        System.out.println("中序遍历");
        tree.theInOrderTraversal(root);
        System.out.println("");
        System.out.println("后序遍历");
        tree.thePostOrderTraversal(root);
        System.out.println("");
    }
}

@Data
class Node {
    public int data;
    public Node leftNode;
    public Node rightNode;

    public Node(int data, Node leftNode, Node rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}