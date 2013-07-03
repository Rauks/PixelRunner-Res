/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package randomgen;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import xmlleveltool.LevelElement;
import xmlleveltool.ObjectsList;

/**
 *
 * @author Karl
 */
public class Generator {
    private ArrayList<ObjectsList> objetcsList = new ArrayList<>();
    private Random rand = new Random();
    
    public static void main(String[] args){
        Generator gen = new Generator();
        gen.generate(37);
        gen.build(System.out);
    }
    
    public void generate(int nbSteps){
        for(int i = 0; i < nbSteps; i++){
            this.objetcsList.add(this.getRandomObjectsList());
        }
    }
    private ObjectsList getRandomObjectsList(){
        ObjectsList list = new ObjectsList();
        try {
            switch(this.rand.nextInt(5)){
                case 1:
                    list.getList().add(new LevelElement(LevelElement.LevelTypes.ROCKET, 0));
                    break;
                case 2:
                    list.getList().add(new LevelElement(LevelElement.LevelTypes.TRAP, 0));
                    break;
                case 3:
                    list.getList().add(new LevelElement(LevelElement.LevelTypes.WALL, 0));
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void build(OutputStream out){
        try {
            Element root = new Element("lvl");
            Attribute s = new Attribute("s", "1");
            root.setAttribute(s);
            
            Document document = new Document(root);
            
            for(ObjectsList ol : this.objetcsList){
                Element e = new Element("e");
                for(LevelElement le : ol.getList()){
                    Element o = new Element("o");
                    o.setAttribute(new Attribute("t", String.valueOf(le.getType().getId())));
                    o.setAttribute(new Attribute("l", String.valueOf(le.getLayer())));
                    e.getChildren().add(o);
                }
                root.getChildren().add(e);
            }

            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, out);
        } catch (IOException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
