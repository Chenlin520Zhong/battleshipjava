import java.awt.*;

/**
 * Created by lenovo on 2016/7/26.
 */
public class Ship {
    private int length;
    public String name;
    public boolean Avaliable=true;
    int x1,x2,y1,y2,number;
    int life;

    public int getLength(){

        return this.length;
    }

    public Ship(String name,int a,int n){
        this.name=name;
        this.number=a;
        this.length=n;
        this.life=n;
    }

    public void setPosition(int a, int b, boolean v){
        this.x1 = a;
        this.y1 = b;
        if(v) {
            this.x2 = a;
            this.y2 = b+this.length-1;
        }else{
            this.x2 = a+this.length-1;
            this.y2 = b;
        }
    }

}
