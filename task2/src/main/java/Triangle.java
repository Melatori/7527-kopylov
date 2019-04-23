public class Triangle extends Figure {
    private double sideOne;
    private double angleOne;
    private double sideTwo;
    private double angleTwo;
    private double sideThree;
    private double angleThree;

    public Triangle(double sideOne, double sideTwo, double sideThree) throws IllegalArgumentException {
        if ((sideOne <= 0) || (sideTwo <= 0) || (sideThree <= 0) ||
                (sideOne + sideTwo <= sideThree) || (sideTwo + sideThree <= sideOne) || (sideOne + sideThree <= sideOne)) {
            throw new IllegalArgumentException("Неправильно указанны стороны треугольника, невозможный треугольник");
        }
        this.name = "Треугольник";
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        this.sideThree = sideThree;
        this.perimeter = calcPerimeter(this.sideOne, this.sideTwo, this.sideThree);
        this.area = calcArea(this.sideOne, this.sideTwo, this.sideThree, this.perimeter);
        this.angleOne = calcAngle(this.sideOne, this.sideTwo, this.sideThree);
        this.angleTwo = calcAngle(this.sideTwo, this.sideOne, this.sideThree);
        this.angleThree = calcAngle(this.sideThree, this.sideOne, this.sideTwo);
        setParams();
    }

    private static double calcPerimeter(double sideOne, double sideTwo, double sideThree) {
        return sideOne + sideTwo + sideThree;
    }

    private static double calcArea(double sideOne, double sideTwo, double sideThree, double perimeter) {
        double halfPerimeter = perimeter / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - sideOne) * (halfPerimeter - sideTwo) * (halfPerimeter - sideThree));
    }

    private static double calcAngle(double oppositeSide, double adjacentSideOne, double adjacentSideTwo) {
        return Math.toDegrees(Math.acos((Math.pow(adjacentSideOne, 2) + Math.pow(adjacentSideTwo, 2) - Math.pow(oppositeSide, 2)) / (2 * adjacentSideOne * adjacentSideTwo)));
    }

    @Override
    protected void setParams() {
        super.setParams();
        params.add(String.format("Сторона 1: %.2f ее противолежащий угол: %.2f", sideOne, angleOne));
        params.add(String.format("Сторона 2: %.2f ее противолежащий угол: %.2f", sideTwo, angleTwo));
        params.add(String.format("Сторона 3: %.2f ее противолежащий угол: %.2f", sideThree, angleThree));
    }
}
