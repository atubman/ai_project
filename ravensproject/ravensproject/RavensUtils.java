package ravensproject;

import java.util.HashMap;

public class RavensUtils {
	public HashMap<String,String> DiffRavensObjects(RavensObject fig1, RavensObject fig2){
		HashMap<String, String> diffs = new HashMap<>();
		HashMap<String, String> fig1Atrib = fig1.getAttributes();
		HashMap<String, String> fig2Atrib = fig2.getAttributes();
		for (String key : fig1Atrib.keySet()){
			if(fig2Atrib.containsKey(key)){
				if (fig2Atrib.get(key).compareTo(fig1Atrib.get(key)) != 0) {
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
