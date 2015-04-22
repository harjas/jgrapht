Feature: Depth First Search on a graph

	Scenario: dfs is running
		Given There is a graph
		And graph is not empty
		When you run DFS
		Then the output is the path taken
	
	Scenario: empty graph test
		Given an empty graph
		When I run DFS
		Then output is fail

	Scenario: check if vertex insertion works
		Given a graph
		When you run dfs
		Then you get output

	Scenario: check if edge insertion works
		Given a graph
		Then the edges are inserted
