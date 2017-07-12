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
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.Vector;
import java.awt.geom.Point2D;
import java.lang.*;
import java.lang.Double;

public class LogReader {
    private String nomefile;
    private Vector<Pose> v;
    
    public LogReader(String nf){
        nomefile=nf;
        v= new Vector();
    }
    
    public boolean read(){
        FileReader f;
        try{
            f=new FileReader(nomefile);
            //System.out.println("file: " +nomefile);
        }
        catch (FileNotFoundException e) {
            System.err.println("non trovo il file " +nomefile);
            return false;
        }
        BufferedReader b;
        b=new BufferedReader(f);
        try{
            String riga=b.readLine();
                while(riga!=null){
                    
                    
                    parse(riga);
                    riga=b.readLine();
                }
            }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        
        
        return true;
    }
    public void parse(String riga){
        int index=riga.indexOf("I");
        if(index<0){
            System.err.println("Errore logfile non trovo I");
            System.exit(-1);
        }
        String ss= riga.substring(0,index);
        long timestamp=Long.valueOf(ss.trim()).longValue();
        int index2=riga.indexOf("pose");
        if(index2>0)
        {
            String sp= riga.substring(index2+6);
            //System.out.println("sp: "+sp);
            String x= sp.substring(sp.indexOf("p")+4, sp.indexOf(","));
            //System.out.println("x: "+x);
            String temp=sp.substring(sp.indexOf(",")+1);
            String y= temp.substring(0, temp.indexOf(","));
            //System.out.println("y: "+y);
            double xd=Double.valueOf(x);
            //System.out.println("xd: "+xd);
            double yd=Double.valueOf(y);
            //System.out.println("yd: "+yd);
            Point2D p= new Point2D.Double(xd,yd);
            String q= temp.substring(temp.indexOf("q")+4, temp.indexOf("z")-3);
            //System.out.println("q: "+q);
            String z= temp.substring(temp.indexOf("z")+7,temp.indexOf("\"}"));
            //System.out.println("z: "+z);
            if(z.equals("32North")){
                Pose pose= new Pose(timestamp,p, q, z);
                v.add(pose);
            }       
        }
    }
   
    public Vector<Pose> getVector() {
        return v;
    }   
}