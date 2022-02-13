package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) throws IOException{

        tasks = loadDataToDab(FILE_NAME);
        printOptions(OPTIONS);

        wyborAkcji(tasks);

    }

    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;

    public static void printOptions(String[] opt) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option" + ConsoleColors.RESET);
        for (String option : opt) {
            System.out.println(option);
        }
    }

    public static String[][] loadDataToDab(String fileName){

        Path sciezka = Paths.get(fileName);

        if (!Files.exists(sciezka)){
            System.out.println("file not exists");
            System.exit(0);
        }

        String[][] tab= null;

        try {
            List<String> linie = Files.readAllLines(sciezka);
            tab = new String[linie.size()][linie.get(0).split(",").length];

            for (int i = 0; i < linie.size(); i++){

                String[] split = linie.get(i).split(",");

                for (int j = 0; j < split.length; j++){
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }

    public static void wyborAkcji(String[][] tasks){

        Scanner skan = new Scanner(System.in);

        while (skan.hasNextLine()){
            String input = skan.next();
            switch (input){
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask(tasks, getTheNumber());
                    System.out.println("Value was removed successfully");
                    break;
                case "list":
                    listTask(tasks);
                    break;
                case "exit":
                    exitTask(FILE_NAME, tasks);
                    System.out.println(ConsoleColors.RED + "Bye bye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a correct option");
        }
        printOptions(OPTIONS);


        }
    }public static void addTask(){

        Scanner skan = new Scanner(System.in);
        System.out.println("Pleas add task description");
        String description = skan.nextLine();

        System.out.println("Pleas ass task due date");
        String date = skan.nextLine();

        System.out.println("Is your task is important: true / false");
        String important = skan.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length+1);

        tasks[tasks.length-1] = new String[3];
        tasks[tasks.length-1][0] = description;
        tasks[tasks.length-1][1] = date;
        tasks[tasks.length-1][2]= important;

    }
    public static boolean isNumberGreaterThanZero(String input) { //metoda sprawdzająca czy Stringa można zamienić na inta oraz czy jest większa niż zero
        if (NumberUtils.isParsable(input)){
            return Integer.parseInt(input) >= 0;
        }else{
           return false;
        }
    }
    public static int getTheNumber(){ //pobieranie numeru lini do usuniecia

        Scanner skan = new Scanner(System.in);
        System.out.println("Please select number to remove");

        String n = skan.nextLine();

        while (!isNumberGreaterThanZero(n)){
            System.out.println("Incorrect argument, please give a number greater or equal zero");
            skan.nextLine();
        }return Integer.parseInt(n);
    }
    public static void removeTask(String[][] tab, int index){

       try{
           if (index < tab.length){
               tasks = ArrayUtils.remove(tab, index);
           }
       }catch (ArrayIndexOutOfBoundsException ex){
           System.out.println("Element not exists in tab");
       }



    }public static void listTask(String[][] tab){

            for (int row = 0; row < tab.length; row++) {

                System.out.print(row + 1 + ": ");

                for (int col = 0; col < tab[0].length; col++) {

                    if (tab[row][0] != null) {
                        System.out.print(tab[row][col] + ", ");
                    }
                }
                if (tab[row][0] != null) {

                    System.out.println("\n");
                }
            }


    }public static void exitTask(String fileName, String[][] tab){
        Path plik = Paths.get(fileName);

        String[] lines = new String[tab.length];

        for (int i = 0; i < tab.length; i++){
            lines[i] = String.join(",", tab[i]);
        }

        try {
            Files.write(plik, Arrays.asList(lines));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
