/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArticulationChains;
import Dfs.Dfs;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    private Object CollectionUtils;
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
    public void runArticulationViaChains(){//Fifth
            List<Pair<Integer,Integer>> chains = this.findChains();
            List<Pair<Integer,Integer>> bridges = this.findPridges(chains);
            List<Integer> articulationPoints = this.findArticulationPoints(bridges);
            this.printArticulationPoints(articulationPoints);
    }
    public void printArticulationPoints(List<Integer> articulationPoints){//Fourth
        if(articulationPoints.size()>0){
            System.out.print("\nThe Articulation Points are : "+articulationPoints+"\n");
            //for (Integer articulationPoint : articulationPoints ) {
            //    System.out.println(articulationPoint);
            //}
        }else{
            System.out.print("\nThere are'nt Articulation Points in This Graph :):):):)\n");
        }
    }
    public List<Integer> findArticulationPoints(List<Pair<Integer,Integer>> bridges){//Third
        List<Integer> articulationPoints =new ArrayList<>();
        for (Pair<Integer,Integer> bridge : bridges ) {
            if(dfs.numOfDegree((int)bridge.getKey())>0)
                articulationPoints.add((int)bridge.getKey());
            if(dfs.numOfDegree((int)bridge.getValue())>0)
                articulationPoints.add((int)bridge.getValue());            
        }
        return articulationPoints;
    }
    public List<Pair<Integer,Integer>> findPridges(List<Pair<Integer,Integer>> chains){//Second
        List<Pair<Integer,Integer>> bridges=new ArrayList<>();
        List<Pair<Integer,Integer>> allTreeEdges = new ArrayList<Pair<Integer,Integer>>(dfs.getTreeEdges());
        allTreeEdges.removeAll(chains);//delete common Pair that are exists in this.findChains() from  dfs.getTreeEdges() , all remaining elements are belong to dfs.getTreeEdges() and also named PRIDGES
        for (Pair<Integer,Integer> bridge : allTreeEdges ) {
            bridges.add(bridge);
        }
        System.out.print("\nBridges are: "+bridges);
        return bridges;
    }
    public List<Pair<Integer,Integer>> findChains(){//First
        int[] visitTimeCopy = dfs.getVisitTimes().clone();
        List<Pair<Integer,Integer>> chainsNotContainsRoot=new ArrayList<Pair<Integer,Integer>> ();
         for(int i=0; i<this.graph.length;i++){
             List<Pair<Integer,Integer>> backEdges = getBackEdgesForNode(dfs.getIndexInVisitTimes(i));
             if(backEdges.size() == 0) continue;
             System.out.println("\n  "+dfs.getIndexInVisitTimes(i)+" --> "+backEdges);
                 int startPath=(int)backEdges.get(0).getValue();//1=2,  2
                 visitTimeCopy[startPath]=-2;
                 boolean once = false;
                 for(Pair<Integer,Integer> backEdgesForCurrentNode:backEdges){
                     List path=new ArrayList();
                        if(!once){
                            path.add(startPath);
                            visitTimeCopy[startPath]=-2;
                            once=true;
                        }
                        path.add(backEdgesForCurrentNode.getKey());//1=2,  1
                        visitTimeCopy[backEdgesForCurrentNode.getKey()]=-2;
                        int jump=dfs.getParents()[backEdgesForCurrentNode.getKey()];
                        chainsNotContainsRoot.add(new Pair(jump,backEdgesForCurrentNode.getKey()));
                        do{
                            if(jump==-1) break;
                            path.add(jump);
                            visitTimeCopy[jump]=-2;
                            chainsNotContainsRoot.add(new Pair(dfs.getParents()[jump],jump));
                            jump=dfs.getParents()[jump];
                        }while(visitTimeCopy[jump]!=-2);
                        System.out.print("  "+path);
                 }
         }
         return chainsNotContainsRoot;
    }
}
