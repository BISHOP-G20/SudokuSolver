import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Puzzle {

    JTextArea[] squares;
    Column[] columns = new Column[9];
    Row[] rows = new Row[9];
    Section[] sections = new Section[9];
    Cell[] cells = new Cell[81];


    Puzzle(){
        int row = 1;
        int column = 1;

        squares = new JTextArea[81];

        for(int i = 0; i < squares.length; ++i){
            if(squares[i] == null) {
                squares[i] = new JTextArea("");
            }
        }

        for(JTextArea area: squares){

            area.setForeground(Color.BLACK);
            area.setEditable(true);

            area.setBounds((60 * column) - 45, (60 * row) - 45, 40, 38);

            ++column;

            if(column == 10){
                column = 1;
                ++row;
            }
        }
    }

    public int getZeros(){
        int total = 0;
        for(Cell cell: cells){
            if(cell.getValue() == 0){
                ++total;
            }
        }
        return total;
    }

    public void getValues(){

        for(int i = 0; i < squares.length; ++i){

            if(cells[i] == null) {
                cells[i] = new Cell();
                cells[i].setId(i);
            }

            String input = squares[i].getText().strip();

            int retrievedNum;


            if(input.equals("1")){
                retrievedNum = 1;
            }
            else if(input.equals("2")){
                retrievedNum = 2;
            }
            else if(input.equals("3")){
                retrievedNum = 3;
            }
            else if(input.equals("4")){
                retrievedNum = 4;
            }
            else if(input.equals("5")){
                retrievedNum = 5;
            }
            else if(input.equals("6")){
                retrievedNum = 6;
            }
            else if(input.equals("7")){
                retrievedNum = 7;
            }
            else if(input.equals("8")){
                retrievedNum = 8;
            }
            else if(input.equals("9")){
                retrievedNum = 9;
            }
            else{
                retrievedNum = 0;
            }

            cells[i].setValue(retrievedNum, squares);
        }
    }

    public void createGroups(){

        for(int i = 0; i < 9; ++i){
            columns[i] = new Column();
            rows[i] = new Row();
            sections[i] = new Section();

            columns[i].setColumn(i, cells);
            rows[i].setRow(i, cells);
            sections[i].setSection(i, cells);

        }
    }

    public void eliminationSolve(){

            for (Cell cell : cells) {

                createGroups();

                if (cell.getValue() == 0) {

                    int section = cell.getSection();
                    int[] thisSection = sections[section].getValues();

                    int row = cell.getRow();
                    int[] thisRow = rows[row].getValues();

                    int column = cell.getColumn();
                    int[] thisColumn = columns[column].getValues();

                    if (cell.getSize() > 1) {

                        System.out.println("Cell ID: " + cell.getId() + "  Pvalue list size: " + cell.getSize());
                        System.out.println("PValues: ");
                        cell.printValues();

                        for(int i = 0; i < 9; ++i){

                        for (Iterator<Integer> itr = cell.getPValues().iterator(); itr.hasNext();) {


                                try{
                                    int value = itr.next();

                                    if(value == thisSection[i] || value == thisRow[i] || value == thisColumn[i]){
                                        itr.remove();
                                        System.out.println("Removed value: " + value);
                                        System.out.println();

                                        break;
                                    }
                                }
                                catch (NoSuchElementException e){
                                    break;
                                }
                            }

                            if(cell.getPValues().size() <= 1){
                                break;
                            }

                        }

                    } else {

                        if (cell.getSize() > 0) {

                            int value = cell.getPValue(0);
                            cell.setValue(value, squares);

                            System.out.println("Elimination Solve:");
                            System.out.println("Cell " + cell.getId() + " Value: " + value);
                            System.out.println();
                        }
                    }
                }
            }
    }

    public void boardSolve(){

        for(int i = 0; i < 9; ++i) {

            createGroups();

            boolean containsNumber = false;

            for (Section section : sections){
                int[] list = section.getValues();

                for (int num : list) {
                    if (num == i + 1) {
                        containsNumber = true;
                    }
                }

                if (containsNumber == true) {
                    continue;
                }

                else {
                    int[] secRows = section.getRows();
                    int[] secColumns = section.getColumns();

                    boolean[] rowContainsNum = new boolean[]{false, false, false};
                    boolean[] columnContainsNum = new boolean[]{false, false, false};

                    int rowNumScore = 0;
                    int columnNumScore = 0;

                    for (int j = 0; j < 3; ++j) {

                        int[] rowVals = rows[secRows[j]].getValues();

                        for (int val : rowVals) {
                            if (val == i) {
                                rowContainsNum[j] = true;
                                ++rowNumScore;
                            }
                        }
                    }

                    for (int j = 0; j < 3; ++j) {

                        int[] columnVals = columns[secColumns[j]].getValues();

                        for (int val : columnVals) {
                            if (val == i) {
                                columnContainsNum[j] = true;
                                ++columnNumScore;
                            }
                        }
                    }

                    if (rowNumScore == 2 && columnNumScore == 2) {
                        int emptyRow = 0;
                        int emptyColumn = 0;

                        for (int j = 0; j < 3; ++j) {
                            if (rowContainsNum[j] == false) {
                                emptyRow = secRows[j];
                            }
                            if (columnContainsNum[j] == false) {
                                emptyColumn = secColumns[j];
                            }
                        }

                        cells[emptyColumn + (emptyRow * 9)].setValue(i, squares);

                        System.out.println("Board Solve:");
                        System.out.println("Cell " + cells[emptyColumn + (emptyRow * 9)].getId() + " Value: " + i);
                        System.out.println();
                    }
                }
            }
        }
    }

    public void sectionSolve(){

        for(Section section: sections){

            ArrayList<Integer> missingValues = new ArrayList<>();


            for(int l = 1; l < 10; ++l){

                boolean hasValue = false;

                for(int secValue: section.getValues()) {

                    if(secValue == l){
                        hasValue = true;
                    }
                }
                if(hasValue == false){
                    missingValues.add(l);
                }
            }


            ArrayList<Integer> openCells;

            Cell[] secCells = section.getCells();

            int[] secRows = section.getRows();

            int[] secColumns = section.getColumns();

            for(int missingValue: missingValues){

                openCells = new ArrayList<>(9);

                for(int x = 0; x < 9; ++x){

                    if(secCells[x].getValue() == 0) {
                        openCells.add(x);
                    }
                    else{
                        openCells.add(-1);
                    }
                }

                createGroups();

                for(int j = 0; j < 3; ++j){
                    for(int rowValue: rows[secRows[j]].getValues()){
                        if(missingValue == rowValue){
                            for(int q = 0; q < 3; ++q){
                                openCells.set((j * 3) + q, -1);
                            }
                        }

                        break;
                    }
                }
                for(int j = 0; j < 3; ++j){
                    for(int columnValue: columns[secColumns[j]].getValues()){
                        if(missingValue == columnValue){
                            for(int q = 0; q < 3; ++q){
                                openCells.set(j + (q * 3), -1);
                            }
                        }

                        break;
                    }
                }

                for(Iterator<Integer> itr = openCells.iterator(); itr.hasNext();){
                    int cellPos = itr.next();

                    if(cellPos == -1){
                        itr.remove();
                    }
                }

                if(openCells.size() == 1){
                    secCells[openCells.get(0)].setValue(missingValue, squares);

                    System.out.println("Section solve: ");
                    System.out.println("Cell " + secCells[openCells.get(0)].getId() + " Value: " + missingValue);
                    System.out.println();
                }
            }

        }
    }

    public void getCellValue(){

        int totalCount = 1;

        for(Cell cell: cells) {

            System.out.print(" [ " + cell.getValue() + " ] ");

            if(totalCount % 9 == 0) {
                System.out.print("\n\n");
            }
            ++totalCount;
        }

        System.out.println();
        System.out.println();
    }

    public void reset(){

        for(Cell cell: cells){
            cell.setValue(0, squares);
        }

        for(JTextArea square: squares){
            square.setText("");
        }
    }

}
