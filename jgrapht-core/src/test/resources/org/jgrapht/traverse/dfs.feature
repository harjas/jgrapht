Feature: Depth First Search on a graph

	Scenario: dfs is running
		Given There is a graph
		And graph is not empty
		When you run DFS
		Then the output is the path taken
