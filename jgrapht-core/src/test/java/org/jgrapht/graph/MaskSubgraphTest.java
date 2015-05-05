package org.jgrapht.graph;

import java.util.*;

import junit.framework.*;

import org.jgrapht.*;



public class MaskSubgraphTest
    extends TestCase
{
	private MaskSubgraph<Integer, DefaultEdge> ms;
	private Integer v1 = 1;
    private Integer v2 = 2;
    private Integer v3 = 3;
    private Integer v4 = 4;
    private Integer v5 = 5;

	class TestFunctor<V, E> implements MaskFunctor<V, E> {

		private V testV;

		public TestFunctor(V v) {
			testV = v;
		}

		public boolean isEdgeMasked(E edge) {

			return false;
		}

		public boolean isVertexMasked(V v) {
			if(v.equals(testV)) {
				return true;
			}

			return false;
		}

	}

	private MaskSubgraphTest.TestFunctor<Integer, DefaultEdge> tf;
	private SimpleDirectedGraph<Integer, DefaultEdge> g;

	public void testContainsEdge() {
		assertTrue(ms.containsEdge(g.getEdge(v2, v1)));
	}

	public void testContainsVertex() {
		assertTrue(ms.containsVertex(v1));
	}

	public void testDegreeOf() {
		assertEquals(3, ms.degreeOf(v1));
	}

	public void testGetAllEdges() {
		Set<DefaultEdge> se = ms.getAllEdges(v2, v1);
		assertTrue(se.contains(g.getEdge(v2, v1)));
	}

	public void testGetEdgeSource() {
		assertEquals(v2, ms.getEdgeSource(g.getEdge(v2, v1)));
	}

	public void testGetEdgeTarget() {
		assertEquals(v1, ms.getEdgeTarget(g.getEdge(v2, v1)));
	}

	public void testIncomingEdgesOf() {
		assertEquals(2, ms.incomingEdgesOf(v1).size());
	}

	public void testOutgoingEdgesOf() {

		Set<DefaultEdge> es = new HashSet<DefaultEdge>();
		es.add(g.getEdge(v2, v1));

		assertEquals(es, ms.outgoingEdgesOf(v2));
	}


	@Override
    protected void setUp()
    {
        g = new SimpleDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);

        g.addEdge(v2, v1);
        g.addEdge(v1, v2);
        g.addEdge(v3, v1);
        g.addEdge(v4, v1);

        g.addEdge(v3, v2);
        g.addEdge(v4, v2);

        g.addEdge(v4, v3);

        g.addEdge(v4, v5);

        tf = new TestFunctor(4);
        ms = new MaskSubgraph<Integer, DefaultEdge>(g, tf);
    }
}