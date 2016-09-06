import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import  javax.swing.border.*;

/**
 * Created by ????? on 2016/7/19.
 */
public class Battleship extends JFrame {

    JButton b1,b2,b3;
    // Board board = new Board() ;
    JPanel board;
    JLabel j1;

    public Battleship(){

        super("Battleship Game Pre-alpha    Chenlin Zhong");
        setBackground(Color.BLUE);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
         setlook();
        //j1=new JLabel("����");
        board= new Board();
        //c=getContentPane();
        //c= new JPanel();
       // c.setLayout(new BorderLayout());
        //c.add(board,BorderLayout.CENTER);

        add(board,BorderLayout.CENTER);
        pack();
       /* setSize(MARGIN * 2 + 100 + GRID_SPAN * ROWS * 2,
                MARGIN * 2 + GRID_SPAN * COLS);*/
        setVisible(true);
    }

    private void setlook(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Nimbus look ERROR");
        }
    }




    public static void main(String[] args){
        Battleship bs= new Battleship();

    }
 }
