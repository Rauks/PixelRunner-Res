package xmlLevelEncoder;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Main
{
   static Element root = new Element("lvl");

   static Document document = new Document(root);

   public static void main(String[] args)
   {
	   
	  Attribute s = new Attribute("s","1");
	  root.setAttribute(s);
	   
      Element e1 = new Element("e");
      root.addContent(e1);
      
      Element o1 = new Element("o");
      e1.addContent(o1);
      Attribute t1 = new Attribute("t","0");
      Attribute l1 = new Attribute("l","0");
      o1.setAttribute(t1);
      o1.setAttribute(l1);
      
      Element o2 = new Element("o");
      e1.addContent(o2);
      Attribute t2 = new Attribute("t","1");
      Attribute l2 = new Attribute("l","1");
      o2.setAttribute(t2);
      o2.setAttribute(l2);     

      Element e2 = new Element("e");
      root.addContent(e2);
      
      try
      {
         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
         sortie.output(document, System.out);
         sortie.output(document, new FileOutputStream("lvl.xml"));
      }
      catch (IOException exc){}
   }
}