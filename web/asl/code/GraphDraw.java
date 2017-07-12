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

public class GraphDraw extends JPanel {
    int width;
    int height;
    int a=0;
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    
    double SCALE;

    public GraphDraw() { //Constructor
        
        
	nodes = new ArrayList<Node>();
	edges = new ArrayList<Edge>();
	width = 30;
	height = 30;
        SCALE = 20.;
    }
    
    public GraphDraw(double scale) { //Constructor
        
        
	nodes = new ArrayList<Node>();
	edges = new ArrayList<Edge>();
	width = 30;
	height = 30;
        SCALE = scale;
    }
    
    public void addNode(double utm_x, double utm_y,
                        int x, int y)
    { 
	//add a node at pixel (x,y)
	nodes.add(new Node(nodes.size(), utm_x, utm_y, x, y));
	this.repaint();
    }
    
    public void addNode(Node n)
    { 
	//add a node at pixel (x,y)
	nodes.add(new Node(nodes.size(),
                           n.getUtmX(),
                           n.getUtmY(),
                           n.getX(),
                           n.getY()));
	this.repaint();
    }
    
    public void addEdge(int i, int j) {
	//add an edge between nodes i and j
	edges.add(new Edge(i,j));
	this.repaint();
    }
    
    public void paint(Graphics g) { // draw the nodes and edges
	
               
	g.setColor(Color.red);
	for (Edge e : edges) {
            
            int xi = (int)(nodes.get(e.getI()).x*SCALE);
            int yi = (int)(nodes.get(e.getI()).y*SCALE);
            int xj = (int)(nodes.get(e.getJ()).x*SCALE);
            int yj = (int)(nodes.get(e.getJ()).y*SCALE);
            
            
           
            //System.out.println("yi: " + yi);
            //System.out.println("xj: " + xj);
            //System.out.println("yj: " + yj);
            
            
            
            //double theta = Math.PI/2;    
        
            int xip = xi; 
                    //(int)(Math.cos(theta) * xi - Math.sin(theta) * yi);
            int yip = yi;
                    //(int)(Math.sin(theta) * xi + Math.cos(theta) * yi);
            
            xip -= 500;
            yip -= 300;
            
            /*
            System.out.println("xip: " + xip);
            System.out.println("yip: " + yip);
            */
            
            int xjp = xj;
                    //(int)(Math.cos(theta) * xj - Math.sin(theta) * yj);
            int yjp = yj;
                    //(int)(Math.sin(theta) * xj + Math.cos(theta) * yj);
            
            xjp -= 500;
            yjp -= 300;
            
            
            //System.out.println("xjp: " + xjp);
            //System.out.println("yjp: " + yjp);
            
            
	    g.drawLine(xip, yip, xjp, yjp);
	}
        g.setColor(Color.black);
	for (Node n : nodes) {	    
	    
            
            
            int x = (int)(n.x*SCALE);
            int y = (int)(n.y*SCALE);
            
            //double theta = Math.PI/2;    
        
            int xp = x;
                    //(int)(Math.cos(theta) * x - Math.sin(theta) * y);
            int yp = y;
                    //(int)(Math.sin(theta) * x + Math.cos(theta) * y);
            
            xp -= 500;
            yp -= 300;
            
            
            String m=null;
            m= String.valueOf(a).toString();
            g.fillOval(xp, yp, 4, 4);
            g.setFont(new Font("Futura", Font.PLAIN, 16)); 
	    g.drawString(m , xp+8, yp+8);
            a++;
	}
    }
    
    public ArrayList<Node> getNodes() {
        return nodes;
    }
    
    public static double distanceBetweenUTM(double i_x, double i_y,
                                            double j_x, double j_y)
    {
        return Math.sqrt(Math.pow(i_x - j_x, 2) +
                         Math.pow(i_y - j_y, 2));
    }
}
