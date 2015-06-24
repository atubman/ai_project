package ravensproject;

import java.util.HashMap;

/*
 * RavensObjectTransform.java
 * 
 * Keeps track of the partners and diffs between 2 Ravens Figures. This was used for 2x2 only.
 * 
 */

public class RavensFigurePartners {
    private String nameObj1;
    private String nameObj2;
    private int simScore;
    HashMap<String, String> partners;
  
    public RavensFigurePartners(String name1,String name2, HashMap<String, String> diffs) {
        this.nameObj1=name1;
        this.nameObj2=name2;
        partners =diffs;
        simScore = 0;
    }
    public RavensFigurePartners(String name1,String name2) {
    	this.nameObj1=name1;
    	this.nameObj2=name2;
    	partners =new HashMap<>();
    	simScore = 0;
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
	public int getSimScore() {
		return simScore;
	}
	public void setSimScore(int simScore) {
		this.simScore = simScore;
	}


}
