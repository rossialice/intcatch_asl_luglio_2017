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
import java.awt.geom.Point2D;

public class Pose {
    //variabili d'istanza
    private long timestamp;
    private String q;
    private String zone;
    private Point2D p;
    
    public Pose(long timestamp, Point2D p, String q, String zone){
            this.timestamp=timestamp;
            this.p=p;
            this.q=q;
            this.zone=zone;
         }

    public long getTimestamp(){
            return timestamp;
         }

    public String toString(){
            String s= "Pose(";
            s = s + (timestamp+",");
            s += (p.getX()+"," + p.getY()+",");
            s += (q+"," + zone +")");
            return s;
         }

    public Point2D getP(){
            return p;
         }
}
   
   
