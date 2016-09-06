import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by lenovo on 2016/7/27.
 */
public class Battlefield extends JPanel  {

    public static final int MARGIN=20;//???
    public static final int GRID_SPAN=39;//?????
    public static final int ROWS=10;//????????
    public static final int COLS=10;//????????

    Color lightBlue= new Color(10,180,120,100);
    //GridLayout g= new GridLayout(10,10);
    public Battlefield(){
        setBackground(Color.WHITE);
        //setSize(MARGIN*2+GRID_SPAN*10,MARGIN*2+GRID_SPAN*10);
        setOpaque(false);

    }

    public Dimension getPreferredSize()		{	return new Dimension(MARGIN*2+GRID_SPAN*10,MARGIN*2+GRID_SPAN*10);	}





    public void paintComponent(Graphics g) {
        for(int i=0;i<=ROWS;i++){//??????
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);
        }
        for(int i=0;i<=COLS;i++){//??????
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);
        }




    }


}
