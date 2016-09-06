import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Created by lenovo on 2016/7/20.
 * 2016/7/19 Play some battleship game
 */

public class  Board extends JPanel{
    Graphics2D g;

    public JPanel you,enemy,
            board2,
            board3,
            shipboard;

    Nextstep ns=new Nextstep();

    public boolean Alive_A[]={true,true,true,true,true};
    public boolean Alive_B[]={true,true,true,true,true};
    public int noSink=0;
    int x1,
            y1,
       shiplength,
        i,j,k,
        ex=0,ey=-1,
        r1,r2,r0;
    boolean r3;
    String jtt= "deploy your ships\n";
    public JTextArea jt;
    public JScrollPane js;
    public int[][] player1={
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
    public int[][] shipsetA={
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
    public int[][] shipsetB={
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
    public int[][] player2={
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
    Ship  carrierA= new Ship("carrier",51,5),
            cruiserA=new Ship("cruiser",41,4),
            submarineA= new Ship("submarine",31,3),
            destroyerA = new Ship("destroyer",32,3),
            corvetteA= new Ship("corvette",21,2),
            carrierB= new Ship("carrier",51,5),
            cruiserB=new Ship("cruiser",41,4),
            submarineB= new Ship("submarine",31,3),
            destroyerB = new Ship("destroyer",32,3),
            corvetteB= new Ship("corvette",21,2),
            tempship=null;

    Ship[] shipsA=new Ship[]{carrierA,cruiserA,submarineA,destroyerA,corvetteA},
            shipsB=new Ship[]{carrierB,cruiserB,submarineB,destroyerB,corvetteB};

    public static final int MARGIN=20;//???
    public static final int GRID_SPAN=39;//?????
    public static final int ROWS=10;//????????
    public static final int COLS=10;//????????
    public int shipnumber=0;
    public boolean isHit_You=true, isHit_Enemy=true;

    public Board(){
        //test parameters
      /*  shipsA[0].setPosition(2,3,2,7);
        shipsA[1].setPosition(3,2,6,2);
        shipsA[2].setPosition(4,3,4,5);
        shipsA[3].setPosition(5,6,5,8);
        shipsA[4].setPosition(9,10,10,10);*/


        setLayout(new BorderLayout());

        board2=new JPanel();
        board2.setLayout(new BorderLayout());
        you= new Yourfield();
        enemy = new Enemyfield();
        board2.add(you,BorderLayout.WEST);
        board2.add(enemy,BorderLayout.EAST);
        board3=new JPanel();
        jt=new JTextArea(5,30);
        js=new JScrollPane(jt);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        board3.add(js);
        jt.setText(jtt);

        shipboard=new Shipboard();

        add(board2, BorderLayout.CENTER);
        add(board3,BorderLayout.SOUTH);
        add(shipboard,BorderLayout.WEST);
        setSize(MARGIN*2+GRID_SPAN*2*2,MARGIN*2+GRID_SPAN*2);
        setVisible(true);
    }

    public class Shipboard extends JPanel implements ActionListener  {

        public JButton carrier,
                cruiser,
                submarine,
                destroyer,
                corvette;

        Color lightBlue= new Color(10,180,120,100);
        GridLayout g= new GridLayout(10,10);
        public Shipboard(){
            setLayout(new GridLayout(5, 1));
            add(carrier = new JButton("carrier"));
            add(cruiser = new JButton("cruiser"));
            add(submarine = new JButton("submarine"));
            add(destroyer = new JButton("destroyer"));
            add(corvette = new JButton("corvette"));

            carrier.addActionListener(this);
            cruiser.addActionListener(this);
            submarine.addActionListener(this);
            destroyer.addActionListener(this);
            corvette.addActionListener(this);
            setOpaque(true);

        }



        public void actionPerformed (ActionEvent e){
            if(e.getSource()==carrier){
                if(carrierA.Avaliable==true){
                shipnumber=51;
                    shiplength=5;
                    tempship=carrierA;
                }
            }
            if(e.getSource()==cruiser){
                if(cruiserA.Avaliable==true){
                shipnumber=41;
                    shiplength=4;
                    tempship=cruiserA;
                }
            }
            if(e.getSource()==submarine){
                if(submarineA.Avaliable==true){
                shipnumber=31;
                    shiplength=3;
                    tempship=submarineA;
                }
            }
            if(e.getSource()==destroyer){
                if(destroyerA.Avaliable==true){
                shipnumber=32;
                    shiplength=3;
                    tempship=destroyerA;
                }
            }
            if(e.getSource()==corvette){
                if(corvetteA.Avaliable==true){
                shipnumber=21;
                    shiplength=2;
                    tempship=corvetteA;
                }
            }
            jtt+=shipnumber+"\n";
            jt.setText(jtt);
        }


    }


    public class Yourfield extends Battlefield implements MouseListener  {



    Color lightBlue= new Color(10,180,120,100);
    boolean vertical=false;
    int x_current,y_current;
    //GridLayout g= new GridLayout(10,10);
    public Yourfield(){
        setBackground(Color.WHITE);
        //setSize(MARGIN*2+GRID_SPAN*10,MARGIN*2+GRID_SPAN*10);
        addMouseListener(this);

        addMouseMotionListener(new MouseMotionListener(){
                                   public void mouseDragged(MouseEvent e){

                                   }
                                   public void mouseMoved(MouseEvent e){
                                       x1=((e.getX()-MARGIN+GRID_SPAN)/GRID_SPAN)-1;
                                       y1=((e.getY()-MARGIN+GRID_SPAN)/GRID_SPAN)-1;
                                       if(x1<0||x1>ROWS-1||y1<0||y1>COLS-1) setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                       else setCursor(new Cursor(Cursor.HAND_CURSOR));
                                       x_current=x1;
                                       y_current=y1;
                                       you.repaint();
                                   }
}
        );

        setOpaque(false);

    }

   // public Dimension getPreferredSize()		{	return new Dimension(MARGIN*2+GRID_SPAN*10,MARGIN*2+GRID_SPAN*10);	}

    public void drawships(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.setStroke(new BasicStroke(25));
        for(int i=0;i<5;i++){
            if(!shipsA[i].Avaliable)
                    g.drawLine(
                    (int)((shipsA[i].x1+0.5)*GRID_SPAN+MARGIN),// -1 fixed in Ship class
                    (int)((shipsA[i].y1+0.5)*GRID_SPAN+MARGIN),
                    (int)((shipsA[i].x2+0.5)*GRID_SPAN+MARGIN),
                    (int)((shipsA[i].y2+0.5)*GRID_SPAN+MARGIN)
                    );
        }
    }





    public void paintComponent(Graphics d) {
        super.paintComponent(d);
        g=(Graphics2D)d;
        drawships(g);

        g.setStroke(new BasicStroke(5));
        for( i=0;i<10;i++)
            for( j=0;j<10;j++){
                if(player1[i][j]==2){
                    g.setColor(Color.RED);
                    g.drawOval(
                            i*GRID_SPAN+MARGIN+10,
                            j*GRID_SPAN+MARGIN+10,
                            GRID_SPAN-20,
                            GRID_SPAN-20);}
                else if(player1[i][j]==1) {
                    g.setColor(Color.BLUE);
                    g.drawOval(
                            i*GRID_SPAN+MARGIN+15,
                            j*GRID_SPAN+MARGIN+15,
                            GRID_SPAN-30,
                            GRID_SPAN-30);
                    g.drawOval(
                            i*GRID_SPAN+MARGIN+8,
                            j*GRID_SPAN+MARGIN+8,
                            GRID_SPAN-16,
                            GRID_SPAN-16);
                }
            }

        g.setColor(Color.ORANGE);
        g.setStroke(new BasicStroke(25));
        if(tempship!=null
                &&(x1>=0&&x1<=ROWS-1&&y1>=0&&y1<=COLS-1)
                ) {
        if(vertical) {
            g.drawLine(
                    (int) ((x1 + 0.5) * GRID_SPAN + MARGIN),
                    (int) ((y1 + 0.5) * GRID_SPAN + MARGIN),
                    (int) ((x1 + 0.5) * GRID_SPAN + MARGIN),
                    (int) ((y1 + 0.5+ tempship.getLength()-1) * GRID_SPAN + MARGIN)
            );
        }
            else{

                g.drawLine(
                        (int) ((x1 + 0.5) * GRID_SPAN + MARGIN),
                        (int) ((y1 + 0.5) * GRID_SPAN + MARGIN),
                        (int) ((x1 + 0.5 + tempship.getLength()-1) * GRID_SPAN + MARGIN),
                        (int) ((y1 + 0.5) * GRID_SPAN + MARGIN)
                );
            }



        }
     /*   g.setColor(Color.RED);
        g.setStroke(new BasicStroke(5));
        for(int x1=0;x1<10;x1++)
            for(int y1=0;y1<10;y1++){
                if(player1[x1][y1]==1)g.drawOval(
                        x1*GRID_SPAN+MARGIN+10,
                        y1*GRID_SPAN+MARGIN+10,
                        GRID_SPAN-20,
                        GRID_SPAN-20);
            }
`   */


    }

    void placeship(int a,int b,boolean v,int c){
        alpha:if(v&&b+c-1<=9){
            for(i=b;i<b+c;i++){
                if(shipsetA[a][i]>0)break alpha;
            }
            for(i=b;i<b+c;i++){
                shipsetA[a][i]=tempship.number;
            }
            tempship.setPosition(a,b,vertical);
            tempship.Avaliable=false;
            tempship=null;
        }
        else if(!v&&a+c-1<=9){
            for(i=a;i<a+c;i++){
                if(shipsetA[i][b]>0)break alpha;
            }
            for(i=a;i<a+c;i++){
                shipsetA[i][b]=tempship.number;
            }
            tempship.setPosition(a,b,vertical);
            tempship.Avaliable=false;
            tempship=null;
        }
        //if
    }

    public void mouseClicked(MouseEvent e){
        /*
        int x1=(e.getX()-MARGIN+GRID_SPAN)/GRID_SPAN;
        int y1=(e.getY()-MARGIN+GRID_SPAN)/GRID_SPAN;
        String msg=String.format("you clicked the "+x1+" , "+y1+" board!!","Chenlin Zhong");
        // JOptionPane.showMessageDialog(this, msg);
        if(player1[x1-1][y1-1]==0)player1[x1-1][y1-1]=1;
        jtt+="You hit enemy ship\n";
        jt.setText(jtt);*/

        //????????????????????
    }
    public void mouseEntered(MouseEvent e){
        //????????????????
    }
    public void mouseExited(MouseEvent e){
        //?????????????
    }
    public void mouseReleased(MouseEvent e){
        //???????????????????
    }
    public void mousePressed(MouseEvent e){
        if(x1>=0&&x1<=ROWS-1&&y1>=0&&y1<=COLS-1){
            if (e.getButton() == 1) {
                if (tempship != null) {
                    placeship(x1, y1, vertical, tempship.getLength());
                }
            }
            if (e.getButton() == 3) {
                vertical = !vertical;
            }
            you.repaint();
        }
    }

}


    public class Enemyfield extends Battlefield implements MouseListener  {



        Color lightBlue= new Color(10,180,120,100);

        //GridLayout g= new GridLayout(10,10);
        public Enemyfield(){
            //deploy enemy ships
            setEnemy();
            setBackground(Color.WHITE);
            //setSize(MARGIN*2+GRID_SPAN*10,MARGIN*2+GRID_SPAN*10);
            addMouseListener(this);

            addMouseMotionListener(new MouseMotionListener(){
                                       public void mouseDragged(MouseEvent e){

                                       }
                                       public void mouseMoved(MouseEvent e){
                                           x1=((e.getX()-MARGIN+GRID_SPAN)/GRID_SPAN)-1;
                                           y1=((e.getY()-MARGIN+GRID_SPAN)/GRID_SPAN)-1;
                                           if(x1<0||x1>ROWS-1||y1<0||y1>COLS-1) setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                           else setCursor(new Cursor(Cursor.HAND_CURSOR));
                                       }
                                   }
            );

            setOpaque(false);

        }

        // public Dimension getPreferredSize()		{	return new Dimension(MARGIN*2+GRID_SPAN*10,MARGIN*2+GRID_SPAN*10);	}


        public void setEnemy(){
            /*shipsB[0].setPosition(1,2,true);
            shipsB[1].setPosition(3,2,false);
            shipsB[2].setPosition(4,3,false);
            shipsB[3].setPosition(5,6,false);
            shipsB[4].setPosition(7,8,false);*/
            for(j=0;j<5;j++) {
                w1:while(true){
                r1 = (int) (Math.random() * 10);
                r2 = (int) (Math.random() * 10);
                switch ((int) (Math.random() * 2)){
                    case 0:{r3=true;break;}
                    case 1:{r3=false;break;}
                    }
                    w2:if(r3&&r2+shipsB[j].getLength()-1<=9){
                        for(i=r2;i<r2+shipsB[j].getLength();i++){
                            if(shipsetB[r1][i]>0)break w2;
                        }
                        for(i=r2;i<r2+shipsB[j].getLength();i++){
                            shipsetB[r1][i]=shipsB[j].number;
                        }
                        shipsB[j].setPosition(r1,r2,r3);
                        break w1;
                    }
                    else if(!r3&&r1+shipsB[j].getLength()-1<=9){
                        for(i=r1;i<r1+shipsB[j].getLength();i++){
                            if(shipsetB[i][r2]>0)break w2;
                        }
                        for(i=r1;i<r1+shipsB[j].getLength();i++){
                            shipsetB[i][r2]=shipsB[j].number;
                        }
                        shipsB[j].setPosition(r1,r2,r3);
                        break w1;
                    }

                }
            }
            /*
            for(i=0;i<5;i++){
                for(j=shipsB[i].x1;j<=shipsB[i].x2;j++){
                    for(k=shipsB[i].y1;k<=shipsB[i].y2;k++){
                        shipsetB[j][k]=shipsB[i].number;
                    }
                }
            }*/
        }




        public void paintComponent(Graphics d) {
            super.paintComponent(d);
            g=(Graphics2D)d;

            g.setStroke(new BasicStroke(5));
            for( x1=0;x1<10;x1++)
                for( y1=0;y1<10;y1++){
                    if(player2[x1][y1]==2){
                        g.setColor(Color.RED);
                        g.drawOval(
                            x1*GRID_SPAN+MARGIN+10,
                            y1*GRID_SPAN+MARGIN+10,
                            GRID_SPAN-20,
                            GRID_SPAN-20);}
                    else if(player2[x1][y1]==1) {
                        g.setColor(Color.BLUE);
                        g.drawOval(
                            x1*GRID_SPAN+MARGIN+15,
                            y1*GRID_SPAN+MARGIN+15,
                            GRID_SPAN-30,
                            GRID_SPAN-30);
                        g.drawOval(
                                x1*GRID_SPAN+MARGIN+8,
                                y1*GRID_SPAN+MARGIN+8,
                                GRID_SPAN-16,
                                GRID_SPAN-16);
                    }
                }


        }


        public void mouseClicked(MouseEvent e){


        }
        public void mouseEntered(MouseEvent e){
        }
        public void mouseExited(MouseEvent e){
        }
        public void mouseReleased(MouseEvent e){
        }
        public void mousePressed(MouseEvent e){

            //String msg=String.format("you clicked the "+x1+" , "+y1+" board!!","Chenlin Zhong");
            // JOptionPane.showMessageDialog(this, msg);
            if(isHit_You!=false) {
                x1 = ((e.getX() - MARGIN + GRID_SPAN) / GRID_SPAN) - 1;
                y1 = ((e.getY() - MARGIN + GRID_SPAN) / GRID_SPAN) - 1;
                if (player2[x1][y1] == 0) {
                    if (shipsetB[x1][y1] > 0) {//you hit enmey
                        player2[x1][y1] = 2;
                        isHit_You = true;
                        enemy.repaint();

                        switch(shipsetB[x1][y1]){
                            case 51:
                                tempship=shipsB[0];
                                getHit(tempship,0);
                                break;
                            case 41:
                                tempship=shipsB[1];
                                getHit(tempship,1);
                                break;
                            case 31:
                                tempship=shipsB[2];
                                getHit(tempship,2);
                                break;
                            case 32:
                                tempship=shipsB[3];
                                getHit(tempship,3);
                                break;
                            case 21:
                                tempship=shipsB[4];
                                getHit(tempship,4);
                                break;
                        }

                        tempship=null;


                    } else {//you missed enemyI
                        player2[x1][y1] = 1;
                        isHit_You = false;
                        jtt += "You missed\n";
                        jt.setText(jtt);
                        isHit_Enemy=true;
                        enemy.repaint();
                        enemyAction();

                    }


                }

                //battlefield1.repaint();
            }

        }

    }

    public void enemyAction(){
        new Thread(new Runnable(){
            public void run() {

                do {
                    try {
                        Thread.sleep(800);
                    } catch (Exception e) {
                    }
                        ns.step(player1,Alive_A,noSink);
                        if (shipsetA[ns.ex][ns.ey] > 0) {//you hit enemy
                            player1[ns.ex][ns.ey] = 2;
                            isHit_Enemy = true;

                            switch(shipsetA[ns.ex][ns.ey]){
                                case 51:
                                    tempship=shipsA[0];
                                    getHit_B(tempship,0);
                                    break;
                                case 41:
                                    tempship=shipsA[1];
                                    getHit_B(tempship,1);
                                    break;
                                case 31:
                                    tempship=shipsA[2];
                                    getHit_B(tempship,2);
                                    break;
                                case 32:
                                    tempship=shipsA[3];
                                    getHit_B(tempship,3);
                                    break;
                                case 21:
                                    tempship=shipsA[4];
                                    getHit_B(tempship,4);
                                    break;
                            }

                            tempship=null;


                            you.repaint();
                        } else {//you missed enemy
                            player1[ns.ex][ns.ey] = 1;
                            jtt += "Your turn\n";
                            jt.setText(jtt);
                            isHit_Enemy = false;
                            you.repaint();
                        }



                } while (isHit_Enemy == true);
                isHit_You = true;
            }
        }
        ).start();
    }

                public void enemystep(int[][] a,boolean[] b){
                    if(ey<9)ey++;
                    else if(ex<9) {
                        ex++; ey=0;
                    }
        }

    public void setText(String str){
        jtt += str+"\n";
        jt.setText(jtt);
    }

    public void getHit(Ship s,int index){
        s.life--;
        if(s.life<=0){
            setText(s.name+" has sink");
            Alive_B[index]=false;
        }
        if(defeat(Alive_B))setText("You Win!");
    }

    public void getHit_B(Ship s,int index){
        s.life--;

        System.out.println(s.name);
        if(s.life<=0){setText(s.name+" has sink");
        Alive_A[index]=false;
        noSink=index+1;
        if(defeat(Alive_A))setText("You Lose!");
        }else {
            noSink=0;
        }
    }

    public boolean defeat(boolean[] a){
        for(boolean c:a){
         if(c==true)return false;
        }
        return true;
    }
}