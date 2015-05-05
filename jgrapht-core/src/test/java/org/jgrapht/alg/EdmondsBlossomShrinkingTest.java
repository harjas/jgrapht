/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2012, by Barak Naveh and Contributors.
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
/* -------------------------
 * EdmondsBlossomShrinkingTest.java
 * -------------------------
 * (C) Copyright 2012-2012, by Alejandro Ramon Lopez del Huerto and Contributors.
 *
 * Original Author:  Alejandro Ramon Lopez del Huerto
 * Contributor(s):
 *
 * Changes
 * -------
 * 24-Jan-2012 : Initial revision (ARLH);
 *
 */
package org.jgrapht.alg;

import junit.framework.TestCase;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Set;

/**
 * .
 * 
 * @author Alejandro R. Lopez del Huerto
 * @since Jan 24, 2012
 */
public final class EdmondsBlossomShrinkingTest extends TestCase
{
    public void testOne()
    {
        // create an undirected graph
        UndirectedGraph<Integer, DefaultEdge> g =
            new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        Integer v1 = 1;
        Integer v2 = 2;
        Integer v3 = 3;
        Integer v4 = 4;

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        DefaultEdge e12 = g.addEdge(v1, v2);
        DefaultEdge e23 = g.addEdge(v2, v3);
        DefaultEdge e24 = g.addEdge(v2, v4);
        DefaultEdge e34 = g.addEdge(v3, v4);

        // compute max match
        EdmondsBlossomShrinking<Integer, DefaultEdge> matcher =
            new EdmondsBlossomShrinking<Integer, DefaultEdge>(g);
        Set<DefaultEdge> match = matcher.getMatching();
        assertEquals(2, match.size());
        assertTrue(match.contains(e12));
        assertTrue(match.contains(e34));
    }
    
    public void testBloom()
    {
        // create an undirected graph
        UndirectedGraph<Integer, DefaultEdge> g =
            new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        Integer v1 = 1;
        Integer v2 = 2;
        Integer v3 = 3;
        Integer v4 = 4;
        Integer v5 = 5;
        Integer v6 = 6;

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        DefaultEdge e12 = g.addEdge(v1, v2);
        DefaultEdge e23 = g.addEdge(v2, v3);
        DefaultEdge e34 = g.addEdge(v3, v4);
        DefaultEdge e45 = g.addEdge(v4, v5);
        DefaultEdge e56 = g.addEdge(v5, v6);
        DefaultEdge e62 = g.addEdge(v6, v2);

        // compute max match
        EdmondsBlossomShrinking<Integer, DefaultEdge> matcher =
            new EdmondsBlossomShrinking<Integer, DefaultEdge>(g);
        Set<DefaultEdge> match = matcher.getMatching();
        assertEquals(3, match.size());
        assertTrue(match.contains(e12));
        assertTrue(match.contains(e34));
        assertTrue(match.contains(e56));
    }

    public void testDisconnected()
    {
        // create an undirected graph
        UndirectedGraph<Integer, DefaultEdge> g =
            new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        Integer v1 = 1;
        Integer v2 = 2;
        Integer v3 = 3;
        Integer v4 = 4;

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        DefaultEdge e12 = g.addEdge(v1, v2);
        DefaultEdge e34 = g.addEdge(v3, v4);

        // compute max match
        EdmondsBlossomShrinking<Integer, DefaultEdge> matcher =
            new EdmondsBlossomShrinking<Integer, DefaultEdge>(g);
        Set<DefaultEdge> match = matcher.getMatching();
        assertEquals(2, match.size());
        assertTrue(match.contains(e12));
        assertTrue(match.contains(e34));
    }

    public void testTwoWay()
    {
        // create an undirected graph
        UndirectedGraph<Integer, DefaultEdge> g =
            new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        Integer v1 = 1;
        Integer v2 = 2;

        g.addVertex(v1);
        g.addVertex(v2);

        DefaultEdge e12 = g.addEdge(v1, v2);
        DefaultEdge e21 = g.addEdge(v2, v1);

        // compute max match
        EdmondsBlossomShrinking<Integer, DefaultEdge> matcher =
            new EdmondsBlossomShrinking<Integer, DefaultEdge>(g);
        Set<DefaultEdge> match = matcher.getMatching();
        assertEquals(1, match.size());
        assertTrue(match.contains(e12) || match.contains(e21));
    }

    // Bug! null pointer exception
    public void testForest()
    {
        // create an undirected graph
        UndirectedGraph<Integer, DefaultEdge> g =
            new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        Integer v1 = 1;
        Integer v2 = 2;
        Integer v3 = 3;
        Integer v4 = 4;
        Integer v5 = 5;
        Integer v6 = 6;
        Integer v7 = 7;
        Integer v8 = 8;
        Integer v9 = 9;
        Integer v10 = 10;
        Integer v11 = 11;
        Integer v12 = 12;
        Integer v13 = 13;
        Integer v14 = 14;
        Integer v15 = 15;
        Integer v16 = 16;
        Integer v17 = 17;
        Integer v18 = 18;
        Integer v19 = 19;

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);
        g.addVertex(v10);
        g.addVertex(v11);
        g.addVertex(v12);
        g.addVertex(v13);
        g.addVertex(v14);
        g.addVertex(v15);
        g.addVertex(v16);
        g.addVertex(v17);
        g.addVertex(v18);
        g.addVertex(v19);

        DefaultEdge e12 = g.addEdge(v1, v2);
        DefaultEdge e23 = g.addEdge(v2, v3);
        DefaultEdge e34 = g.addEdge(v3, v4);
        DefaultEdge e45 = g.addEdge(v4, v5);
        DefaultEdge e56 = g.addEdge(v5, v6);
        DefaultEdge e57 = g.addEdge(v5, v7);
        DefaultEdge e58 = g.addEdge(v5, v8);
        DefaultEdge e69 = g.addEdge(v6, v9);
        DefaultEdge e710 = g.addEdge(v7, v10);
        DefaultEdge e811 = g.addEdge(v8, v11);
        DefaultEdge e1213 = g.addEdge(v12, v13);
        DefaultEdge e1314 = g.addEdge(v13, v14);
        DefaultEdge e1415 = g.addEdge(v14, v15);
        DefaultEdge e1416 = g.addEdge(v14, v16);
        DefaultEdge e1517 = g.addEdge(v15, v17);
        DefaultEdge e1618 = g.addEdge(v16, v18);

        // compute max match
        EdmondsBlossomShrinking<Integer, DefaultEdge> matcher =
            new EdmondsBlossomShrinking<Integer, DefaultEdge>(g);
        Set<DefaultEdge> match = matcher.getMatching();
        assertEquals(8, match.size());
    }

    // BUG!!, infinite loop
    public void testForestBug()
    {
        // create an undirected graph
        UndirectedGraph<Integer, DefaultEdge> g =
            new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        Integer v1 = 1;
        Integer v2 = 2;
        Integer v3 = 3;
        Integer v4 = 4;
        Integer v5 = 5;
        Integer v6 = 6;
        Integer v7 = 7;
        Integer v8 = 8;
        Integer v9 = 9;
        Integer v10 = 10;
        Integer v11 = 11;

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);
        g.addVertex(v10);


        //DefaultEdge e119 = g.addEdge(v11, v9);
        DefaultEdge e910 = g.addEdge(v9, v10);
        DefaultEdge e101 = g.addEdge(v10, v1);

        DefaultEdge e12 = g.addEdge(v1, v2);
        DefaultEdge e23 = g.addEdge(v2, v3);
        DefaultEdge e24 = g.addEdge(v2, v4);
        DefaultEdge e25 = g.addEdge(v2, v5);
        DefaultEdge e36 = g.addEdge(v3, v6);
        DefaultEdge e47 = g.addEdge(v4, v7);
        DefaultEdge e58 = g.addEdge(v5, v8);

        // compute max match
        EdmondsBlossomShrinking<Integer, DefaultEdge> matcher =
            new EdmondsBlossomShrinking<Integer, DefaultEdge>(g);
        Set<DefaultEdge> match = matcher.getMatching();
        assertEquals(5, match.size());
    }

    // Coverage Yay!
    public void testComplete()
    {
        // create an undirected graph
        UndirectedGraph<Integer, DefaultEdge> g =
            new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        Integer v1 = 1;
        Integer v2 = 2;
        Integer v3 = 3;
        Integer v4 = 4;
        Integer v5 = 5;

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);

        DefaultEdge e12 = g.addEdge(v1, v2);
        DefaultEdge e13 = g.addEdge(v1, v3);
        DefaultEdge e14 = g.addEdge(v1, v4);
        DefaultEdge e15 = g.addEdge(v1, v5);
        DefaultEdge e23 = g.addEdge(v2, v3);
        DefaultEdge e24 = g.addEdge(v2, v4);
        DefaultEdge e25 = g.addEdge(v2, v5);
        DefaultEdge e34 = g.addEdge(v3, v4);
        DefaultEdge e35 = g.addEdge(v3, v5);
        DefaultEdge e45 = g.addEdge(v4, v5);

        // compute max match
        EdmondsBlossomShrinking<Integer, DefaultEdge> matcher =
            new EdmondsBlossomShrinking<Integer, DefaultEdge>(g);
        Set<DefaultEdge> match = matcher.getMatching();
        assertEquals(2, match.size());
    }
}
