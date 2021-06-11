package com.zealousdev;

import java.util.Set;
import java.util.TreeSet;


public class TreeNode implements Comparable<TreeNode> {
	
	private String value;
	private Set<TreeNode> children = new TreeSet<TreeNode>();
	
	public TreeNode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void addChild(TreeNode child) throws CircularDependencyException {
		if(child.getDependencyTree().contains(this)) {
			throw new CircularDependencyException();
		} 
		children.add(child);
	}
	
	public Set<TreeNode> getDependencyTree() {
        Set<TreeNode> dependencyTree = new TreeSet<>();
        for (TreeNode child : children) {
        	dependencyTree.add(child);
        	dependencyTree.addAll(child.getDependencyTree());
        }
        return dependencyTree;
    }
	
	@Override
    public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TreeNode node = (TreeNode) o;
		return value != null ? value.equals(node.value) : node.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    } 
    
    @Override
    public int compareTo(TreeNode o) {
        return value.compareTo(o.value);
    }
}
