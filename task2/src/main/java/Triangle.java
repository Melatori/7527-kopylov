public class Triangle extends Figure {
    private double sideOne;
    private double angleOne;
    private double sideTwo;
    private double angleTwo;
    private double sideThree;
    private double angleThree;

    public Triangle(double sideOne, double sideTwo, double sideThree) throws Exception {
        if ((sideOne <= 0) || (sideTwo <= 0) || (sideThree <= 0) ||
                (sideOne + sideTwo < sideThree) || (sideTwo + sideThree < sideOne) || (sideOne + sideThree < sideOne)) {
            throw new Exception("Неправильно указанны стороны треугольника, невозможный треугольник");
        }
        this.name = "Треугольник";
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        this.sideThree = sideThree;
        setPerimeter();
        setArea();
        setAngles();
        setParams();
    }

    private void setArea() {
        double halfPerimeter = this.perimeter / 2;
        this.area = Math.sqrt(halfPerimeter * (halfPerimeter - this.sideOne) * (halfPerimeter - this.sideTwo) * (halfPerimeter - this.sideThree));
    }

    private void setPerimeter() {
        this.perimeter = this.sideOne + this.sideTwo + this.sideThree;
    }

    private void setAngles() {
        this.angleOne = Math.acos((Math.pow(this.sideTwo, 2) + Math.pow(this.sideThree, 2) - Math.pow(this.sideOne, 2)) / (2 * this.sideTwo * this.sideThree));
        this.angleTwo = Math.acos((Math.pow(this.sideOne, 2) + Math.pow(this.sideThree, 2) - Math.pow(this.sideTwo, 2)) / (2 * this.sideOne * this.sideThree));
        this.angleThree = Math.acos((Math.pow(this.sideOne, 2) + Math.pow(this.sideTwo, 2) - Math.pow(this.sideThree, 2)) / (2 * this.sideOne * this.sideTwo));
    }

    @Override
    protected void setParams() {
        super.setParams();
        params.add(String.format("Сторона 1: %.2f ее противолежащий угол: %.2f", this.sideOne, this.angleOne));
        params.add(String.format("Сторона 2: %.2f ее противолежащий угол: %.2f", this.sideTwo, this.angleTwo));
        params.add(String.format("Сторона 3: %.2f ее противолежащий угол: %.2f", this.sideThree, this.angleThree));
    }
}
