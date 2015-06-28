package ravensproject;

public class MiniRavensObject {
// A ravens object that really only takes into account size shape and fill
	//used in comparisons of clones that are accumulating
	
	String itsShape;
	String itsFill;
	String itsSize;
	String itsWidth;
	String itsHeight;
	
	public MiniRavensObject(String size, String shape, String fill){
		itsSize = size;
		itsShape = shape;	
		itsFill = fill;
	}
	public MiniRavensObject(RavensObject obj){
		itsShape = obj.getAttributes().get("shape");	
		itsFill = obj.getAttributes().get("fill");

		itsSize = obj.getAttributes().get("size");
		if(itsSize == null){
			itsWidth = obj.getAttributes().get("width");
			itsWidth = obj.getAttributes().get("height");
		}
	}
	public String getItsSize() {
		return itsSize;
	}
	
	
	public void setItsSize(String itsSize) {
		this.itsSize = itsSize;
	}
	
	
	public String getItsShape() {
		return itsShape;
	}
	
	
	public void setItsShape(String itsShape) {
		this.itsShape = itsShape;
	}
	
	
	public String getItsFill() {
		return itsFill;
	}
	
	
	public void setItsFill(String itsFill) {
		this.itsFill = itsFill;
	}
	//returns true if they equals
	public boolean equalsOther(MiniRavensObject otherObj){
		if(itsSize != null && otherObj.getItsSize() !=null){
		return ((otherObj.getItsSize().compareTo(itsSize)==0) 
				&& otherObj.getItsFill().compareTo(itsFill) ==0 
				&& otherObj.getItsShape().compareTo(itsShape) ==0);
		} else{
			return false;
		}
	}
	public boolean equalsOtherNoSize(MiniRavensObject otherObj){
		return (otherObj.getItsFill().compareTo(itsFill) ==0 
				&& otherObj.getItsShape().compareTo(itsShape) ==0);
	}
	
	
}
