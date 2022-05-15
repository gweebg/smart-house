package org.Controller;

import org.Exceptions.ExistingIdException;
import org.Exceptions.NegativeDeviceIdException;
import org.House.Simulation;
import org.Utils.FileLoader;
import org.View.View;

import java.io.IOException;
import java.util.Scanner;

public class Controller
{
    public static Simulation loadData()
    {
        Simulation mySimulation = null;
        double baseCost = 36;
        double tax = 7;

        Scanner userInput = new Scanner(System.in);
        View.loadMenu();
        int option = userInput.nextInt();

        switch (option)
        {
            case 1 -> {
                View.print("Not yet implemented.\n");
            }

            case 2 -> {
                View.loadFromFilePrompt();
                userInput.nextLine();
                String path = userInput.nextLine();
                try
                {
                    FileLoader loader = new FileLoader();
                    loader.loadFromFile(path, baseCost, tax);

                    mySimulation = new Simulation(loader.getEnergyProviders(), loader.getHouses(), baseCost, tax);
                }
                catch (NegativeDeviceIdException | IOException | ExistingIdException e)
                {
                    View.exceptionPrinter(e);
                    System.exit(0);
                }
            }

            case 3 -> {
                View.print("Exiting...\n");
                System.exit(0);
            }
        }

        return mySimulation;
    }
}
