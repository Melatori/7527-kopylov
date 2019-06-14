package ru.ctf.focusstart.kopylov;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner userInputReader = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String userName = userInputReader.nextLine();

        Socket socket = new Socket("localhost", 4444);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.println(userName);
        writer.flush();

        Thread messageListenerThread = new Thread(() -> {
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    if (reader.ready()) {
                        System.out.println(reader.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        });
        messageListenerThread.start();

        new Thread(() -> {
            try {
                while (true) {
                    String userInput = userInputReader.nextLine();
                    if ("\\q".equals(userInput)) {
                        messageListenerThread.interrupt();
                        socket.close();
                        break;
                    }
                    writer.println(userInput);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}