package model;

public class TreeApplication {
    public static void main(String[] args) {

        MyTree <Integer> myTree = new MyTree<>();
        for(int i=0; i<7; i++){
            //myTree.add(i);
            myTree.put(i);
        }
       //myTree.printTree();
        System.out.println("***************");

        for (Object o: myTree.outTree()) {
            System.out.println(o);
        }
    }

}
