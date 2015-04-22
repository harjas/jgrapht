Feature: Depth First Search on a graph

	Background:
		Given There is a graph
	Scenario: We have a graph
		And graph is not empty
		When you run DFS
		Then the output is the path taken
