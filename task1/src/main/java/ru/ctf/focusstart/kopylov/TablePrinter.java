package ru.ctf.focusstart.kopylov;

public class TablePrinter {

    private int[][] tableArray;

    public TablePrinter(int[][] array) {
        tableArray = array;
    }

    public void buildMulTable() {

        int tableSize = tableArray.length;

        int maxLength = String.valueOf(tableArray[tableSize - 1][tableSize - 1]).length();

        String dividingString = "";

        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < maxLength; j++) {
                dividingString += "-";
            }
            if (i != tableSize - 1) {
                dividingString += "+";
            }
        }

        String formattedString = "%" + maxLength + "d";

        for (int i = 0; i < tableSize; i++) {
            String mulString = "";

            for (int j = 0; j < tableSize; j++) {
                mulString += String.format(formattedString, tableArray[i][j]);
                if (j != tableSize - 1) {
                    mulString += "|";
                }
            }

            System.out.println(mulString);
            System.out.println(dividingString);
        }
    }
}
