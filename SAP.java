/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a06;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;

/**
 *
 * @author Jarom_2
 */
public class SAP 
{
    private Digraph dG;
    
    
    public SAP(Digraph G)
    {
    	if(G == null) throw new NullPointerException();
        dG = new Digraph(G);
    }
    
    public boolean isDAG()
    {
        DirectedCycle dc = new DirectedCycle(dG);
        return !dc.hasCycle();
    }
    
    public boolean isRootedDAG()
    {
        if( !isDAG()) return false;
        
        for (int i = 0; i < dG.V(); i++) {
            if(dG.outdegree(i) == 0) return true;
        }
        return false;
    }
    
    //Shortest ancestral path between v and w
    public int length(int v, int w)
    {   
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths( dG, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths( dG, w);
        
        return bfs.distTo(ancestor(v,w)) + bfs2.distTo(ancestor(v,w));
    }
    
    public int ancestor(int v, int w)
    {
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths( dG, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths( dG, w);
        
        for( int el : bfs.pathTo(root()))//replace 'v' in both of these with whatever the root is.
        {
            for( int el2 : bfs2.pathTo(root()))
            {
                if( el == el2) return el;
            }
        }
        
        return -1;
    }
    
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
    	return 0;
    }
    
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        return 0;
    }
    
    private int root()
    {
        int root = -1;
        for(int i = 0; i < dG.V(); i++)
        {
            if( dG.outdegree(i) == 0) root = dG.outdegree(i);
        }
        
        return root;
    }
    
    public static void main(String[] args) {
        
    }
}
