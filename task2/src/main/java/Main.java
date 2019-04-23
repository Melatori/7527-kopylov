import java.io.*;

public class Main {

    private enum FigureType {
        CIRCLE,
        RECTANGLE,
        TRIANGLE
    }

    public static void main(String[] args) {

        if (args.length <= 0) {
            System.out.println("Неправильно указанны параметры");
            return;
        }
        try {
            String[] fileData = readFile(args[0]);
            if (fileData == null) {
                return;
            }
            String[] params;

            switch (FigureType.valueOf(fileData[0])) {
                case CIRCLE:
                    Circle circle = new Circle(Double.valueOf(fileData[1]));
                    printData(circle, args);
                    break;
                case TRIANGLE:
                    params = fileData[1].split(" ");
                    Triangle triangle = new Triangle(Double.valueOf(params[0]), Double.valueOf(params[1]), Double.valueOf(params[2]));
                    printData(triangle, args);
                    break;
                case RECTANGLE:
                    params = fileData[1].split(" ");
                    Rectangle rectangle = new Rectangle(Double.valueOf(params[0]), Double.valueOf(params[1]));
                    printData(rectangle, args);
                    break;
                default:
                    System.err.println("Ошибка, нет возможности опознать фигуру");
                    break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Некорректно введены параметры фигуры");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Введено недостаточно параметров фигуры");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка инициализации фигуры: " + e.getMessage());
        }
    }

    private static String[] readFile(String fileName) {
        String[] data = new String[2];

        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            data[0] = reader.readLine();
            data[1] = reader.readLine();

        } catch (FileNotFoundException e) {
            System.err.println("Ошибка, файл не найден");
            return null;
        } catch (SecurityException e) {
            System.err.println("Ошибка, файл не доступен");
            return null;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении строки");
            return null;
        }
        return data;
    }

    private static void printData(Figure figure, String[] args) {
        if (args.length == 1) {
            printData(figure);
        } else {
            printData(figure, args[1]);
        }
    }

    private static void printData(Figure data) {
        for (String param : data.getParams()) {
            System.out.println(param);
        }
    }

    private static void printData(Figure data, String out) {
        try (FileWriter fileWriter = new FileWriter(out)) {
            for (String param : data.getParams()) {
                fileWriter.write(param + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
