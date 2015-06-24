package ravensproject;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;

public class PartnerSortUtil {

//	ArrayList<RavensTriple> allCombos;
	// <aValue>, <Bvalue,diffs>
	HashMap<String,HashMap<String,Integer>> allCombos;

	public PartnerSortUtil(HashMap<String, RavensObject> objA,
			HashMap<String, RavensObject> objB) {
		allCombos = new HashMap<>();
		RavensUtils itsUtils = new RavensUtils();
		for (String aObj : objA.keySet()) {
			HashMap<String,Integer> tempHash = new HashMap<String, Integer>();
			for (String bObj : objB.keySet()) {
				HashMap<String, String> diffs = itsUtils.DiffRavensObjects(
						objA.get(aObj), objB.get(bObj)).getR();
				
				tempHash.put( bObj, diffs.size());
			}
			allCombos.put(aObj,tempHash);
		}
	}
	
	public int GetDiffs(String a, String b){
		return allCombos.get(a).get(b);
	}
	public ArrayList<RavensPair<String, String>> getAllVals(int val){
		ArrayList<RavensPair<String, String>> rtrn = new ArrayList<RavensPair<String, String>>(); 
		for (String a : allCombos.keySet()){
			for (String b : allCombos.get(a).keySet()){
				if(allCombos.get(a).get(b) == val){
					rtrn.add(new RavensPair<String, String>(a, b));
				}
			}
		}
		return rtrn;
	}
	public void RmContaining(String rm){
		allCombos.remove(rm);
		for(String a : allCombos.keySet()){
			allCombos.get(a).remove(rm);
		}
	}
//	public ArrayList<String> getEmpties(){
//		for()
//	}
	public int getComboSize(){
		
//		for()
		return allCombos.size();
	}
	public Boolean hasObj(String obj){
		for(String a: allCombos.keySet()){
			if(a.equals(obj)){
				return true;
			} else{
				for(String b : allCombos.get(a).keySet()){
					if(b.equals(obj)){
						return true;
					}
				}
			}
		}
		return false;
	}
}
