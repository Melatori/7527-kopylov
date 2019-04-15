package ru.ctf.focusstart.kopylov;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {

        boolean correctSize = false;
        int size = 0;

        try {

            size = new Scanner(System.in).nextInt();

            if (size < 1) {
                throw new InputMismatchException();
            } else if (size > 32) {
                System.out.println("Размер таблицы больше 32, возможно некорректное отображение");
            }

            correctSize = true;

        } catch (InputMismatchException e) {
            System.err.println("Значение введено некорректно");
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка " + e.toString());
        }

        //После конструкции try-catch, так после конструкции. Правда size пришлось вынести за try-catch и correctSize определить как необходимое условие
        if (correctSize) {
            MathArrayBuilder arrayBuilder = new MathArrayBuilder();
            TablePrinter print = new TablePrinter(arrayBuilder.BuildMulArray(size));
            print.BuildMulTable();
        }
    }
}
