package org.view;

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
        s.append("   [3] Create manually.\n");
        s.append("   [4] Quit from program.\n");
        s.append("[>] Option (b to go back): ");

        System.out.println(s);
    }

    public static void mainMenu()
    {
        StringBuilder s = new StringBuilder();

        s.append("[*] Welcome to Smart House Simulator\n");
        s.append("[?] Choose an action:\n");
        s.append("   [1] Generate random configuration.\n");
        s.append("   [2] Jump through time.\n");
        s.append("   [3] Print stored data.\n");
        s.append("   [4] Quit from program.\n");
        s.append("[>] Option (b to go back): ");

        System.out.println(s);
    }

    public static void jumpMenu()
    {
        System.out.println("[*] Jump through time:\n");
        System.out.println("   [?] Enter a date or the amount of days to jump [int/local-datetime]: ");
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

}
