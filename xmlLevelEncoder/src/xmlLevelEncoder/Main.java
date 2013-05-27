package xmlLevelEncoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Main
{
	static Element root = new Element("lvl");
	static Document document = new Document(root);
	static ArrayList<Element> eList = new ArrayList<>();
	
	public static void main(String[] args)
	{

		Attribute s = new Attribute("s","1");
		root.setAttribute(s);		
		
		ObjectL oList = new ObjectL();
		oList.add("0", "0");
		oList.add("1", "1");
		oList.add("2", "3");
		Element e1 = new Element("e");
		eList.add(putObjectIntoE(e1, oList));
		
		eList.add(new Element("e"));
		
		putElementIntoRoot();
		
		try
		{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
			sortie.output(document, new FileOutputStream("lvl.xml"));
		}
		catch (IOException exc){}
	}

	private static void putElementIntoRoot() {
		for(Iterator i = eList.iterator(); i.hasNext();){
			root.addContent((Element) i.next());
		}
	}

	private static Element putObjectIntoE(Element e1, ObjectL oList) {
		for(Iterator i = oList.get().iterator(); i.hasNext();){
			Pair pair = (Pair) i.next();
			Element o = new Element("o");
			o.setAttribute(new Attribute("t",pair.getFirst()));
			o.setAttribute(new Attribute("l",pair.getSecond()));
			e1.addContent(o);
		}
		return e1;
	}
}