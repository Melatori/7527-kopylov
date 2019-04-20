import java.io.*;

public class Main {

    private enum FigureType {
        CIRCLE,
        RECTANGLE,
        TRIANGLE
    }

    public static void main(String[] args) {

        if (args.length <= 0) {
            System.out.println("неправильно указанны параметры");
        } else {
            try {
                String[] fileData = readFile(args[0]);
                String[] params;

                switch (FigureType.valueOf(fileData[0])) {
                    case CIRCLE:
                        Circle circle = new Circle(Double.valueOf(fileData[1]));
                        if (args.length == 1) {
                            getData(circle);
                        } else {
                            getData(circle, args[1]);
                        }
                        break;
                    case TRIANGLE:
                        params = fileData[1].split(" ");
                        Triangle triangle = new Triangle(Double.valueOf(params[0]), Double.valueOf(params[1]), Double.valueOf(params[2]));
                        if (args.length == 1) {
                            getData(triangle);
                        } else {
                            getData(triangle, args[1]);
                        }
                        break;
                    case RECTANGLE:
                        params = fileData[1].split(" ");
                        Rectangle rectangle = new Rectangle(Double.valueOf(params[0]), Double.valueOf(params[1]));
                        if (args.length == 1) {
                            getData(rectangle);
                        } else {
                            getData(rectangle, args[1]);
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String[] readFile(String fileName) throws IOException {
        String[] data = new String[2];

        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            data[0] = reader.readLine();
            data[1] = reader.readLine();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (SecurityException e) {
            System.out.println("Нет доступа к файлу");
        }

        return data;
    }

    private static void getData(Circle data) {
        for (String param : data.getParams()) {
            System.out.println(param);
        }
    }

    private static void getData(Circle data, String out) {
        try (FileWriter fileWriter = new FileWriter(out)) {
            for (String param : data.getParams()){
                fileWriter.write(param + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getData(Rectangle data) {
        for (String param : data.getParams()) {
            System.out.println(param);
        }
    }

    private static void getData(Rectangle data, String out) {
        try (FileWriter fileWriter = new FileWriter(out)) {
            for (String param : data.getParams()){
                fileWriter.write(param + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getData(Triangle data) {
        for (String param : data.getParams()) {
            System.out.println(param);
        }
    }

    private static void getData(Triangle data, String out) {
        try (FileWriter fileWriter = new FileWriter(out)) {
            for (String param : data.getParams()){
                fileWriter.write(param + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
