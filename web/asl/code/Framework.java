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

import java.util.Vector;
import java.util.*;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.geom.Point2D;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;

public class Framework extends JPanel implements ActionListener{
    //Variabile d'istanza
    private ImageIcon mapIcon;
    private BufferedImage mapImage;
    private JLabel mapLabel;
    private JFrame frame;
    private JButton playButton,
                    stopButton,
                    nextButton,
                    mapButton,
                    logButton,
                    graphButton,
                    riconoscimentiButton;
    private JPanel buttonPanel;
    private String currentLogFile;
    private String mapname;
    //actions
    //0 standby
    //1 play
    //2 stop
    //3 next
    private int actions, initialPoint,finishPoint;
    private Vector<Pose> log;
    private Map map;
    
    private GraphDraw graphPanel;
    private JFrame graphFrame;
    
    public Framework(){
        JFrame.setDefaultLookAndFeelDecorated(false);
        frame = new JFrame("IntcatchDEMO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //stand by
        actions = 0;
        log = null;
        
    }
    
    public void addButton(){
        //play
        ImageIcon playButtonIcon = new ImageIcon("images/play.png");
        playButton = new JButton(playButtonIcon);
        playButton.setActionCommand("play");
        playButton.addActionListener(this);
        buttonPanel =new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(playButton);
        //stop
        ImageIcon stopButtonIcon = new ImageIcon("images/stop.png");
        stopButton = new JButton(stopButtonIcon);
        stopButton.setActionCommand("stop");
        stopButton.addActionListener(this);
        buttonPanel.add(stopButton);
        //next
        ImageIcon nextButtonIcon = new ImageIcon("images/next.png");
        nextButton = new JButton(nextButtonIcon);
        nextButton.setActionCommand("next");
        nextButton.addActionListener(this);
        buttonPanel.add(nextButton);
        //button map
        ImageIcon mapButtonIcon = new ImageIcon("images/map.png");
        mapButton = new JButton(mapButtonIcon);
        mapButton.setActionCommand("map");
        mapButton.addActionListener(this);
        buttonPanel.add(mapButton);
        //button images
        ImageIcon logButtonIcon = new ImageIcon("images/log.png");
        logButton = new JButton(logButtonIcon);
        logButton.setActionCommand("log");
        logButton.addActionListener(this);
        buttonPanel.add(logButton);
        //graph button
        ImageIcon graphButtonIcon = new ImageIcon("images/graph.png");
        graphButton = new JButton(graphButtonIcon);
        graphButton.setActionCommand("graph");
        graphButton.addActionListener(this);
        buttonPanel.add(graphButton);
        //riconoscimenti button
        ImageIcon riconoscimentiButtonIcon = new ImageIcon("images/rico.png");
        riconoscimentiButton = new JButton(riconoscimentiButtonIcon);
        riconoscimentiButton.setActionCommand("rico");
        riconoscimentiButton.addActionListener(this);
        buttonPanel.add(riconoscimentiButton);
        //altro
        frame.getContentPane().add(buttonPanel,BorderLayout.NORTH);
        frame.setVisible(true);
        frame.repaint();  
        mapButton.setEnabled(false);
        graphButton.setEnabled(false);
    }
    
    public void addMap(){
        mapImage= map.getMapImage();
        mapIcon=new ImageIcon(mapImage);
        mapLabel=new JLabel(mapIcon);
        frame.getContentPane().add(mapLabel,BorderLayout.CENTER);
        frame.getContentPane().add(new JScrollPane(mapLabel), BorderLayout.CENTER);
        frame.setVisible(true);       
    }
    
    public void drawMap(Map map,Vector<Point2D> vector){
    //drawMap
    }
    
    public int run(){
         while(actions==0){
            try{
                Thread.sleep(300);
            }
            catch(InterruptedException e) {}
        }    
        if(log==null){
            
            //condizione valida se il log non viene caricato
            if(actions==1){
                String message = "selezionare un log prima di premere play.";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
                actions=0;
                playButton.setEnabled(true);
                return(-1);
            }     
            if(actions==2){
            //condizione valida se attivo stop prima di play
                if(actions==0){
                String message = "selezionare prima play di stop!";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
                actions=0;
                return(-1);
                }
            }
            if(actions==3){
            //condizione valida se attivo stop prima di play
                if(actions==0){
                String message = "selezionare il LOG!";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
                actions=0;
                return(-1);
                }
            }
        }
        else{
            mapButton.setEnabled(false);
            
        }
               
                
                Iterator<Pose> it= log.iterator();
               
                Graphics2D g2d = mapImage.createGraphics();
                Point2D currentPoint=null;
                Point2D previusPoint=null;
                double previusAng=0;
                double angolo=0;
                int colore=0;
                while(it.hasNext()){
                    
                    while((actions==1)&&(it.hasNext())){
                       
                        Pose p = it.next();
                        Point2D puntoImmagine = map.convert(p);
                        previusPoint=currentPoint;
                        currentPoint=puntoImmagine;
                        previusAng=angolo;
                        angolo=angleColor(previusPoint,currentPoint);
                        colore=angle(angolo,previusAng,colore);
                        if(colore==1){
                            g2d.setColor(Color.red);
                        }
                        if(colore==2){
                            g2d.setColor(Color.black);       
                        }
                        if(colore==3){
                            g2d.setColor(Color.blue);
                        }
                        
                        int punto_x = (int)(puntoImmagine.getX());
                        int punto_y = (int)(puntoImmagine.getY());
                        
                        /*
                        cambiare
                        
                        punto_x += 13700;
                        punto_x *= 3;
                        punto_y += 41200;
                        punto_y *= 3;
                        */
                        
                        g2d.fillOval(punto_x, punto_y, 4, 4);
                        System.out.println("punto immagine  "  + punto_x + " " + punto_y);
                        frame.revalidate();
                        frame.repaint();
                    }
                    while(actions==2){
                            stopButton.setEnabled(false);
                            try{
                                Thread.sleep(30);
                            }
                            catch(InterruptedException e){}
                    }
                    //NEXTTTTTT
                    if(actions==3){
                        actions=3;
                        while((actions==3)&&(it.hasNext())){
                           Pose p = it.next();
                        Point2D puntoImmagine = map.convert(p);
                        previusPoint=currentPoint;
                        currentPoint=puntoImmagine;
                        previusAng=angolo;
                        angolo=angleColor(previusPoint,currentPoint);
                        colore=angle(angolo,previusAng,colore);
                        if(colore==1){
                            g2d.setColor(Color.red);
                        }
                        if(colore==2){
                            g2d.setColor(Color.black);       
                        }
                        if(colore==3){
                            g2d.setColor(Color.blue);
                        }
                        g2d.fillOval((int)(puntoImmagine.getX()), (int)(puntoImmagine.getY()), 4, 4);
                        frame.revalidate();
                        frame.repaint();
                        actions=0;
                        while(actions==0){
                            try{
                                Thread.sleep(30);
                            }
                            catch(InterruptedException e){}
                        }
                        }  
                    }
                    
                }
                int reply = JOptionPane.showConfirmDialog(null, "RICARICARE?", "---", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    setVisible(false); //you can't see me!
                    frame.dispose(); //Destroy the JFrame object
                    return -2;
                    
                }
                else {
                   //JOptionPane.showMessageDialog(null, "OTTIMA SCELTA");
                }
        
                
                return 0;
        }
    
    public double angleColor(Point2D b,Point2D a){
        //1 dritto rosso
        //2 destra verde
        //3 sinistra blu
        if(a==null||b==null){
            return 1;
        }
        double ax=a.getX();
        double ay=a.getY();
        double bx=b.getX();
        double by=b.getY();
        double ang=Math.tan(ax-bx/ay-by);
        if(ang<0){
            ang+=360;
        }
        System.out.println("ang: " + ang);
        return ang;
        
        
    }
    public int angle(double ang, double pAng, int cl){
        
        
        if(ang==pAng){
            
            return cl;
        }
        else {
            if((ang<2||ang>358)||(ang>178&&ang<182)){
               // System.out.println("1");
                return 1;
            }
            else{
                if(ang>180){
                 //   System.out.println("2");
                    return 2;
                }
                else{
                   // System.out.println("3");
                    return 3;
                }
            }
        }
          
            
          
    }
    
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        System.out.println("command: " + command);
        if(command.equals("play")){
            playButton.setEnabled(false);
            stopButton.setEnabled(true);
            nextButton.setEnabled(true);
            
            actions= 1;

        }else if(command.equals("stop")){
            playButton.setEnabled(true);
            stopButton.setEnabled(false);
            nextButton.setEnabled(true);
            actions= 2;

        }
        else if(command.equals("next")){
            playButton.setEnabled(true);
            stopButton.setEnabled(true);
            nextButton.setEnabled(true);
            actions= 3;
        }
        else if(command.equals("map")){
            mapChooser();
            graphButton.setEnabled(true);
            mapButton.setEnabled(false);
        }
        else if(command.equals("log")){
            fileChooser();
            mapButton.setEnabled(true);  
            logButton.setEnabled(false);
        }
        else if(command.equals("graph")){
            Graph graph=  createGraph();
           
            initialPoint=valore("initial point");
            finishPoint=valore("final point");
            runDijkstra(graph);
        }
        else if(command.equals("rico")){
            rico();
        }
         
    }
 
    public void mapChooser() {
        try {        
           JFileChooser fileChooser = new JFileChooser("./dati");
           int n = fileChooser.showOpenDialog(Framework.this);
           if (n == JFileChooser.APPROVE_OPTION) {
               File f = fileChooser.getSelectedFile();
               mapname=f.toString();
               MapReader mr = new MapReader(mapname);
               map = mr.getMap();
               addMap();
               }
               }
        catch (Exception ex) {
           } 
    }
    public void rico() {
        JOptionPane.showMessageDialog(frame,
    "Programma sviluppato da \nCarloBottaro, LeonardoDallaRiva e DomenicoBloisi",
    "CREDITI.",
    JOptionPane.PLAIN_MESSAGE);
    }
 
    public void fileChooser() {  
        try {       
            JFileChooser fileChooser = new JFileChooser("./Dati");
            int n = fileChooser.showOpenDialog(Framework.this);
            if (n == JFileChooser.APPROVE_OPTION) {
                File f = fileChooser.getSelectedFile();
                currentLogFile = new String(f.toString());
                LogReader lr = new LogReader(currentLogFile);
                
                if(lr.read()){
                   System.out.print("Reading log...");
                   System.out.flush();
                }
                else {
                   System.out.println("Error in reading log"); 
                   return;
                }
                
                System.out.println("[OK]");
                
                log = lr.getVector();

                log = riduciLog(log, 30);
                
            }
        } 
        catch (Exception ex) {
        }
    }
    
    public Graph createGraph() {
        int cont=0;
       
        //progressPanel =new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        graphFrame = new JFrame("Grafo");
                
        graphFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        graphPanel= new GraphDraw(2);
       
        graphFrame.setSize((int)(mapImage.getWidth()*1.3),
                               (int)(mapImage.getHeight()*1.3));
       
        JProgressBar progressBar = new JProgressBar(0, 100);
        JFrame progressFrame= new JFrame();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        JPanel panel = new JPanel();
        panel.add(progressBar);
        graphFrame.getContentPane().add(panel,BorderLayout.NORTH);
        graphFrame.getContentPane().add(graphPanel,BorderLayout.CENTER);
        //panel.add(progressBar);
        progressFrame.getContentPane().add(panel,BorderLayout.NORTH);
        progressFrame.setSize(300, 300);
        progressFrame.setVisible(false);
        progressFrame.repaint();
        graphFrame.setVisible(true);
        double temp_x=0,temp_y=0;
        double max=-1;

        Iterator<Pose> it = log.iterator();
        while(it.hasNext()) {
            Pose pose = it.next();
            Point2D point = map.convert(pose);
            double x = point.getX();
            double y = point.getY();
                        cont++;
                        //System.out.println("cont: "+ cont);
                        progressBar.setValue(cont);
                        progressBar.setVisible(false);
            ArrayList<Node> nodes = graphPanel.getNodes();
            Node n = new Node(nodes.size(),
                point.getX(),
                point.getY(),
                (int)x,
                (int)y);
                temp_x=point.getX();
                temp_y=point.getY();
                Pose oldpose=null;
            for(int i=0;i<log.size();i++)
            {
                
                Pose p= log.get(i);
                if(oldpose==null)
                {
                    oldpose=p;
                }
                else
                {
                    Point2D p1 = map.convert(p);
                    Point2D p2 = map.convert(oldpose);
                    double d = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) +
                         Math.pow(p1.getY() - p2.getY(), 2));
                    //System.out.println("d: "+ d);
                    if(max<d)
                    {
                        max=d;
                        //System.out.println("max: "+ max);
                    }
                    oldpose=p;
                }    
            }
            for (Node node : nodes) {
               // System.out.println(node.toString());
                double d = GraphDraw.distanceBetweenUTM( x, y, node.getX(), node.getY());
                //System.out.println("d: "+ d);
                if(d <= max && d >= 0) {
                    graphPanel.addEdge(node.getIdx(), n.getIdx());
                }
            }
            graphPanel.addNode(n);
            
            //wait
            try{
                Thread.sleep(30);
            }
            catch(InterruptedException ie) {

            }
            
            graphFrame.revalidate();
            graphFrame.repaint();
        }
        
        Graph graph = generateGraph(log, max);
        graph.print();
        return graph;
        
    }
    
    private void runDijkstra(Graph graph){
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        ArrayList<Node> nodes = graph.getNodes();
        dijkstra.execute(nodes.get(initialPoint));
        LinkedList<Node> path = dijkstra.getPath(nodes.get(finishPoint));
        System.out.println("getpathortest Path:");
        for (Node n : path) {
            System.out.println(n.toString());
        }        
    }
    
    private Vector<Pose> riduciLog(Vector<Pose> log, int new_size){
        Vector<Pose> ridotto=new Vector<Pose>();
        Iterator<Pose> it = log.iterator();
         
        int skip_value= log.size()/new_size;
        while(it.hasNext()){
            ridotto.add(it.next());
            for(int i=0;i<skip_value;i++){
                if(it.hasNext()){
                    it.next();
                }
                else{
                    break;
                }
            }
        }        
        return ridotto;
    }
    
    private Graph generateGraph(Vector<Pose> poses, double max_dist)
    {
        ArrayList<Node> nodes = new ArrayList<Node>();        
        Iterator<Pose> it = poses.iterator();
        int i = 0;
        while(it.hasNext()){            
            Pose pose = it.next();
            Point2D point = map.convert(pose);
            double x = point.getX();
            double y = point.getY();
            Node location = new Node(String.valueOf(i),
                                    String.valueOf(i),
                                    nodes.size(),
                                    point.getX(),
                                    point.getY(),
                                    (int)x,
                                    (int)y);
            
            
            System.out.println("Nodo " + i + " UTM " + pose.getP());
            
            nodes.add(location);
            i++;
        }
        
        ArrayList<Edge> edges = new ArrayList<Edge>();
        
        int j = 0;
        for (Node n : nodes) {
            for (Node m : nodes) {
                double d = Graph.distanceBetweenUTM(n.getX(),
                                                    n.getY(),
                                                    m.getX(),
                                                    m.getY());
                if(d <= max_dist && d > 0) {
                                       
                    Edge e = new Edge(  String.valueOf(j),
                                        nodes.get(n.getIdx()),
                                        nodes.get(m.getIdx()),
                                        d);
                    edges.add(e);
                    j++;
                }
            }
        }
               
        return new Graph(nodes, edges);
        
    }
    
    public int valore(String input){
        Object[] possibilities = {"1","2","3","4","5","6","7","8","9", "10","11","12","13","14","15","16","17","18","19", "20","26"};
        String s = (String)JOptionPane.showInputDialog(
                    frame,
                    "Choose a number: \n it's for DijkstraAlgorithm.."
                    + input,
                    "   ",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    "10");
        int b = Integer.parseInt(s);

        return b;
    }
}


