package model;


import java.util.ArrayList;
import java.util.List;

public class MyTree<T extends Comparable<T>> {

    private TreeItem<T> root;
    private int elementsCount;

    public MyTree() {
        root = null;
        elementsCount = 0;
    }

    public TreeItem<T> add(T value){
        TreeItem<T> newItem;
        elementsCount++;
        if (root == null){
            newItem = new TreeItem<>(value, -1);
            root = newItem;
            return newItem;
        }else{
            TreeItem<T> parentTreeItem = findParent(root, value);
            newItem = new TreeItem<>(value, parentTreeItem.getLevel());
            int direction = parentTreeItem.getDirection(value);
            if (direction > 0)
                parentTreeItem.setRight(newItem);
            else if (direction < 0)
                parentTreeItem.setLeft(newItem);
            parentTreeItem.changeDirection();
            return parentTreeItem;
        }
    }




    private TreeItem<T> findParent(TreeItem<T> currentElement, T value) {
        int direction = currentElement.getDirection(value);
        TreeItem<T> nextItem = (direction > 0)? currentElement.getRight() : (direction < 0)? currentElement.getLeft() : null;
        if (nextItem != null) currentElement.changeDirection();
        return (nextItem == null)? currentElement : findParent(nextItem, value);
    }

    public  void printTree(){
        printTreeElement(root);
    }

    private void printTreeElement(TreeItem<T> curItem) {
        if (curItem == null)return;
        System.out.println(curItem.toString());
        printTreeElement(curItem.getRight());
        printTreeElement(curItem.getLeft());
    }

    public int getElementsCount() {
        return elementsCount;
    }



    private Object[] printElem(TreeItem<T> treeItem){
        List<String> result = new ArrayList<>();
        if (treeItem.getRight() !=null){
            Object[]  temp = printElem(treeItem.getRight());
            for (int i = 0; i < temp.length; i++){
                result.add("   " + temp[i]);
            }
        }
        result.add(treeItem.toString());
        if (treeItem.getLeft() !=null){
            Object[]  temp = printElem(treeItem.getLeft());
            for (int i = 0; i < temp.length; i++){
                result.add("   " + temp[i]);
            }
        }
        return result.toArray();
    }

    public Object[] outTree(){
        return (root != null)? printElem(root) : new String[]{"Дерево пустое"};
    }


    private TreeItem<T> addNewNode(TreeItem<T> node, T value){
        if (node == null) return new TreeItem<>(value, -1);
        int direction = value.compareTo(node.getValue());
        if (direction > 0){
            node.setRight( addNewNode(node.getRight(), value) );
        } else if (direction < 0 ){
            node.setLeft(  addNewNode(node.getLeft(), value));
        }

        return balance(node);
    }

    public void put(T value){
        this.root = addNewNode(root, value);
    }


    private TreeItem<T> l_rotate(TreeItem<T> node){
        TreeItem<T> tempNode = node.getRight();
        node.setRight(tempNode.getLeft());
        tempNode.setLeft(node);

        correctHeight(node);
        correctHeight(tempNode);

        return tempNode;
    }

    private TreeItem<T> r_rotate(TreeItem<T> node){
        TreeItem<T> tempNode = node.getLeft();
        node.setLeft(tempNode.getRight());
        tempNode.setRight(node);

        correctHeight(node);
        correctHeight(tempNode);

        return tempNode;
    }


    private void correctHeight(TreeItem<T> tempNode) {
        int heightLeft = getNodeHeight(tempNode.getLeft());
        int heightRight = getNodeHeight(tempNode.getRight());
        tempNode.setHeight(Math.max(heightLeft, heightRight) + 1);
    }

    private int getNodeHeight(TreeItem<T> node) {
        return (node == null)? 0 : node.getHeight();
    }

    private int  bFactor(TreeItem<T> node){
        return getNodeHeight(node.getRight()) - getNodeHeight(node.getLeft());
    }

    private TreeItem<T> balance(TreeItem<T> node){
        correctHeight(node);
        if (bFactor(node) == 2){
            if (bFactor(node.getRight()) < 0){
                node.setRight( r_rotate(node.getRight()) );
            }
            return l_rotate(node);
        }else if (bFactor(node) == -2){
            if (bFactor(node.getLeft()) > 0){
                node.setLeft( l_rotate(node.getLeft()) );
            }
            return r_rotate(node);
        }
        return node;
    }

}
