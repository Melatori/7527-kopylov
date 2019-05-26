package ru.ctf.focusstart.kopylov.UIComponents;

import java.awt.*;

public class Palette {
    private static final Color headerBackgroundColor = new Color(0, 150, 136);
    private static final Color textColor = new Color(255, 255, 255);
    private static final Color darkTextColor = new Color(33, 33, 33);
    private static final Color inputBackgroundColor = new Color(189, 189, 189);
    private static final Color inputBorderColor = new Color(33, 33, 33);
    private static final Color buttonBackgroundColor = new Color(189, 189, 189);
    private static final Color headColor = new Color(0, 121, 107);
    private static final Color backgroundColor = new Color(178, 223, 219);
    private static final Color openedCellColor = new Color(117, 117, 117);
    private static final Color cellBorderColor = new Color(0, 121, 107);
    private static final Color explodedCellColor = new Color(0, 121, 107);
    private static final Color loseButtonColor = new Color(244, 67, 54);
    private static final Color winButtonColor = new Color(76, 175, 80);

    private Palette() {

    }

    public static Color getHeaderBackgroundColor() {
        return headerBackgroundColor;
    }

    public static Color getTextColor() {
        return textColor;
    }

    public static Color getHeadColor() {
        return headColor;
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static Color getOpenedCellColor() {
        return openedCellColor;
    }

    public static Color getCellBorderColor() {
        return cellBorderColor;
    }

    public static Color getExplodedCellColor() {
        return explodedCellColor;
    }

    public static Color getDarkTextColor() {
        return darkTextColor;
    }

    public static Color getInputBackgroundColor() {
        return inputBackgroundColor;
    }

    public static Color getInputBorderColor() {
        return inputBorderColor;
    }

    public static Color getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    public static Color getLoseButtonColor() {
        return loseButtonColor;
    }

    public static Color getWinButtonColor() {
        return winButtonColor;
    }
}
