package xmlLevelEncoder;

import java.util.ArrayList;

public class ObjectL {

	ArrayList<Pair> al;
	
	public ObjectL(){
		this.al = new ArrayList<>();
	}
	
	public void add(String object, String layer){
		this.al.add(new Pair(object, layer));
	}
	
	public ArrayList<Pair> get(){
		return this.al;
	}
}
