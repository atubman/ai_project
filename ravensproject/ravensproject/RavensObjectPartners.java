package ravensproject;

import java.util.HashMap;

/*
 * RavensObjectTransform.java
 * 
 * Keeps track of the artners and diffs between 2 Ravens objects.
 * 
 */

public class RavensObjectPartners {
    private String nameObj1;
    private String nameObj2;
    HashMap<String, String> partners;
  
    public RavensObjectPartners(String name1,String name2, HashMap<String, String> diffs) {
        this.nameObj1=name1;
        this.nameObj2=name2;
        partners =diffs;
    }
    public RavensObjectPartners(String name1,String name2) {
    	this.nameObj1=name1;
    	this.nameObj2=name2;
    	partners =new HashMap<>();
    }

	public String getNameObj1() {
		return nameObj1;
	}

	public void setNameObj1(String nameObj1) {
		this.nameObj1 = nameObj1;
	}

	public String getNameObj2() {
		return nameObj2;
	}

	public void setNameObj2(String nameObj2) {
		this.nameObj2 = nameObj2;
	}

	public HashMap<String, String> getItsDiffs() {
		return partners;
	}

	public void setItsPartners(HashMap<String, String> itsPartners) {
		this.partners = itsPartners;
//		System.out.println("Setting Partners");
//		for (String part: itsPartners.keySet()){
//			System.out.print(" " + part + "," + partners.get(part));
//			
//		}
//		System.out.println("");
	}


}
