/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dfs;
import java.util.*;  
import javafx.util.Pair; 
import java.util.Random;

/**
 *
 * @author DELL
 */
public class Dfs {
    private boolean once=false;
    private int rootNode;
    static int visitNum=0;
    Set<Integer> ansestors = new HashSet<Integer>();
    private int [][] originGraph;
    private int [][] treeEdgesGraph;
    private int status [];
    private int visitTime [];
    public int parents [];
    private List<Pair<Integer, Integer>> treeEdges=new ArrayList<Pair<Integer, Integer>>();
    private List<Pair<Integer, Integer>> backwardEdges=new ArrayList<Pair<Integer, Integer>>();
    public Dfs(int[][] arr){
            this.originGraph=arr;
            Random r=new Random();        
            this.rootNode=2;//r.nextInt(this.originGraph.length);
            this.status = new int[this.originGraph.length];
            this.visitTime = new int[this.originGraph.length];
            this.parents = new int[this.originGraph.length];
            this.treeEdgesGraph=new int[this.originGraph.length][this.originGraph.length];
            for (int i = 0; i < this.originGraph.length; i++){
                this.status[i]=1;
                this.visitTime[i]=0;
                this.parents[i]=0;
            }
            System.out.print("input Graph : ");
            for (int i = 0; i < this.originGraph.length; i++){
                System.out.print("\n");
                for (int j = 0; j < this.originGraph.length; j++)
                    System.out.print(arr[i][j]+" ");
            }
            System.out.print("\nDFS Execution Result : ");

    }
    public int[] getParents(){
        return this.parents;
    }
    public int getRootNode(){
        return this.rootNode;
    }
    public int[][] getTreeEdgesGraph(){
            return this.treeEdgesGraph;
    }
    public List<Pair<Integer, Integer>> getBackwardEdges(){
            return this.backwardEdges;
    }
    public int[] getVisitTimes(){
            return visitTime;
    }
    public int getIndexInVisitTimes(int element){
        for (int j = 0; j < this.originGraph.length; j++)
            if(this.visitTime[j]==element)
                return j;
        return -1;
    }
    public int getIndexInParents(int element){
        for (int j = 0; j < this.originGraph.length; j++)
            if(this.parents[j]==element)
                return j;
        return -1;
    }
    public void setTreeEdgesGraph(){
         for(Pair edge:treeEdges)
             this.treeEdgesGraph[(int)edge.getKey()][(int)edge.getValue()]=1;
    }
    public void dfsRun(int node){
        if(!this.once){
            this.parents[node]=-1;
            this.once=true;
        }
       this.status[node]=2;//discovered
       System.out.print(node +" -> ");
       for (int j = 0; j < status.length; j++){
           if(this.originGraph[node][j] == 1 && this.status[j]==1){
               this.visitTime[j]=++this.visitNum;
               treeEdges.add(new Pair<>(node,j));
               this.parents[j]=node;
               dfsRun(j);
           }else if(this.originGraph[node][j] == 1 && this.status[j]==2){
                   if(!treeEdges.contains(new Pair<>(j,node)) )//for don't add direct backwardEdge
                        backwardEdges.add(new Pair<>(node,j));
           }
       }
       this.status[node]=3;//finish
       
    }
    public boolean nodeHasMoreThanOneChild(int nodeNumber){
        int numChildrenForRoot=0;//condition 1
        for(int i=0;i<this.originGraph.length;i++)
            if(this.treeEdgesGraph[nodeNumber][i]==1)
                numChildrenForRoot++;
        if(numChildrenForRoot>1)
            return true;
        return false;
    }
    public int numChildren(int nodeNumber){
        int numChildrenForRoot=0;
        for(int i=0;i<this.originGraph.length;i++)
            if(this.treeEdgesGraph[nodeNumber][i]==1)
                numChildrenForRoot++;

        return numChildrenForRoot;
    }
    public boolean isLeaf(int nodeNumber){
        for(int i=0;i<this.originGraph.length;i++)
            if(this.treeEdgesGraph[nodeNumber][i]==1)
                return false;
        return true;
    }
    public boolean isRooted(int nodeNumber,int rootNode){
        if(nodeNumber==rootNode)
            return true;
        return false;
    }
    public void printStatus(){
       System.out.print("\nStatus Array : ");
       for (int j = 0; j < status.length; j++)
            if(j == status.length-1)
               System.out.print(status[j]+" ");
            else
               System.out.print(status[j]+" - ");
    }
    public void printVisitTime(){
       System.out.print("\nVisitTime Array : ");
       for (int j = 0; j < this.originGraph.length; j++)
               System.out.print(visitTime[j]+" ");
    }    
    public void printTreeGraph(){
        this.setTreeEdgesGraph();
        System.out.print("\ntree Graph : ");
        for (int i = 0; i < this.originGraph.length; i++){
            System.out.print("\n");
            for (int j = 0; j < this.originGraph.length; j++)
              System.out.print(this.treeEdgesGraph[i][j]+" ");
        }
    }
    public void printTreeEdges(){
            System.out.print("\ntree Edges : ");
            System.out.print(this.treeEdges+" ");
    }
    public void printParents(){System.out.print("\nparents Array : ");
        for(int i=0;i<this.originGraph.length;i++)
            System.out.print(this.parents[i]+"->"+i+" , ");
    }
    public void printBackwardEdges(){
            System.out.print("\nbackward Edges : ");
            System.out.print(this.backwardEdges+" ");
    }
}
