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
    public Pair<Integer,Integer> getBackEdgeFromNode(int node){
      if(this.isExistBackEdgeFromNode(node))
            for(Pair edge:dfs.getBackwardEdges())  
                if((int)edge.getValue()== node)
                    return edge;
      return new Pair<Integer,Integer>(-1,-1);      
    }
    public void ArticulationChainsRun(){
         for(int i=0; i<this.graph.length;i++){
             List path=new ArrayList();
             Pair<Integer,Integer> backEdge=getBackEdgeFromNode(dfs.getIndexInVisitTimes(i));
             //path.add(dfs.getIndexInVisitTimes(i));
             if(backEdge.getKey()!=-1){
                 System.out.print("\n"+backEdge+"  --   ");
                 int startPath=(int)backEdge.getValue();
                 path.add(backEdge.getValue());
                 dfs.getVisitTimes()[backEdge.getValue()]=-2;
                 path.add(backEdge.getKey());
                 dfs.getVisitTimes()[backEdge.getKey()]=-2;
                 int jump=dfs.getParents()[backEdge.getKey()];
                 do{
                    if((jump != startPath || dfs.getVisitTimes()[jump] != -2) && jump != -1){
                        path.add(jump);
                        dfs.getVisitTimes()[jump]=-2;
                        jump=dfs.getParents()[jump];
                     }else if((jump == startPath || dfs.getVisitTimes()[jump] == -2 ) && jump != -1){
                        path.add(jump);
                        break;
                     }else if(jump == -1){
                         break;
                     }
                 }while(true);
             } System.out.print(path);
             dfs.printVisitTime();
         }
    }
}
