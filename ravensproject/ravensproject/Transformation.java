package ravensproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


// tracks the transformations between 2 frames. should store most of the data that pertains
//to partner objects, diffs, etc.
public class Transformation {
	RavensFigure figureA;
	RavensFigure figureB;
	
	HashMap<String, String> partners;
	ArrayList<RavensDiffs> diffs;
	ArrayList<RavensObject> addedObj;
	ArrayList<RavensObject> removedObj;
	
	RavensUtils itsUtils;
	public Transformation(RavensFigure a, RavensFigure b){
		itsUtils = new RavensUtils();
		figureA = a;
		figureB = b;
		diffs = new ArrayList<>();
		addedObj= new ArrayList<>();
		removedObj= new ArrayList<>();
		
		this.partners = itsUtils.FindPartners(a.getObjects(), b.getObjects());
		processDiffs();
		
	}
	
	public void processDiffs() {
		for (String aObj : partners.keySet()) {

			String bObj = partners.get(aObj);
			if (bObj.compareTo("added") == 0) 
			{
				addedObj.add(figureB.getObjects().get(aObj));
			} 
			else if (bObj.compareTo("removed") == 0) 
			{
				removedObj.add(figureB.getObjects().get(aObj));
			} 
			
			else
			{
				RavensDiffs tempy = new RavensDiffs(figureA.getObjects().get(
						aObj), figureB.getObjects().get(bObj));
				tempy.ProcessDiffs();
				diffs.add(tempy);
			}
		}

	}
	
	public int getNumAdded(){
		return addedObj.size();
	}
	public int getNumRemoved(){
		return removedObj.size();
	}
	
	public ArrayList<RavensDiffs> getDiffs() {
		return diffs;
	}
	
	public void setDiffs(ArrayList<RavensDiffs> diffs) {
		this.diffs = diffs;
	}
	
	public ArrayList<HashMap<String, String>> compareTo(Transformation other) {
		ArrayList<RavensDiffs> myDiffs = diffs;
		ArrayList<RavensDiffs> otherDiffs = other.getDiffs();
		ArrayList<HashMap<String, String>> matchedDiffs = new ArrayList<>();
		int runningSim = 0;
		while (myDiffs.size() > 0 && otherDiffs.size()>0) {
			RavensDiffs tempaDiff = null;
			RavensDiffs tempbDiff = null;
			int simScore = -100;
			for (Iterator<RavensDiffs> aDiffIter = myDiffs.iterator(); aDiffIter.hasNext(); ) {
				RavensDiffs aDiff = aDiffIter.next();
				for (Iterator<RavensDiffs> bDiffIter = otherDiffs.iterator(); bDiffIter.hasNext(); ) {
					RavensDiffs bDiff = bDiffIter.next();
					RavensPair<Integer, HashMap<String, String>> result = itsUtils
							.DiffRavensObjects(aDiff.getItsDiffs(),
									bDiff.getItsDiffs());
					if (result.getR().size() == 0) {
						// Found identicals
						matchedDiffs.add(result.getR());
						aDiffIter.remove();
						bDiffIter.remove();
						tempaDiff = null;
						tempbDiff = null;
						break;
					} else if (result.getL() > simScore) {
						tempaDiff = aDiff;
						tempbDiff = bDiff;
						simScore = result.getL();

					}
				}
			}
			if (tempaDiff != null) {
				matchedDiffs.add(itsUtils.DiffRavensObjects(
						tempaDiff.getItsDiffs(), tempbDiff.getItsDiffs())
						.getR());
				runningSim += itsUtils.DiffRavensObjects(
						tempaDiff.getItsDiffs(), tempbDiff.getItsDiffs())
						.getL();
				boolean resul = myDiffs.remove(tempaDiff);
				otherDiffs.remove(tempbDiff);
				if (!resul) {
					System.out.print("OH NOES!!!");
				}

			}
			

		}
//		System.out.println(otherDiffs.size() + ": "+ myDiffs.size() );
		
		for(RavensDiffs diff : otherDiffs){
			matchedDiffs.add(diff.getItsDiffs());
		}
		for(RavensDiffs diff : myDiffs){
			matchedDiffs.add(diff.getItsDiffs());
		}
		return matchedDiffs;

	}
}
	
	