package org.controller;

import org.house.Simulation;
import org.view.View;
import java.util.Scanner;

public class Controller
{
    public static Simulation loadData()
    {
        Simulation sim = null;
        Scanner userInput = new Scanner(System.in);

        View.loadMenu();
        int option = userInput.nextInt();

        switch (option)
        {
            case 1 -> {
                // View.loadFromBin();
            }

            case 2 -> {
                // View.loadFromFile();
            }

            case 3 -> {
                // View.loadCreateManual();
            }

            case 4 -> {
                System.exit(0);
            }
    }

    public static void run()
    {
        Scanner userInput = new Scanner(System.in);
        Simulation sim = Controller.loadData();

        while (true)
        {




        }
    }
}
