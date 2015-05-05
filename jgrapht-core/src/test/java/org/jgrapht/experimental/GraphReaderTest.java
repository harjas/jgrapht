/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
/* -------------------
 * GraphReaderTest.java
 * -------------------
 * (C) Copyright 2010-2010, by Michael Behrisch and Contributors.
 *
 * Original Author:  Michael Behrisch
 * Contributor(s):   -
 *
 * $Id$
 *
 * Changes
 * -------
 * 24-Dec-2008 : Initial revision (AN);
 *
 */
package org.jgrapht.experimental;

import java.io.*;

import junit.framework.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;


/**
 * .
 *
 * @author Michael Behrisch
 */
public class GraphReaderTest
    extends TestCase
{
    //~ Instance fields --------------------------------------------------------

    String _unweighted = "p 3\ne 1 2\ne 1 3\n";
    String _weighted = "p 3\ne 1 2 .5\ne 1 3 7\n";

    //~ Methods ----------------------------------------------------------------

    /**
     * .
     */
    public void testGraphReader()
    {
        GraphReader<Integer, DefaultEdge> reader;
        try {
            reader =
                new GraphReader<Integer, DefaultEdge>(
                    new StringReader(_unweighted));
            Graph<Integer, DefaultEdge> g =
                new SimpleGraph<Integer, DefaultEdge>(
                    DefaultEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            Graph<Integer, DefaultEdge> g2 =
                new SimpleGraph<Integer, DefaultEdge>(
                    DefaultEdge.class);
            g2.addVertex(0);
            g2.addVertex(1);
            g2.addVertex(2);
            g2.addEdge(0, 1);
            g2.addEdge(0, 2);
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    /**
     * .
     */
    public void testGraphReaderWeighted()
    {
        try {
            GraphReader<Integer, DefaultWeightedEdge> reader =
                new GraphReader<Integer, DefaultWeightedEdge>(
                    new StringReader(_weighted),
                    1);
            Graph<Integer, DefaultWeightedEdge> g =
                new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(
                    DefaultWeightedEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            WeightedGraph<Integer, DefaultWeightedEdge> g2 =
                new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(
                    DefaultWeightedEdge.class);
            g2.addVertex(0);
            g2.addVertex(1);
            g2.addVertex(2);
            g2.setEdgeWeight(g2.addEdge(0, 1), .5);
            g2.setEdgeWeight(g2.addEdge(0, 2), 7);
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    public void testUnweightedGraphReaderEmptyString()
    {	
        GraphReader<Integer, DefaultEdge> reader;
        String emptyGraph = "";
        try {
            reader = new GraphReader<Integer, DefaultEdge>(new StringReader(emptyGraph));
            Graph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            Graph<Integer, DefaultEdge> g2 = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    public void testUnweightedGraphReaderNodesOnly()
    {
        GraphReader<Integer, DefaultEdge> reader;
        String emptyGraph = "p 3\n";
        try {
            reader = new GraphReader<Integer, DefaultEdge>(new StringReader(emptyGraph));
            Graph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            Graph<Integer, DefaultEdge> g2 = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            g2.addVertex(0);
            g2.addVertex(1);
            g2.addVertex(2);
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    //No Expected output. Written to see if case was handled. Result: Is not handled.
    public void testUnweightedGraphReaderEdgesOnly()
    {
        GraphReader<Integer, DefaultEdge> reader;
        String emptyGraph = "e 1 2\ne 2 3";
        try {
            reader = new GraphReader<Integer, DefaultEdge>(new StringReader(emptyGraph));
            Graph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            Graph<Integer, DefaultEdge> g2 = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }
    
    //No expected output. Was written to see if case was handled. Result: Is not handled. 
    public void testUnweightedGraphReaderMoreEdgesThanNodes()
    {
        GraphReader<Integer, DefaultEdge> reader;
        String emptyGraph = "p 2\ne 1 2\ne 2 3";
        try {
            reader = new GraphReader<Integer, DefaultEdge>(new StringReader(emptyGraph));
            Graph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            Graph<Integer, DefaultEdge> g2 = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    //No right expected output. Ran it to see if case was handled. Result: Is not handled. 	
    public void testUnweightedGraphReaderWithDifferentEdgeNumbers()
    {
        System.out.println("Testing Different edge numbers");
	GraphReader<Integer, DefaultEdge> reader;
        String emptyGraph = "p 3\ne 3 4\ne 4 5";
        try {
            reader = new GraphReader<Integer, DefaultEdge>(new StringReader(emptyGraph));
            Graph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            Graph<Integer, DefaultEdge> g2 = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
	    g2.addVertex(0);
	    g2.addVertex(1);
	    g2.addVertex(2);
	    g2.addEdge(3,4);
            g2.addEdge(4,5);	
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    public void testUnweightedGraphReaderWithNegativeNodes()
    {
        GraphReader<Integer, DefaultEdge> reader;
        String emptyGraph = "p -3\ne 1 2\ne 2 3";
        try {
            reader = new GraphReader<Integer, DefaultEdge>(new StringReader(emptyGraph));
            Graph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            Graph<Integer, DefaultEdge> g2 = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
	    g2.addVertex(0);
	    g2.addVertex(1);
	    g2.addVertex(2);
	    g2.addEdge(0,1);
     	    g2.addEdge(1,2);
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    public void testUnweightedGraphReaderWithExtraSpaces()
    {
        GraphReader<Integer, DefaultEdge> reader;
        String emptyGraph = "p 3\ne 1  2 \ne  1  3";
        try {
            reader = new GraphReader<Integer, DefaultEdge>(new StringReader(emptyGraph));
            Graph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            Graph<Integer, DefaultEdge> g2 = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
            g2.addVertex(0);
            g2.addVertex(1);
            g2.addVertex(2);
            g2.addEdge(0,1);
            g2.addEdge(0,2);
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    public void testGraphReaderWeightedWithNegativeWeights()
    {   
        String graphString = "p 3\ne 1 2 .5\ne 1 3 -7\n";
        try {
            GraphReader<Integer, DefaultWeightedEdge> reader = new GraphReader<Integer, DefaultWeightedEdge>(new StringReader(graphString),1);
            Graph<Integer, DefaultWeightedEdge> g = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            WeightedGraph<Integer, DefaultWeightedEdge> g2 = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            g2.addVertex(0);
            g2.addVertex(1);
            g2.addVertex(2);
            g2.setEdgeWeight(g2.addEdge(0, 1), .5);
            g2.setEdgeWeight(g2.addEdge(0, 2), -7);
            
	    DefaultWeightedEdge e1 = g.getEdge(0, 1);
            DefaultWeightedEdge e2 = g.getEdge(0, 2);
            DefaultWeightedEdge e3 = g2.getEdge(0, 1);
            DefaultWeightedEdge e4 = g2.getEdge(0, 2);

            assertEquals(g2.getEdgeWeight(e3), g.getEdgeWeight(e1));
            assertEquals(g2.getEdgeWeight(e4), g.getEdgeWeight(e2));
	    assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

    public void testGraphReaderWeightedWithMissingWeights()
    {   
        String graphString = "p 3\ne 1 2\ne 1 3 -7\n";
        try {
            GraphReader<Integer, DefaultWeightedEdge> reader = new GraphReader<Integer, DefaultWeightedEdge>(new StringReader(graphString),1);
            Graph<Integer, DefaultWeightedEdge> g = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            WeightedGraph<Integer, DefaultWeightedEdge> g2 = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            g2.addVertex(0);
            g2.addVertex(1);
            g2.addVertex(2);
            g2.setEdgeWeight(g2.addEdge(0, 1), 1);
            g2.setEdgeWeight(g2.addEdge(0, 2), -7);
	    
	    DefaultWeightedEdge e1 = g.getEdge(0, 1);
	    DefaultWeightedEdge e2 = g.getEdge(0, 2);	
	    DefaultWeightedEdge e3 = g2.getEdge(0, 1);
	    DefaultWeightedEdge e4 = g2.getEdge(0, 2);
            
	    assertEquals(g2.getEdgeWeight(e3), g.getEdgeWeight(e1));
	    assertEquals(g2.getEdgeWeight(e4), g.getEdgeWeight(e2));
        } catch (IOException e) {
        }
    }

    public void testGraphReaderWeightedWithMaxValueWeights()
    {   
        String graphString = "p 3\ne 1 2\ne 1 3 1.79769313486232E+308\n";
        try {
            GraphReader<Integer, DefaultWeightedEdge> reader = new GraphReader<Integer, DefaultWeightedEdge>(new StringReader(graphString),1);
            Graph<Integer, DefaultWeightedEdge> g = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            VertexFactory<Integer> vf = new IntVertexFactory();
            reader.generateGraph(g, vf, null);
            
            WeightedGraph<Integer, DefaultWeightedEdge> g2 = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            g2.addVertex(0);
            g2.addVertex(1);
            g2.addVertex(2);
            g2.setEdgeWeight(g2.addEdge(0, 1), 1);
            g2.setEdgeWeight(g2.addEdge(0, 2), Double.POSITIVE_INFINITY);
	    System.out.println(g.toString());

            DefaultWeightedEdge e1 = g.getEdge(0, 1);
            DefaultWeightedEdge e2 = g.getEdge(0, 2);
            DefaultWeightedEdge e3 = g2.getEdge(0, 1);
            DefaultWeightedEdge e4 = g2.getEdge(0, 2);

            assertEquals(g2.getEdgeWeight(e3), g.getEdgeWeight(e1));
            assertEquals(g2.getEdgeWeight(e4), g.getEdgeWeight(e2));
            assertEquals(g2.toString(), g.toString());
        } catch (IOException e) {
        }
    }

public void testGraphReaderTerminalCoverage() {
    String _unweightedG = "p 10\ne 1 2\ne 2 3\ne 3 4\ne 4 5\ne 5 6\ne 6 7\ne 7 8\ne 8 9\ne 9 10";
    GraphReader<Integer, DefaultEdge> reader;
    try {
        reader =
            new GraphReader<Integer, DefaultEdge>(
                new StringReader(_unweightedG));
        Graph<Integer, DefaultEdge> g =
            new SimpleGraph<Integer, DefaultEdge>(
                DefaultEdge.class);
        VertexFactory<Integer> vf = new IntVertexFactory();
        reader.generateGraph(g, vf, null);
        Graph<Integer, DefaultEdge> g2 =
            new SimpleGraph<Integer, DefaultEdge>(
                DefaultEdge.class);
        g2.addVertex(0);
        g2.addVertex(1);
        g2.addVertex(2);
        g2.addVertex(3);
        g2.addVertex(4);
        g2.addVertex(5);
        g2.addVertex(6);
        g2.addVertex(7);
        g2.addVertex(8);
        g2.addVertex(9);
        
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 5);
        g2.addEdge(5, 6);
        g2.addEdge(6, 7);
        g2.addEdge(7, 8);
        g2.addEdge(8, 9);

        assertEquals(g2.toString(), g.toString());
    } catch (IOException e) {
    }
}

    //~ Inner Classes ----------------------------------------------------------

    private static final class IntVertexFactory
        implements VertexFactory<Integer>
    {
        int last = 0;

        @Override
        public Integer createVertex()
        {
            return last++;
        }
    }
}

// End GraphReaderTest.java
