package hillel.tree;

public class Main {
    public static void main(String[] args) {
          Tree tree = new Tree();

        tree.addNode(5);
        tree.addNode(2);
        tree.addNode(10);
        tree.addNode(6);
        tree.addNode(12);
        tree.addNode(9);
        tree.addNode(1);
        tree.addNode(15);

        tree.printTree();

        tree.deleteNode(9);
        tree.printTree();

        Node found = tree.findNode(12);
        found.printValueNode();
    }
}
