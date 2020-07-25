package model;

public class TreeItem<T extends Comparable<T>> {

    //Значение в узле
    private T value;

    //Ссылка на правый элемент
    private TreeItem<T> right;
    //Ссылка на левый элемент
    private TreeItem<T> left;



    //Направление
    private int direction;

    private int level;

    //Высота
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TreeItem(T value, int level) {
        this.value = value;
        this.right = null;
        this.left = null;
        this.level = level + 1;
        this.direction = 1;
        this.height = 0;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeItem<T> getRight() {
        return right;
    }

    public void setRight(TreeItem<T> right) {
        this.right = right;
    }

    public TreeItem<T> getLeft() {
        return left;
    }

    public void setLeft(TreeItem<T> left) {
        this.left = left;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString(){
        return (value != null)? value.toString() /*+ "(" + level + ")"*/ :"Пусто";
    }

    public void changeDirection(){
        // direction *= -1;
    }

    public int getDirection(T value) {

        return direction;
    }





}
