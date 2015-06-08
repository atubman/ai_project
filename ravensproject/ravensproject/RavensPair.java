package ravensproject;

//its a pair, 

public class RavensPair<L,R> {
    private L l;
    private R r;
    public RavensPair(L l, R r){
        this.l = l;
        this.r = r;
    }
    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
}