package com.github.situx.aoc2017.day7;

import java.util.Set;
import java.util.TreeSet;

/**
 * A tree element to represent a node in a tree.
 * @author situx
 *
 */
public class TreeElem implements Comparable<TreeElem> {
	/**
	 * The weight of the tree node.
	 */
	private Integer weight;
	/**
	 * The node of the tree node.
	 */
	private String name;
	/**
	 * The parents of the tree node.
	 */
	private Set<TreeElem> parents;
	/**
	 * Indicates if the node is a parent node.
	 */
	private Boolean isParent;
	/**
	 * Indicates if the node has childNodes.
	 */
	private Boolean hasChildren;
	/**
	 * The weight sum of all the nodes children and its own weight.
	 */
	private Integer weightSum;
	
	public Boolean getIsParent() {
		return isParent;
	}

	@Override
	public String toString() {
		return "TreeElem [weight=" + weight + ", name=" + name + ", parents=" + parents
				+ ", isParent=" + isParent + "]";
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public TreeElem(String name){
		this.name=name;
		this.parents=new TreeSet<TreeElem>();
		this.isParent=false;
		this.weightSum=0;
		this.hasChildren=false;
	}
	
	public Boolean getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TreeElem> getParents() {
		return parents;
	}

	public void setParents(Set<TreeElem> parents) {
		this.parents = parents;
	}

	public Integer getWeightSum() {
		return weightSum;
	}

	public void setWeightSum(Integer weightSum) {
		this.weightSum = weightSum;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TreeElem){
			return ((TreeElem)obj).name.equals(name);
		}
		return false;
		
	}

	@Override
	public int compareTo(TreeElem o) {
		return this.name.compareTo(o.name);
	}
	
}
