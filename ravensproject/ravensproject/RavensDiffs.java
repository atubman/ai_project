package ravensproject;

import java.util.HashMap;

public class RavensDiffs {
	public RavensObject objA;
	public RavensObject objB;
	
	public HashMap<String,String> itsDiffs;
	
	public RavensDiffs(	RavensObject objA, RavensObject objB){
		
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
	
}
