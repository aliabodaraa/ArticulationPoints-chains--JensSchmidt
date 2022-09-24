/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulationpointsusingchains;
import Dfs.Dfs;
import ArticulationChains.ArticulationChains;
import java.util.Random;
import java.util.Scanner;  // Import the Scanner class
import javafx.util.Pair;
/**
 *
 * @author DELL
 */
public class ArticulationPointsUsingChains {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] graph1={
                     {0,1,1,0,1},
                     {1,0,0,0,0},
                     {1,0,0,1,1},
                     {0,0,1,0,0},
                     {1,0,1,0,0}
                    };
       int[][] graph2={
                     {0,1,0,1,0,0},
                     {1,0,1,0,1,0},
                     {0,1,0,1,1,1},
                     {1,0,1,0,0,0},
                     {0,1,1,0,0,1},
                     {0,0,1,0,1,0}
                    };
              int[][] graph3={
                     {0,1,1},
                     {1,0,0},
                     {1,0,0}
                    };
              int[][] graph4={
                     {0,1,1},
                     {1,0,1},
                     {1,1,0}
                    };
          int[][] graph5={
                     {0,1,0,1},
                     {1,0,1,0},
                     {0,1,0,1},
                     {1,0,1,0}
                    };
          int[][] graph6={
                     {0,1,1,1,0,0,0,0,0,0},
                     {1,0,1,0,1,0,0,0,0,0},
                     {1,1,0,1,1,0,0,0,0,0},
                     {1,0,1,0,0,0,0,0,0,0},
                     {0,1,1,0,0,1,0,0,1,1},
                     {0,0,0,0,1,0,1,1,0,0},
                     {0,0,0,0,0,1,0,1,0,0},
                     {0,0,0,0,0,1,1,0,0,0},
                     {0,0,0,0,1,0,0,0,0,1},
                     {0,0,0,0,1,0,0,0,1,0},
                    };
        //Dfs dfs=new Dfs(graph6);
        //dfs.dfsRun(2);
        //dfs.printTreeEdges();
        //dfs.printBackwardEdges();
        ArticulationChains articulation=new ArticulationChains(graph6);
        articulation.ArticulationChainsRun();
    }
    
}
