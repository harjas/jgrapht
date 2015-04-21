package org.jgrapht.traverse;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import cucumber.api.java.en.And;  
import cucumber.api.java.en.Given;  
import cucumber.api.java.en.Then;  
import cucumber.api.java.en.When;  

public class StepDefinitions extends AbstractGraphIteratorTest {
	
	private DirectedGraph<String, DefaultEdge> dg;	
	private boolean isEmpty;
	private AbstractGraphIterator<String, DefaultEdge> dfs;

    @Override
    String getExpectedStr1()
    {
        return "1,3,6,5,7,9,4,8,2";
    }

    @Override
    String getExpectedStr2()
    {
        return "1,3,6,5,7,9,4,8,2,orphan";
    }

    @Override
    String getExpectedFinishString()
    {
        return "6:4:9:2:8:7:5:3:1:orphan:";
    }

    @Override
    AbstractGraphIterator<String, DefaultEdge> createIterator(
        DirectedGraph<String, DefaultEdge> g,
        String vertex)
    {
        AbstractGraphIterator<String, DefaultEdge> i =
            new DepthFirstIterator<String, DefaultEdge>(g, vertex);
        i.setCrossComponentTraversal(true);

        return i;
    }

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

	@When("^DFS is run$")
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

        	String expected = "ABCGIFEHJKLD";
        	assertEquals(expected, actual);
	}
}
