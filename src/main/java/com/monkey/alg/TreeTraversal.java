package com.monkey.alg;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal {

    public static void main(String[] args) {

        TreeNode root = new TreeNode("F");

        root.left = new TreeNode("B");
        root.right = new TreeNode("G");
        root.left.left = new TreeNode("A");
        root.left.right = new TreeNode("D");
        root.left.right.left = new TreeNode("C");
        root.left.right.right = new TreeNode("E");
        root.right.right= new TreeNode("I");
        root.right.right.left = new TreeNode("H");
        System.out.println("前序");
        pre(root);
        System.out.println();
        System.out.println("中序");
        middle(root);
        System.out.println();
        System.out.println("后序");
        after(root);
        System.out.println();
        System.out.println("层序");
        layer(root);
    }

    private static void pre(TreeNode root){
        if(root!=null){
            System.out.print(root.getNodeName());
            pre(root.left);
            pre(root.right);
        }

    }

    private static void after(TreeNode root){
        if(root!=null){
            pre(root.left);
            pre(root.right);
            System.out.print(root.getNodeName());
        }


    }

    private static void middle(TreeNode root){
        if(root!=null){
            pre(root.left);
            System.out.print(root.getNodeName());
            pre(root.right);
        }

    }
    private static void layer(TreeNode root){
        if(root==null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode t = queue.poll();
            System.out.print(t.getNodeName());
            if(t.left!=null){
                queue.add(t.left);
            }
            if(t.right!=null){
                queue.add(t.right);
            }
        }

    }




    private static class  TreeNode{

        private String nodeName;

        private TreeNode left;

        private TreeNode right;

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public TreeNode(String nodeName) {
            this.nodeName = nodeName;
        }
    }


}
