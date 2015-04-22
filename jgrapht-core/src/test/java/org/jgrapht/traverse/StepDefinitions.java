package org.jgrapht.traverse;

import org.jgrapht.*;
import org.jgrapht.graph.*; 
import cucumber.api.java.en.Given;  
import cucumber.api.java.en.Then;  
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;  
import static org.junit.Assert.assertEquals;

public class StepDefinitions {
	
	private DirectedGraph<String, DefaultEdge> dg;
        private boolean isEmpty;
        private AbstractGraphIterator<String, DefaultEdge> dfs;
	
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
}
