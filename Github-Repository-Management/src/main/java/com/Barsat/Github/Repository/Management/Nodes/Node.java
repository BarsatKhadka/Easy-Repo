package com.Barsat.Github.Repository.Management.Nodes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Node {
    public String name;
    public boolean isDirectory;
    public Node parent;
    public List<Node> children = new ArrayList<>();

    public Node(String name , Node parent , boolean isDirectory) {
        this.name = name;
        this.parent = parent;
        this.isDirectory = isDirectory;
    }

    public void addChildrenToParent(Node children){
        this.children.add(children);

    }


    //recursion to get any node that is inside list of list.
    public Node accessAnyNode(String name){
        if(name.equals(this.name)){
            return this;
        }

        for(Node child : children){
            Node theNode = child.accessAnyNode(name);
            if (theNode != null) {
                return theNode;
            }

        }
        return null;
    }


    // Helper method  generate a string representation of the tree structure
    public String toStringHelper(Node node, int level) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++) {
            sb.append("  ");  // Two spaces per level of depth
        }


        sb.append(node.getName()).append("\n");
//        sb.append(node.isDirectory);



        for (Node child : node.getChildren()) {
            sb.append(toStringHelper(child, level + 1));  // Increase the level for child nodes
        }

        return sb.toString();
    }


}
