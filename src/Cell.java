import javax.swing.*;
import java.util.ArrayList;

public class Cell {

    private int value;
    private int column;
    private int row;
    private int section;
    private int id;
    private ArrayList<Integer> potentialValues;

    Cell(){
        potentialValues = new ArrayList();

        for(int i = 1; i < 10; ++i){
            potentialValues.add(i);
        }

    }

    public void setValue(int value, JTextArea[] squares) {
        this.value = value;
        squares[id].setText(String.valueOf(value));
    }
    public void setColumn(int column){
        this.column = column;
    }
    public void setRow(int row){
        this.row = row;
    }
    public void setSection(int section){
        this.section = section;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void removePValue(int i){
        potentialValues.remove(i);
    }

    public int getValue() {
        return value;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getSection() {
        return section;
    }

    public int getId() {
        return id;
    }

    public int getPValue(int i){
        return potentialValues.get(i);
    }

    public int getSize(){
        return potentialValues.size();
    }

    public void printValues(){
        String values = "";
        for(int pValue: potentialValues){
            values += pValue + " ";
        }
        values += "\n";
        System.out.println(values);
    }

    public ArrayList<Integer> getPValues(){
        return potentialValues;
    }

}
