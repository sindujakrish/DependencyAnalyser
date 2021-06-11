package com.zealousdev;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class DependencyAnalyser {
	
	Map<String, TreeNode> nodeMap = new TreeMap<>();
	
	public void readInput(String[] input) throws CircularDependencyException {
		for(String line: input) {
			addDependencies(line.split(" "));
		}
	}
	
	private void addDependencies(String[] tokens) throws CircularDependencyException {
		String parent = null;
		TreeNode currentNode = null;
		for(String token: tokens) {
			if(parent==null) {
				parent = token;
				currentNode = addNodeToMap(parent);
			} else {
				currentNode.addChild(addNodeToMap(token));
			}
		}
	}
	
	public TreeNode addNodeToMap(String token) {
		if (nodeMap.containsKey(token)) {
			return nodeMap.get(token);
		}
		TreeNode currentNode = new TreeNode(token);
		nodeMap.put(token, currentNode);
		return currentNode;
	}
	
	public void printAlldependencies() {
		for (String value : nodeMap.keySet()) {
            if(nodeMap.get(value).getDependencyTree().size() >0) {
            	StringBuilder output = new StringBuilder(value);
            	output.append("  ");
            	for(TreeNode childNode: nodeMap.get(value).getDependencyTree()) {
            		output.append(childNode.getValue()+ " ");
            	}
            	System.out.println(output.toString().trim());
            }
        }
	}
	
	public String getDependenciesFor(String token) {
		TreeNode node = nodeMap.get(token);
		if(node != null && node.getDependencyTree().size() >0) {
			return node.getDependencyTree().stream().map(it -> it.getValue()).collect(Collectors.joining(" "));
		}
		return "";
	}

}
