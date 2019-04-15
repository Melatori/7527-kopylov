package ru.ctf.focusstart.kopylov;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {

        boolean correctSize = false;
        final int MIN_REQUIRED_SIZE = 1;
        final int MAX_CORRECT_SIZE = 32;
        int size = 0;

        try {

            size = new Scanner(System.in).nextInt();

            if (size < MIN_REQUIRED_SIZE) {
                throw new InputMismatchException();
            } else if (size > MAX_CORRECT_SIZE) {
                System.out.println("Размер таблицы больше 32, возможно некорректное отображение");
            }

            correctSize = true;

        } catch (InputMismatchException e) {
            System.err.println("Значение введено некорректно");
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка " + e.toString());
        }

        if (correctSize) {
            MathArrayBuilder arrayBuilder = new MathArrayBuilder();
            TablePrinter print = new TablePrinter(arrayBuilder.buildMulArray(size));
            print.buildMulTable();
        }
    }
}
