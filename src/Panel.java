import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {


    GameBoard board;
    Puzzle puzzle;
    JLayeredPane pane;
    GridBagConstraints panelCon;

    final Dimension P_DIMENSION = new Dimension(600, 600);
    final int B_DIMENSION = 540;

    Panel(){

        pane = new JLayeredPane();
        pane.setPreferredSize(P_DIMENSION);

        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.add(pane);
        this.setPreferredSize(P_DIMENSION);

        newBoard();

        for(JTextArea area: newPuzzle()){
            pane.add(area, 1);
        }

    }

    public JTextArea[] newPuzzle(){
        puzzle = new Puzzle();
        return puzzle.squares;
    }

    public void newBoard(){
        board = new GameBoard(B_DIMENSION);
    }

    public void paint(Graphics g){
        draw(g);
    }

    public void draw(Graphics g){
        board.draw(g);
    }

    public GridBagConstraints panelConstraints(){

        panelCon = new GridBagConstraints();

        panelCon.gridwidth = 2;
        panelCon.gridx = 0;
        panelCon.gridy = 0;
        panelCon.insets = new Insets(0, 30, 30, 0);

        return panelCon;
    }
}



