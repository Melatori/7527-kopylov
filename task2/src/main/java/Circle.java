public class Circle extends Figure {
    private double radius;
    private double diameter;

    public Circle(double radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("Неправильно указан радиус круга, невозможный круг");
        }
        this.radius = radius;
        this.name = "Круг";
        this.perimeter = calcPerimeter(this.radius);
        this.area = calcArea(this.radius);
        this.diameter = calcDiameter(this.radius);

        setParams();
    }

    private static double calcArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    private static double calcPerimeter(double radius) {
        return 2 * Math.PI * radius;
    }

    private static double calcDiameter(double radius) {
        return radius * 2;
    }

    @Override
    protected void setParams() {
        super.setParams();
        params.add(String.format("Радиус: %.2f", radius));
        params.add(String.format("Диаметр: %.2f", diameter));
    }
}
