package Tic;

public class Punkt {
    public int x,y;

    public Punkt(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Punkt() {
    }

    @Override
    public String toString(){
        return "["+x+","+y+"]";
    }
}
