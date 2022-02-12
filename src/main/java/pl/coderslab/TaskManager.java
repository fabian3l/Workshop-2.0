package pl.coderslab;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) throws IOException{

        printOptions(OPTIONS);
        tasks(FILE_NAME);
        wyborAkcji();

    }

    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};

    public static void printOptions(String[] opt) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option" + ConsoleColors.RESET);
        for (String option : opt) {
            System.out.println(option);
        }
    }

    public static String[][] tasks(String fileName) throws IOException {


        Path file = Paths.get(fileName);
        long line = 0;
        line = Files.lines(file).count();
        int lines = (int) line;
        String[][] tab = new String[lines][3];
        String[][] noExcuse = null;

        if (!Files.exists(file)) {
            System.out.println("blad");
            return noExcuse;
        } else {
            Scanner scan = new Scanner(file);

            int i = 0;
            while (scan.hasNextLine()) {
                tab[i] = scan.nextLine().split(",");
                i++;
            }

            return tab;
        }
    }
    public static void wyborAkcji(){
        Scanner skan = new Scanner(System.in);
        String input = skan.next();

        switch (input){
            case "add":
                addTask();
                break;
            case "remove":
                removeTask();
                break;
            case "list":
                listTask();
                break;
            case "exit":
                exitTask();
                break;
            default:
                System.out.println("Please select a correct option");

        }
    }public static void addTask(){
        Scanner skan = new Scanner(System.in);
        System.out.println("Pleas add task description");
        String description = skan.nextLine();

        System.out.println("Pleas ass task due date");
        String date = skan.nextLine();

        System.out.println("Is your task is important: true / false");
        String important = skan.nextLine();


        try {
            String[][] result = tasks(FILE_NAME);
            result[]
        } catch (IOException e) {
            e.printStackTrace();
        }



    }public static void removeTask(){

    }public static void listTask(){


            String[][] tab = new String[0][0];

            try {
                tab = tasks(FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int row = 0; row < tab.length; row++) {
                System.out.println(row + 1 + ": ");
                for (int col = 0; col < tab[0].length; col++) {

                    if (tab[row][0] != null) {
                        System.out.print(tab[row][col] + " ");
                    }
                }
                if (tab[row][0] != null) {

                    System.out.println("\n");
                }
            }


    }public static void exitTask(){

    }


}
