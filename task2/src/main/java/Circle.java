public class Circle extends Figure {
    private double radius;
    private double diameter;

    public Circle(double radius) throws Exception{
        if (radius <= 0) {
            throw new Exception("Неправильно указан радиус круга, невозможный круг");
        }
        this.radius = radius;
        this.name = "Круг";
        setArea();
        setPerimeter();
        setDiameter();
        setParams();
    }

    private void setArea() {
        this.area = Math.PI * Math.pow(this.radius, 2);
    }

    private void setPerimeter() {
        this.perimeter = 2 * Math.PI * this.radius;
    }

    private void setDiameter() {
        this.diameter = this.radius * 2;
    }

    @Override
    protected void setParams() {
        super.setParams();
        params.add(String.format("Радиус: %.2f", this.radius));
        params.add(String.format("Диаметр: %.2f", this.diameter));
    }
}
