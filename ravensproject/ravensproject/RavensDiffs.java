package ravensproject;

import java.util.HashMap;
/*
 * Stores a pair of RavensObjecs and their respective diffs.
 */
public class RavensDiffs {
	public RavensObject objA;
	public RavensObject objB;
	RavensUtils itsUtils;
	public HashMap<String,String> itsDiffs;
	
	public RavensDiffs(	RavensObject a, RavensObject b){
		itsUtils = new RavensUtils();
		objA = a;
		objB = b;
				
	}

	public RavensObject getObjA() {
		return objA;
	}

	public void setObjA(RavensObject objA) {
		this.objA = objA;
	}

	public RavensObject getObjB() {
		return objB;
	}

	public void setObjB(RavensObject objB) {
		this.objB = objB;
	}

	public HashMap<String, String> getItsDiffs() {
		return itsDiffs;
	}

	public void setItsDiffs(HashMap<String, String> itsDiffs) {
		this.itsDiffs = itsDiffs;
	}

	public void ProcessDiffs() {
		
		itsDiffs = itsUtils.NewDiffRavensObjects(objA, objB).getR();
		
		System.out.println("DIFFS " + objA.getName() + "-->" + objB.getName());
		for (String diffy: itsDiffs.keySet()){
			System.out.println(diffy + ":" + itsDiffs.get(diffy));
		}
	}
	
}
