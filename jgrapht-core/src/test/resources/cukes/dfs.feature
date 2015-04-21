Feature: Depth First Search on a graph

	Background:
		Given There is a graph
	Scenario: There is a graph
		And graph is not empty
		When DFS is run
		Then the output is the path taken
