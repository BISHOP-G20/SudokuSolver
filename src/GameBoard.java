import javax.swing.*;
import java.awt.*;

public class GameBoard extends Rectangle {

    private int B_DIM;

    GameBoard(int B_DIM){

        super(0, 0, 540, 540);
        this.B_DIM = B_DIM;


    }

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, 540, 540);

        for(int i = 60; i < B_DIM; i += 60) {
            if((i % 180) == 0){
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(4));
            }
            else{
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1));
            }
            g2d.drawLine(i, 0, i, B_DIM);
        }
        for(int i = 60; i < B_DIM; i += 60) {
            if((i % 180) == 0){
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(4));
            }
            else{
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1));
            }
            g2d.drawLine(0, i, B_DIM, i);
        }
    }
}
