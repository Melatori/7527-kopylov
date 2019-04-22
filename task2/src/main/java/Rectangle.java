import java.io.IOException;

public class Rectangle extends Figure {
    private double diagonal;
    private double length;
    private double width;

    public Rectangle(double sideOne, double sideTwo) throws IOException {
        if ((sideOne <= 0) || (sideTwo <= 0)) {
            throw new IOException("Неправильно указанны стороны прямоугольника, невозможный прямоугольник");
        }
        this.name = "Прямоугольник";
        if (sideOne > sideTwo) {
            this.length = sideOne;
            this.width = sideTwo;
        } else {
            this.length = sideTwo;
            this.width = sideOne;
        }
        this.perimeter = calcPerimeter(this.length, this.width);
        this.area = calcArea(this.length, this.width);
        this.diagonal = calcDiagonal(this.length, this.width);
        setParams();
    }

    private static double calcArea(double length, double width) {
        return length * width;
    }

    private static double calcPerimeter(double length, double width) {
        return length * 2 + width * 2;
    }

    private static double calcDiagonal(double length, double width) {
        return Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
    }

    @Override
    protected void setParams() {
        super.setParams();
        params.add(String.format("Длина диагонали: %.2f", diagonal));
        params.add(String.format("Длина: %.2f", length));
        params.add(String.format("Ширина: %.2f", width));
    }
}
