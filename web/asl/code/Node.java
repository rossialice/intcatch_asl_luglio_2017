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

class Node {
    int x, y; //coordinate immagine
    double utm_x, utm_y; //coordinate UTM
    int idx; //indice del nodo
    final private String id;
    final private String name;


    public Node(int _idx,
                double _utm_x,
                double _utm_y,
                int _x,
                int _y)
    {
       // System.out.println("Sono nell node");
        idx = _idx;
        utm_x = _utm_x;
        utm_y = _utm_y;
        x = _x;
        y = _y;
        id = null;
        name = null;
    }
    
    public Node(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Node(String _id,
                String _name,
                int _idx,
                double _utm_x,
                double _utm_y,
                int _x,
                int _y)
    {
        id = _id;
        name = _name;
        idx = _idx;
        utm_x = _utm_x;
        utm_y = _utm_y;
        x = _x;
        y = _y;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }    

    public String toString() {
        return "id: " + id + " name: " + name + " utm: " + utm_x + " " +
                utm_y;
    }

    public int getIdx() {
        return idx;
    }

    public double getUtmX() {
        return utm_x;
    }

    public double getUtmY() {
        return utm_y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
