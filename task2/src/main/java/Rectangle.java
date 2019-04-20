public class Rectangle extends Figure {
    private double diagonal;
    private double length;
    private double width;

    public Rectangle(double sideOne, double sideTwo) throws Exception {
        if ((sideOne <= 0) || (sideTwo <= 0)) {
            throw new Exception("Неправильно указанны стороны прямоугольника, невозможный прямоугольник");
        }
        this.name = "Прямоугольник";
        if (sideOne > sideOne) {
            length = sideOne;
            width = sideTwo;
        } else {
            length = sideTwo;
            width = sideOne;
        }
        setArea();
        setPerimeter();
        setDiagonal();
        setParams();
    }

    private void setArea() {
        this.area = this.length * this.width;
    }

    private void setPerimeter() {
        this.perimeter = this.length * 2 + this.width * 2;
    }

    private void setDiagonal() {
        this.diagonal = Math.sqrt(Math.pow(this.length, 2) + Math.pow(this.width, 2));
    }

    @Override
    protected void setParams() {
        super.setParams();
        params.add(String.format("Длина диагонали: %.2f", this.diagonal));
        params.add(String.format("Длина: %.2f", this.length));
        params.add(String.format("Ширина: %.2f", this.width));
    }
}
