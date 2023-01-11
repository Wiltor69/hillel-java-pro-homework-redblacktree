package hillel.tree;

import java.util.Stack;

public class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public Node findNode(int value) {
        Node currentNode = root;
        while (currentNode.getValue() != value) {
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    public void addNode(int value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (root == null) {
            root = newNode;
        } else {
            Node currentNode = root;
            Node parent;
            while (true) {
                parent = currentNode;
                if (value == currentNode.getValue()) {
                    return;
                } else if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeft();
                    if (currentNode == null) {
                        parent.setLeft(newNode);
                        return;
                    }
                } else {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        parent.setRight(newNode);
                        return;
                    }

                }
            }

        }
    }
    public boolean deleteNode (int value){
        Node currentNode = root;
        Node parent = root;
        boolean isLeft = true;
        while (currentNode.getValue() != value){
            parent = currentNode;
            if(value < currentNode.getValue()){
                isLeft = true;
                currentNode = currentNode.getLeft();
            } else {
                isLeft = false;
                currentNode = currentNode.getRight();
            }
            if (currentNode == null)
                return false;
        }
        if (currentNode.getLeft() == null && currentNode.getRight() == null){
            if (currentNode == root)
                root = null;
            else if (isLeft) {
                parent.setLeft(null);
            }
            else
                parent.setRight(null);
        }
        else if (currentNode.getRight()==null) {
            if(currentNode == root)
                root = currentNode.getLeft();
            else if (isLeft) {
                parent.setLeft(currentNode.getLeft());
            }
            else
                parent.setRight(currentNode.getLeft());
        }
        else if (currentNode.getLeft() == null) {
            if (currentNode == root)
                root = currentNode.getRight();
            else if (isLeft) {
                parent.setLeft(currentNode.getRight());
            }
            else
                parent.setRight(currentNode.getRight());
        }
        else {
            Node hier = receive (currentNode);
            if (currentNode == root)
                root = hier;
            else if (isLeft) {
                parent.setLeft(hier);
            }
            else
                parent.setRight(hier);
        }
        return true;
    }

    private Node receive (Node node){
        Node parent = node;
        Node hier = node;
        Node currentNode = node.getRight();
        while (currentNode != null){
            parent = hier;
            hier = currentNode;
            currentNode = currentNode.getLeft();
        }
        if (hier != node.getRight()){
            parent.setLeft(hier.getRight());
            hier.setRight(node.getRight());
            hier.setLeft(node.getLeft());
        }
        return hier;
    }


    public void printTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getValue());
                    localStack.push(temp.getLeft());
                    localStack.push(temp.getRight());
                    if (temp.getLeft() != null ||
                            temp.getRight() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }

}
