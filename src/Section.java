public class Section {

    private int sectionNum;
    private int firstInt;
    private int[] secRows = new int[]{-1, -1, -1};
    private int[] secColumns = new int[]{-1, -1, -1};
    private Cell[] cellCollection;

    Section(){
        cellCollection = new Cell[9];
    }

    public void setSection(int sectionNum, Cell[] cells){

        int totalCount = 0;

        this.sectionNum = sectionNum;

        if(sectionNum < 3) {
            firstInt = 0 + (sectionNum * 3);
        }
        else if(sectionNum < 6){
            firstInt = 27 + ((sectionNum - 3) * 3);

        }
        else{
            firstInt = 54 + ((sectionNum - 6) * 3);

        }


        for(int i = 0; i < 3; ++i){

            for(int j = 0; j < 3; ++j){

                if(secRows[i] != cells[firstInt + j].getRow()){
                    secRows[i] = cells[firstInt + j].getRow();
                }
                if(secColumns[i] != cells[firstInt + j].getColumn()){
                    secColumns[i] = cells[firstInt + j].getColumn();
                }

                cellCollection[totalCount] = cells[firstInt + j];
                cells[firstInt + j].setSection(sectionNum);

                ++totalCount;
            }

        firstInt += 9;
        }
    }

    public String printValues(){
        String outVals = "";

        for(Cell cell: cellCollection){
            outVals += " " + cell.getValue();
        }

        return outVals;
    }

    public int[] getValues(){

        int[] cellVals = new int[9];

        for(int i = 0; i < 9; ++i){
            cellVals[i] = cellCollection[i].getValue();
        }
        return cellVals;
    }

    public int[] getRows(){

                return secRows;
    }

    public int[] getColumns(){

        return secColumns;
    }

    public int getFirstInt(){
        return firstInt;
    }

    public Cell[] getCells(){
        return cellCollection;
    }

}
