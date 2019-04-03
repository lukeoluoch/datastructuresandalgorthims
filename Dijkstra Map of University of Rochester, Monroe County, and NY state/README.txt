Luke Oluoch
Project: 4
MW 2:00-3:15 Lab

**Note: there are two locations where you will have to change the file location to that of the test computer. One in the Main Method under String fileloc, and one in the MapGUI method fo locationg the image file.

How to Run:
Main Method is Project4.java
-Input: It will ask you for a map. To use ur.txt, enter UR, University of Rochester or Rochester. To use monroe.txt, enter Monroe. To use nys.txt, NY or New York or New York State.
	It will then ask for commands. Use --show and/or --directions. If you choose directions, immediately follow --directions with your two intersection IDs. Keep all commands on the same line separated by a single space.
-Output: For --show, a JFrame will be created with the map. For --directions, a list of all the intersections passed by will be printed. For both, both wil occur, alongside the directional path being drawn.
	 The command line array will also be printed to to make sure no mistakes were made.

Maybe extra credit in making the UR map UR themed?

Classes and Methods:
Project4:
	Main Method
Edge:
	Getters/Setters for edges. Acted for roads.
Intersection: 
	Getters/Setters for intersections, acted as nodes/vertices. Had longitudes and latitudes for calculating distances
Node:
	Getters and setters. Acted as intersections.
MyLinkedList:
	Customized linked list for my node class.
Road:
	Getters and Setters. Acted as edges. Had distances between intesections, intersection IDs and the two intersection points
MapGUI: contains paint class for --show command using 2d graphics, and a scale method for making sure the maps scale when Jframe is resized.

Map: 
	Size method to get size of graph, formPath method to get a String path from an endID using Dijkrstas algorithm, smallestUnknownVertex finds the titular species. dijkstraAlgorithm uses the titular to calculate shortest distance through back tracking. findcost keeps tabs on the distance cost betwen two vertices, insert adds an intersection or road's two intersections to the graph, connected says wheter or not two vertices are connected, roadDist calculates distance on a road, and calcDist translates long and lat distance to meters