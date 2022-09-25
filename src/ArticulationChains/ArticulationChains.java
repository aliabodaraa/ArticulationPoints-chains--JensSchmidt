/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArticulationChains;
import Dfs.Dfs;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author DELL
 */
public class ArticulationChains {
    int currentVertix;
    private boolean isArticulation [];
    private int [][] graph;
    private int [][] chainsgraph;
    private Dfs dfs;
    public ArticulationChains(int[][] graph){
       dfs=new Dfs(graph);
       this.graph=graph;
       this.chainsgraph=new int[this.graph.length][this.graph.length];
       dfs.dfsRun(dfs.getRootNode());
       dfs.setTreeEdgesGraph();
       dfs.printTreeEdges();
       dfs.printBackwardEdges();
       dfs.printParents();
       dfs.printVisitTime();
    }
    public boolean isExistBackEdgeFromNode(int node){
      for(Pair edge:dfs.getBackwardEdges())  
          if((int)edge.getValue()==node)
            return true;
      return false;
    }
    public List<Pair<Integer,Integer>> getBackEdgesForNode(int node){
      List<Pair<Integer,Integer>> list = new ArrayList<Pair<Integer,Integer>>();
            for(Pair edge:dfs.getBackwardEdges())  
                if((int)edge.getValue() == node)
                    list.add(edge);
      return list;      
    }
    public void findPridges(){
        System.out.print("\n11111111111");
        for(Pair<Integer,Integer> l:this.findChains())
            System.out.println("\n"+l);
        System.out.print("\n22222222222");
    }
    public List<Pair<Integer,Integer>> findChains(){
        List<Pair<Integer,Integer>> chainsNotContainsRoot=new ArrayList<Pair<Integer,Integer>> ();
         for(int i=0; i<this.graph.length;i++){
             List<Pair<Integer,Integer>> backEdges = getBackEdgesForNode(dfs.getIndexInVisitTimes(i));
             if(backEdges.size()!=0){
                 int startPath=(int)backEdges.get(0).getValue();//1=2,  2
                 dfs.getVisitTimes()[startPath]=-2;
                 for(Pair<Integer,Integer> backEdgesForCurrentNode:backEdges){
                     List path=new ArrayList();
                        System.out.print("\n"+backEdgesForCurrentNode);
                        path.add(startPath);
                        path.add(backEdgesForCurrentNode.getKey());//1=2,  1
                        //dfs.getVisitTimes()[backEdgesForCurrentNode.getKey()]=-2;
                        if(i != 0){
                           chainsNotContainsRoot.add(new Pair(startPath,backEdgesForCurrentNode.getKey()));
                        }
                        int jump=dfs.getParents()[backEdgesForCurrentNode.getKey()];
                        while(dfs.getVisitTimes()[jump]!=-2){
                            if(jump==-1) break;
                            path.add(jump);
                            dfs.getVisitTimes()[jump]=-2;
                            if(i != 0){
                                chainsNotContainsRoot.add(new Pair(jump,dfs.getParents()[jump]));
                            }
                            jump=dfs.getParents()[jump];
                        };
                        System.out.print(path);
                        dfs.printVisitTime();
                 }
             }
         }//dfs.printVisitTime();
         return chainsNotContainsRoot;
    }
}
