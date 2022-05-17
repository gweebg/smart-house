package org.View;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class View
{
    public static void print(Object o) { System.out.println(o); }

    public static void loadMenu()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Welcome to Smart House Simulator\n");
        s.append("[?] Load the data:\n");
        s.append("   [1] From binary file.\n");
        s.append("   [2] From text file.\n");
        s.append("   [3] Quit from program.\n");
        s.append("[>] Option (b to go back): ");

        System.out.println(s);
    }

    public static void loadFromFilePrompt()
    {
        System.out.println("[?] What's the path for the file ? >> ");
    }

    public static void mainMenu()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Welcome to Smart House Simulator\n");
        s.append("[?] Choose an action:\n");
        s.append("   [1] Edit data.\n");
        s.append("   [2] Simulate passage of time.\n");
        s.append("   [3] List stored data.\n");
        s.append("   [4] Execute queries.\n");
        s.append("   [5] Save current state.\n");
        s.append("   [6] Quit from program.\n");
        s.append("[>] Option: ");

        System.out.println(s);
    }

    public static void editPrompt()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Edit Menu\n");
        s.append("[?] Choose an element to edit:\n");
        s.append("   [1] Energy provider.\n");
        s.append("   [2] House.\n");
        s.append("   [3] Room.\n");
        s.append("   [4] Device.\n");
        s.append("   [5] List all bills.\n");
        s.append("[>] Option: ");

        System.out.println(s);
    }

    public static void queryPrompt()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Listing Menu\n");
        s.append("[?] Choose an option to list:\n");
        s.append("   [1] List houses only.\n");
        s.append("   [2] List houses and it's rooms only.\n");
        s.append("   [3] List houses, rooms and devices.\n");
        s.append("   [4] List energy providers.\n");
        s.append("   [5] List all bills.\n");
        s.append("[>] Option: ");

        System.out.println(s);
    }

    public static void listPrompt()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Query Menu\n");
        s.append("[?] Choose a query to execute:\n");
        s.append("   [1] What house used more energy during the simulation period.\n");
        s.append("   [2] Which energy provider has the larger volume of billing.\n");
        s.append("   [3] List bills by a given energy provider.\n");
        s.append("   [4] Sort the largest energy users on a period of time.\n");
        s.append("[>] Option: ");

        System.out.println(s);
    }

    public static void simulationPrompt(@NotNull LocalDate date)
    {
        System.out.println("Current date: " + date.toString() + "\n");
        System.out.println("Choose a date to jump to (YYYY-mm-dd): ");
    }

    public static void printMenu()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Choose where to print the data:\n");
        s.append("   [1] To file.\n");
        s.append("   [2] To screen.\n");
        s.append("[>] Option (b to go back): ");

        System.out.println(s);
    }

    public static void exceptionPrinter(@NotNull Exception e)
    {
        e.printStackTrace();
    }

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        //for (int i = 0; i < 100; i++) System.out.println("\n");
    }
}
