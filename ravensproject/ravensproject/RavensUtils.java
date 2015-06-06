package ravensproject;

import java.util.HashMap;

public class RavensUtils {
	public static final String ANGLE = "angle";
	public static final String ABOVE = "above";
	public static final String INSIDE = "inside";
	
	public HashMap<String,String> DiffRavensObjects(RavensObject fig1, RavensObject fig2){
		HashMap<String, String> diffs = new HashMap<>();
		HashMap<String, String> fig1Atrib = fig1.getAttributes();
		HashMap<String, String> fig2Atrib = fig2.getAttributes();
		for (String key : fig1Atrib.keySet()){
			if(fig2Atrib.containsKey(key)){
				//if the shape was rotated or reflected, we need to know the difference in angle, no jus tthe second value.
				if(key.equals(ANGLE)){
					int angleChange = Math.abs(Integer.parseInt(fig1Atrib.get(key)) - Integer.parseInt(fig2Atrib.get(key)));
					System.out.println("Angle diff:" + angleChange);
					diffs.put(ANGLE, angleChange+"");
				}
				else if(key.equals(ABOVE)){
					
					//we dont really care who it's above when we diff it, just taht it's still above something
					int numAAbove = 0;
					int numBAbove = 0;
					numAAbove = fig1Atrib.get(key).split(",").length;
					numBAbove = fig2Atrib.get(key).split(",").length;
					if(numAAbove != numBAbove){
						diffs.put(ABOVE, numBAbove + "");
					}
				}
				else if(key.equals(INSIDE)){
					
					//we dont really care who it's above when we diff it, just taht it's still above something
					int numAAbove = 0;
					int numBAbove = 0;
					numAAbove = fig1Atrib.get(key).split(",").length;
					numBAbove = fig2Atrib.get(key).split(",").length;
					if(numAAbove != numBAbove){
						diffs.put(INSIDE, numBAbove + "");
					}
				}
				else if (fig2Atrib.get(key).compareTo(fig1Atrib.get(key)) != 0) {
					diffs.put(key, fig2Atrib.get(key));
					
				}
			} else {
				//figure 1 has extra attribute
				diffs.put(key, fig1Atrib.get(key));
			}
		}
		
		for(String key : fig2Atrib.keySet()){
			if(!fig1Atrib.containsKey(key)){
				//fig 2 has a key that fig 1 doesnt, it's a diff!!!
				diffs.put(key, fig2Atrib.get(key));
				
			}
		}
		return diffs;	
	}
}
