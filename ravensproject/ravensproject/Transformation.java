package ravensproject;

import java.util.HashMap;


// tracks the transformations between 2 frames. should store most of the data that pertains
//to partner objects, diffs, etc.
public class Transformation {
	RavensFigure figureA;
	RavensFigure figureB;
	
	HashMap<String, String> partners;
	RavensDiffs diffs;
	
	RavensUtils itsUtils;
	public Transformation(RavensFigure a, RavensFigure b){
		itsUtils = new RavensUtils();
		figureA = a;
		figureB = b;
		this.partners = itsUtils.FindPartners(a.getObjects(), b.getObjects());
	}
	
	public void getDiffs(){
		for( String aObj : partners.keySet()){
			String bObj = partners.get(aObj);
	
				itsUtils.NewDiffRavensObjects(figureA.getObjects().get(aObj), figureA.getObjects().get( bObj));
		
		}
	}
	
	
	
}

	