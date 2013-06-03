/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlleveltool;

import java.util.ArrayList;
import java.util.Iterator;
import xmlleveltool.LevelElement.LevelTypes;

/**
 *
 * @author Karl
 */
public class ObjectsList {
    private ArrayList<LevelElement> al;
    public int index = 0;

    public ObjectsList(){
            this.al = new ArrayList<>();
    }

    public ArrayList<LevelElement> getList(){
        return this.al;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.index).append(". ");
        for(Iterator<LevelElement> it = this.al.iterator(); it.hasNext();){
            sb.append(it.next().toString());
            if(it.hasNext()){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
