package com.github.situx.aoc2017.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Solution to Advent Of Code 2017 Day 7.
 * @author situx
 */
public class Task7 {

	/**
	 * Parses the tree from the given text file line by line.
	 * @param lines the lines from the text file
	 * @return the tree as a TreeMap of nodename to TreeElement
	 */
	public Map<String,TreeElem> parseTree(String[] lines){
		Map<String,TreeElem> treemap=new TreeMap<>();
		String name;
		Integer weight;
		for(String line:lines){
			name=line.substring(0, line.indexOf(' '));
			weight=Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			if(!treemap.containsKey(name)){
				treemap.put(name, new TreeElem(name));
			}
			TreeElem curtreeElem=treemap.get(name);
			curtreeElem.setWeight(weight);			
			if(!line.endsWith(")")){
				for(String namee:line.substring(line.lastIndexOf(">")+2).split(",\\s")){
					if(!treemap.containsKey(namee)){
						treemap.put(namee, new TreeElem(namee));
					}
					curtreeElem.getParents().add(treemap.get(namee));
					//treemap.get(namee).getChildren().add(curtreeElem);
					treemap.get(namee).setHasChildren(true);
					treemap.get(namee).setIsParent(true);
				}
			}
		}
		return treemap;
	}
	
	/**
	 * Finds the root of a given tree.
	 * @param treemap the treemap representing the tree
	 * @return the node name as String
	 */
	public String findRootOfTree(Map<String,TreeElem> treemap){
		for(TreeElem elem:treemap.values()){
			if(!elem.getIsParent()){
				return elem.getName();
			}
		}
		return "";
	}
	
	/**
	 * Calculates the node weights according to the weights of their given subnodes and their own weights.
	 * @param treemap the map containing the tree
	 * @param treeroot the root name of the rootnode of the tree
	 * @param level the current level of the tree for debugging purposes
	 * @return the node weight at the current level
	 */
	public Integer calculateNodeWeights(Map<String,TreeElem> treemap,String treeroot,Integer level){
		TreeElem rootnode=treemap.get(treeroot);
		Integer weight=0;
		List<Integer> childweights=new LinkedList<Integer>();
		for(TreeElem elem:rootnode.getParents()){
			childweights.add(calculateNodeWeights(treemap, elem.getName(),level+1));
		}
		childweights.add(rootnode.getWeight());
		for(Integer w:childweights){
			weight+=w;
		}
		rootnode.setWeightSum(weight);
		return weight;
	}
	
	/**
	 * Finds an unbalanced node in an already weight-appended tree and suggests how the node weight can be corrected to balance out the tree.
	 * @param treemap the tree as treemap
	 * @param treeroot the root node of the tree
	 * @param weightdiff the weightdifference of the children of the current tree node
	 * @return the value needed to correct the balance of the tree
	 */
	public Integer findUnbalancedNodeAndReturnCorrectWeight(Map<String,TreeElem> treemap,TreeElem treeroot,Integer weightdiff){
		Map<Integer,Integer> countmap=new TreeMap<Integer,Integer>();
		Map<Integer,TreeElem> elemmap=new TreeMap<Integer,TreeElem>();
		for(TreeElem elem:treeroot.getParents()){
			if(elem.getWeightSum()!=elem.getWeight()){
				if(!countmap.containsKey(elem.getWeightSum())){
					countmap.put(elem.getWeightSum(), 0);
				}
				countmap.put(elem.getWeightSum(), countmap.get(elem.getWeightSum())+1);
				elemmap.put(elem.getWeightSum(), elem);
			}
		}
		Integer min=Integer.MAX_VALUE;
		Integer minKey=0;
		Integer max=0;
		Integer maxKey=0;
		for(Integer key:countmap.keySet()){
			if(min>countmap.get(key)){
				min=countmap.get(key);
				minKey=key;
			}
			if(max<countmap.get(key)){
				max=countmap.get(key);
				maxKey=key;
			}
		}
		if(countmap.size()==1){
			return treeroot.getWeight()-weightdiff;
		}
		return findUnbalancedNodeAndReturnCorrectWeight(treemap, elemmap.get(minKey),minKey-maxKey);
	}
	
	
    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task7 task7=new Task7();
        String[] intArray= new String(Files.readAllBytes(Paths.get("aoc2017files/task7_2017.txt")))
        		.split(System.lineSeparator());
        Map<String,TreeElem> tree=task7.parseTree(intArray);
        String root=task7.findRootOfTree(tree);
        System.out.println("Root of tree: "+root);
        task7.calculateNodeWeights(tree, root,0);
        System.out.println("The corresponding node needs to be changed to value: "
        +task7.findUnbalancedNodeAndReturnCorrectWeight(tree, tree.get(root),tree.get(root).getWeight()));
    }
	
}
