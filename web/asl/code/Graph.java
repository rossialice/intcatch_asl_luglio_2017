/*
 *  Intcatch ASL giugno 2017 Background Subtraction Library multi-thread
 *  Copyright 2017 Domenico Bloisi, Leonardo Dalla Riva, Carlo Bottaro.
 *
 *  This file is part of Intcatch ASL giugno 2017 and it is distributed under the terms of the
 *  GNU Lesser General Public License (Lesser GPL)
 *
 *  
 *
 *  Intcatch ASL giugno 2017 is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Intcatch ASL giugno 2017 is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Intcatch ASL giugno 2017.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  This file contains the C++ OpenCV based implementation for
 *  Intcatch ASL giugno 2017 algorithm described in
 *  
 *  Domenico D. Bloisi, Carlo Bottaro and Leonardo Dalla Riva
 *  "Intcatch ASL giugno 2017"
 *  Pattern Recognition Letters
 *
 *  Please, cite the above paper if you use Intcatch ASL giugno 2017.
 *
 *  Intcatch ASL giugno 2017 has been written by Domenico D. Bloisi, Carlo Bottaro and Leonardo Dalla Riva
 *
 *  Please, report suggestions/comments/bugs to
 *  leo00.dallariva@gmail.com
 *  bottarocarloo@gmail.com
 *
 */

package intcatch;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Graph {
    
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;

    public Graph() {       
	nodes = new ArrayList<Node>();
	edges = new ArrayList<Edge>();
    }
    
    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {       
	this.nodes = nodes;
	this.edges = edges;
    }
    
    public void addNode(double utm_x, double utm_y,
                        int x, int y)
    {
	nodes.add(new Node(nodes.size(), utm_x, utm_y, x, y));
    }
    
    public void addNode(Node n)
    {
        nodes.add(new Node(nodes.size(),
                           n.getUtmX(),
                           n.getUtmY(),
                           n.getX(),
                           n.getY()));
    }
    
    public void addEdge(int i, int j) {
	edges.add(new Edge(i,j));
    }
    
    public void print() {
        System.out.println("Nodes:");
	for (Node n : nodes) {	    
	    System.out.println(n.toString());
	}
        System.out.println();
        System.out.println("Edges:");
	for (Edge e : edges) {
            System.out.println(e.toString());
	}
    }
    
    public ArrayList<Node> getNodes() {
        return nodes;
    }
    
    public ArrayList<Edge> getEdges() {
        return edges;
    }
    
    public static double distanceBetweenUTM(double i_x, double i_y,
                                            double j_x, double j_y)
    {
        return Math.sqrt(Math.pow(i_x - j_x, 2) +
                         Math.pow(i_y - j_y, 2));
    }
    
}
