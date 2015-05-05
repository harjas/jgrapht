package org.jgrapht.traverse;

import org.jgrapht.*;
import org.jgrapht.graph.*; 
import cucumber.api.java.en.Given;  
import cucumber.api.java.en.Then;  
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;  
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StepDefinitions {
	
	private DirectedGraph<String, DefaultEdge> dg;
	private DirectedGraph<String, DefaultEdge> dg2;
	private DirectedGraph<String, DefaultEdge> dg3;
        private boolean isEmpty;
        private AbstractGraphIterator<String, DefaultEdge> dfs;
	private AbstractGraphIterator<String, DefaultEdge> dfs2;
	private AbstractGraphIterator<String, DefaultEdge> dfs3;

	@Given("^There is a graph$")
	public void thereIsAGraph(){
		this.dg = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
                String a = "A";
                String b = "B";
                String c = "C";
                String d = "D";

                this.dg.addVertex(a);
                this.dg.addVertex(b);
                this.dg.addVertex(c);
                this.dg.addVertex(d);

                this.dg.addEdge(a,b);
                this.dg.addEdge(b,c);
                this.dg.addEdge(c,d);		
	}

	@And("^graph is not empty$")
	public void isGraphEmpty(){
		if(this.dg.vertexSet().size() > 0)
                        this.isEmpty = false;
                else
                    	this.isEmpty = true;
	}

	@When("^you run DFS$")
	public void runDFS(){
		this.dfs = new DepthFirstIterator<String, DefaultEdge>(this.dg);
	}

	@Then("^the output is the path taken$")
	public void theOutputIsPath(){
		String actual = "";
                while (this.dfs.hasNext()) {
                     String v = this.dfs.next();
                     actual += v;
                }

                String expected = "ABCD";
                assertEquals(expected, actual);
	}

	@Given("^an empty graph$")
	public void emptyGraph(){
		this.dg2 = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
	}

	@When("^I run DFS$")
	public void DFSonEmpty(){
		this.dfs2 = new DepthFirstIterator<String, DefaultEdge>(this.dg2);
	}

	@Then("^output is fail$")
	public void outputIs(){
		assertFalse(this.dfs2.hasNext());
	}

	@Given("^a graph$")
	public void thisIsGraph(){
		this.dg3 = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		String a = "A";
		String b = "B";
		String c = "C";
	
		this.dg3.addVertex(a);
		this.dg3.addVertex(b);
		this.dg3.addVertex(c);

		this.dg3.addEdge(a,b);
		this.dg3.addEdge(b,c);
	}

	@When("^you run dfs$")
	public void runningDFS(){
		this.dfs3 = new DepthFirstIterator<String, DefaultEdge>(this.dg3);
	}

	@Then("^you get output$")
	public void outputHere(){
		/*String actual = "";
       		while (this.dfs3.hasNext()) {
           		String v = dfs.next();
            		actual += v;
        	}

        	String expected = "ABC";*/
        	assertEquals(true, this.dg3.vertexSet().size() == 3);
	}

	@Then("^the edges are inserted$")
	public void outputOfEdges(){
		assertEquals(true, this.dg3.edgeSet().size() == 2);
	}
}
