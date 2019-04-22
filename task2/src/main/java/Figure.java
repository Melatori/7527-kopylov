import java.util.ArrayList;
import java.util.List;

class Figure {
    protected String name;
    protected double perimeter;
    protected double area;
    protected List<String> params = new ArrayList<>();

    protected void setParams() {
        params.add("Тип фигуры: " + name);
        params.add(String.format("Площадь: %.2f", area));
        params.add(String.format("Периметр: %.2f", perimeter));
    }

    public List<String> getParams() {
        return params;
    }
}
