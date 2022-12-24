import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {

    final Dimension dimension = new Dimension(600, 100);

    JButton solveButton;
    JButton resetButton;
    GridBagConstraints buttonCon;
    GridBagConstraints solveCon;
    GridBagConstraints resetCon;
    Panel panel;
    private int loopCount = 0;

    ButtonPanel(Panel panel){

        this.panel = panel;

        this.setLayout(new FlowLayout());
        this.setPreferredSize(dimension);

        resetCon = new GridBagConstraints();
        solveCon = new GridBagConstraints();

        resetCon.gridx = 1;
        resetCon.gridy = 4;


        solveCon.gridx = 0;
        solveCon.gridy = 4;


        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              if(panel.puzzle.cells[0] != null){
                                                  panel.puzzle.reset();
                                                  loopCount = 0;
                                              }
                                          }
                                      });

        solveButton = new JButton("Solve!");
        solveButton.addActionListener(this);

        this.add(solveButton, solveCon);
        this.add(resetButton, resetButton);
    }

    public GridBagConstraints buttonConstraints(){

        buttonCon = new GridBagConstraints();


        buttonCon.gridwidth = 2;
        buttonCon.gridx = 0;
        buttonCon.gridy = 4;
        buttonCon.insets = new Insets(10, 0, 0, 0);

        return buttonCon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        panel.puzzle.getValues();

        while(panel.puzzle.getZeros() > 0) {

            if(loopCount > 200){
                System.out.println("Loop 200 - Terminating");
                break;
            }

            System.out.println("Board " + loopCount);
            panel.puzzle.getCellValue();

            panel.puzzle.eliminationSolve();
            panel.puzzle.boardSolve();
            panel.puzzle.sectionSolve();

            loopCount++;

        }
    }
}
