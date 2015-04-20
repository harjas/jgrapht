package org.jgrapht.util;

import java.util.*;

import junit.framework.*;


public class VertexPairTest
    extends TestCase
{
    VertexPair<Integer> pair = new VertexPair(1, 2);

	public void testGetFirst()
    {
        int e = pair.getFirst();
        assertEquals(1, e);
    }

    public void testGetSecond()
    {
        int e = pair.getSecond();
        assertEquals(2, e);
    }

    public void testHasVertex()
    {
        assertTrue(pair.hasVertex(1) && pair.hasVertex(2));
    }

    public void testGetOther()
    {
        int e = pair.getOther(1);
        assertEquals(2, e);
    }

    public void testToString()
    {
        assertEquals("1,2", pair.toString());
    }
}