package org.View;

import org.House.House;
import org.Suppliers.EnergyProvider;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Scanner;

public class View
{
    public static void print(Object o) { System.out.print(o); }

    public static void loadMenu()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Welcome to Smart House Simulator\n");
        s.append("[?] Load the data:\n");
        s.append("   [1] From binary file.\n");
        s.append("   [2] From text file.\n");
        s.append("   [3] Quit from program.\n");
        s.append("[>] Option (b to go back): ");

        System.out.print(s);
    }

    public static void loadFromFilePrompt()
    {
        System.out.print("[?] What's the path for the file ? >> ");
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

        System.out.print(s);
    }

    public static void editPrompt()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Edit Menu\n");
        s.append("[?] Choose an element to edit:\n");
        s.append("   [1] Energy provider.\n");
        s.append("   [2] Switch house devices.\n");
        s.append("   [3] House.\n");
        s.append("[>] Option: ");

        System.out.print(s);
    }

    public static void editOption(int option)
    {
        if (option == 1) System.out.print("[?] Enter the name of the energy provider: ");
        if (option == 2) System.out.print("[?] Enter the name of the house owner: ");
    }

    public static void editHouse()
    {
        StringBuilder s = new StringBuilder();

        s.append("[?] Choose an element to edit:\n");
        s.append("   [1] Energy Provider.\n");
        s.append("[>] Option: ");

        System.out.print(s);
    }

    public static void editProvider()
    {
        StringBuilder s = new StringBuilder();

        s.append("   [1] Edit base cost (double).\n");
        s.append("   [2] Edit tax value (double).\n");
        s.append("   [3] Edit formula (string).\n");
        s.append("[>] Option: ");

        System.out.print(s);
    }

    public static void listPrompt()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Listing Menu\n");
        s.append("[?] Choose an option to list:\n");
        s.append("   [1] List houses only.\n");
        s.append("   [2] List houses and it's rooms only.\n");
        s.append("   [3] List houses, rooms and devices.\n");
        s.append("   [4] List energy providers.\n");
        s.append("   [5] List bills sent by a energy provider.\n");
        s.append("[>] Option: ");

        System.out.print(s);
    }

    public static void queryPrompt()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Query Menu\n");
        s.append("[?] Choose a query to execute:\n");
        s.append("   [1] What house used more energy during the simulation period.\n");
        s.append("   [2] Which energy provider has the larger volume of billing.\n");
        s.append("   [3] List bills by a given energy provider.\n");
        s.append("   [4] Sort the largest energy users on a period of time.\n");
        s.append("[>] Option: ");

        System.out.print(s);
    }

    public static void firstQuery(House h)
    {
        System.out.print("[*] Query 1 result:\n");
        System.out.print(h);
        View.promptEnterKey();
    }

    public static void secondQuery(EnergyProvider e)
    {
        System.out.print("[*] Query 2 result:\n");
        System.out.print(e);
        View.promptEnterKey();
    }

    public static void forthQueryPrompt(boolean key)
    {
        if (key) System.out.print("Insert start date (YYYY-mm-dd): ");
        else System.out.print("Insert end date (YYYY-mm-dd): ");
    }

    public static void simulationPrompt(@NotNull LocalDate date)
    {
        System.out.println("Current date: " + date);
        System.out.print("Choose a date to jump to (YYYY-mm-dd): ");
    }

    public static void displayCurrentDate(@NotNull LocalDate date)
    {
        System.out.println("Current date: " + date);
    }

    public static void printProviderBillPrompt()
    {
        System.out.println("[*] Bill Listing for energy provider.");
        System.out.print("   [>] Choose an energy provider (string): ");
    }

    public static void exceptionPrinter(@NotNull Exception e)
    {
        System.out.println(e.getMessage());
    }

    public static void promptEnterKey()
    {
        System.out.print("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void exitMessage()
    {
        System.out.println("Exiting Simulator...");
        System.exit(0);
    }

    public static void savingPrompt()
    {
        System.out.println("[?] Where do you wish to save the file ?");
        System.out.print("   [>] Path: ");
    }
}
