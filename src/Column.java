public class Column {

    private int[] numCollection;

    Column(){
        numCollection = new int[9];

    }

    public void setColumn(int colNum, Cell[] cells){
        int interval = colNum;

        for(int i = 0; i < 9; ++i){

            numCollection[i] = cells[interval].getValue();
            cells[interval].setColumn(colNum);

            interval += 9;

        }
    }

    public String printValues(){
        String outVals = "";

        for(int num: numCollection){
            outVals += " " + num;
        }

        return outVals;
    }

    public int[] getValues(){
        return numCollection;
    }

}
