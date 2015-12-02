/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a06;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
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
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths( dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths( dG, w);
        
        int a1 = -1;
        int a2 = -1;
        
        for( int el: bfsV.pathTo(root()))
        {
            if(bfsW.hasPathTo(el)) a1 = el;
        }
        
        for( int el: bfsW.pathTo(root()))
        {
            if( bfsV.hasPathTo(el)) a2 = el;
        }
        
        if(bfsV.distTo(a1) < bfsV.distTo(a2) &&
                bfsW.distTo(a1) < bfsW.distTo(a2))    return a1;
        
        if(bfsV.distTo(a2) < bfsV.distTo(a1) 
                && bfsW.distTo(a2) < bfsW.distTo(a1)) return a2;
        
        return -1;
    }
    
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        
        return bfsV.distTo(ancestor( v, w)) + bfsW.distTo(ancestor(v,w));
    }
    
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        
        for( int el : bfsV.pathTo(root()))//replace 'v' in both of these with whatever the root is.
        {
            for( int el2 : bfsW.pathTo(root()))
            {
                if( el == el2) return el;
            }
        }
        
        return -1;
        
        /*
            What I could do is create a single for-each loop with a sentinel variable that
            controls the index of the internal or opposite list.
            
            Or I can iterate through a single list and see if it has a path to the each of one
            the elements of the opposite.
        */
    }
    
    private int root()
    {
        int root = -1;
        for(int i = 0; i < dG.V(); i++)
        {
            if( dG.outdegree(i) == 0) root = i;
        }
        
        return root;
    }
    
    public static void main(String[] args) {
        
    }
}
