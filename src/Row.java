public class Row {

    private int[] numCollection;

    Row(){
        numCollection = new int[9];
    }

    public void setRow(int rowNum, Cell[] cells){

        for(int i = 0; i < 9; ++i){
            numCollection[i] = cells[(rowNum * 9) + i].getValue();
            cells[(rowNum * 9) + i].setRow(rowNum);

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
