import java.util.Queue;
import java.util.Stack;

/**
 * Created by lenovo on 2016/8/17.
 */
public class Nextstep {
     static int ex=0;
     static int ey=-1;
    int direction=0;
    int x2=-1,y2=-1;
    int dx=-1;
    int dy=-1;
    int x1,y1;

    public int[][] m1={
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,0,0,0,}
    };


    public static final int UP=1;
    public static final int DOWN=-1;
    public static final int RIGHT=2;
    public static final int LEFT=-2;
    int length;

    boolean straight=false;

    public Point lastpoint=new Point(-1,-1);
    public static Point temp;

    public int[][]d;

    int i,j,k;

    public Nextstep(){

    }

    public  void step(int[][] a,boolean[] b,int c){
        // a= a matrix shows the map 2 means hit 1 means miss 0 means it hasn't been shooted
        // b= a boolean array shows the state of ships
        // c=the code of the ship that be destroied last round
        //  0:carrier 1:
        //
        //
        x1=ex;y1=ey;
        d=a;
        switch(c){
            case 1:
                length= 5;
                break;
            case 2:
                length = 4;
                break;
            case 3:
                length =3;
                break;
            case 4:
                length =3;
                break;
            case 5:
                length =2;
                break;
        }
    Delta :if(ey!=-1) {
        if (d[ex][ey] == 2) {
            m1[ex][ey] = 2;
            if (c == 0) {

                if (temp == null) {

                    temp = new Point(ex, ey);
                    if (!turn() || !foward()) search1();
                } else {
                    if (!foward()) {
                        direction = -direction;
                        ex = temp.x;
                        ey = temp.y;
                        alpha: if (!foward()) {
                            if (!turn() || !foward()) {
                                {
                                    while (checkmatrix()) {
                                        temp = new Point(dx,dy);
                                        ex = temp.x;
                                        ey = temp.y;
                                        if (turn() && foward()) break alpha;
                                    }
                                    m1[temp.x][temp.y]=3;
                                    temp=null;
                                    ex=x1;ey=y1;
                                    search1();
                                }
                            }
                        }
                    }
                }
            } else {
                eliminate();
                while (checkmatrix()) {
                    temp = new Point(dx,dy);
                    ex = temp.x;
                    ey = temp.y;
                    if (turn() && foward()) break Delta;
                }
                temp=null;
                ex=x1;ey=y1;
                search1();
            }
        } else {

            if (temp != null) {
                direction = -direction;
                ex = temp.x;
                ey = temp.y;
                if (!foward()) {
                    Beta:
                    if (!foward()) {
                        if (!turn() || !foward()) {
                            {
                                while (checkmatrix()) {
                                    temp = new Point(dx,dy);
                                    ex = temp.x;
                                    ey = temp.y;
                                    if (turn() && foward()) break Beta;
                                }
                                m1[temp.x][temp.y]=3;
                                temp=null;
                                ex=x1;ey=y1;
                                search1();
                            }
                        }
                    }
                }
            } else {
                search1();
            }

        }
    }else {
        search1();
    }


     //   System.out.println(lastpoint.x+"   "+lastpoint.y);
    }

    public class Point{
        int x,y;
        public Point(int a,int b){
            this.x=a;
            this.y=b;
        }
        public void setpoint(int a,int b){
            this.x=a;
            this.y=b;
        }
    }

    public boolean foward(){
        switch (direction){
            case UP:
                if(isLegal(ex,ey-1)){ ey--;
                return true;}else return false;
            case DOWN:
                if(isLegal(ex,ey+1))
                { ey++;
                    return true;}else return false;
            case LEFT:
                if(isLegal(ex-1,ey))
                {ex--;
                    return true;}else return false;
            case RIGHT:
                if(isLegal(ex+1,ey)){
                ex++;
            return true;}else return false;
        }
        return false;
    }

    public boolean turn(){
        if(isLegal(ex,ey-1)){direction =UP;
            return  true;}
         if(isLegal(ex,ey+1)){direction =DOWN;
        return  true;}
        if(isLegal(ex-1,ey)){direction =LEFT;
    return  true;}
        if(isLegal(ex+1,ey)){direction =RIGHT;
                return  true;}
                return false;

    }



    public void search1(){
        do {
            if (x2 == -1) {
                x2 = 0;
                y2 = 1;
            } else if (x2 == 9 && y2 == 8) {
                x2 = 0;
                y2 = 0;
            } else if (y2 == 0) {
                y2 = x2 + 2;
                x2 = 0;
            } else {
                x2++;
                y2--;
            }
        }while (!isLegal(x2,y2));
        ex=x2;ey=y2;
    }

    public void search2(){
        turn();
        foward();
    }

    public boolean checkmatrix(){
        for(i=0;i<10;i++){
            for(j=0;j<10;j++){
                if(m1[i][j]==2){
                    dx=i;
                    dy=j;
                    return true;
                }
            }
        }
        return false;
    }

    public void eliminate(){
        switch (direction){
            case UP:
                for (i=0;i<length;i++)m1[ex][ey+i]=3;
                break;
            case DOWN:
                for (i=0;i<length;i++)m1[ex][ey-i]=3;
                break;
            case LEFT:
                for (i=0;i<length;i++)m1[ex+i][ey]=3;
                break;
            case RIGHT:
                for (i=0;i<length;i++)m1[ex-i][ey]=3;
                break;
        }
    }


    public boolean isLegal(int a,int b){
        return (a>=0&&a<=9&&b>=0&&b<=9&&d[a][b]==0);
    }
}
