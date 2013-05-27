package xmlLevelEncoder;

import java.util.ArrayList;

public class ObjectL {

	private ArrayList<Pair> al;
	public static final int MAX_OBJECT = 8;
	public static final int MAX_LAYER = 10;
	public static final int MIN_HEIGHT_PLATEFORM = 3;
	public static final int MAX_HEIGHT_PLATEFORM = 10;
	public static final int MAX_OBJECT_BY_ELEMENT = 13;
	
	public ObjectL(){
		this.al = new ArrayList<>();
	}

	public void add(String object, String layer){
		if(al.size()<=MAX_OBJECT_BY_ELEMENT){
			
			int objectI = Integer.parseInt(object);
			int layerI = Integer.parseInt(layer);
			
			if(objectI <= MAX_OBJECT || layerI <= MAX_LAYER){
				if(objectI == 5){
					if(layerI >= MIN_HEIGHT_PLATEFORM && layerI <= MAX_HEIGHT_PLATEFORM)
						this.al.add(new Pair(object, layer));
				}
				else{
					this.al.add(new Pair(object, layer));
				}
			}
		}
	}

	public ArrayList<Pair> get(){
		return this.al;
	}
}
