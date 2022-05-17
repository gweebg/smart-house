package org.Controller;

import org.Exceptions.ExistingIdException;
import org.Exceptions.NegativeDeviceIdException;
import org.House.Simulation;
import org.Utils.FileLoader;
import org.View.View;

import javax.script.ScriptException;
import java.io.*;
import java.time.LocalDate;
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
                View.loadFromFilePrompt();
                userInput.nextLine();
                String binPath = userInput.nextLine();

                try
                {
                    FileInputStream fileIn = new FileInputStream(binPath);
                    ObjectInputStream objIn = new ObjectInputStream(fileIn);
                    mySimulation = (Simulation) objIn.readObject();
                    objIn.close();
                }
                catch (IOException | ClassNotFoundException e)
                {
                    View.exceptionPrinter(e);
                }
            }

            case 2 -> {
                View.loadFromFilePrompt();
                userInput.nextLine();
                String path = userInput.nextLine();
                try
                {
                    FileLoader loader = new FileLoader();
                    loader.loadFromFile(path);

                    mySimulation = new Simulation(loader.getEnergyProviders(), loader.getHouses());
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

    public static void run()
    {
        Scanner user = new Scanner(System.in);
        Simulation mySim = Controller.loadData();

        if (mySim == null)
        {
            View.print("Error while loading simulation data.\n");
            System.exit(1);
        }

        while (true)
        {
            View.mainMenu();
            int option = user.nextInt();

            switch (option)
            {
                case 1 -> {
                    View.editPrompt();
                }

                case 2 -> {
                    View.simulationPrompt(mySim.getCurrentDate());
                }

                case 3 -> {
                    View.listPrompt();
                }

                case 4 -> {
                    View.queryPrompt();
                }

                case 5 -> {
                    try
                    {
                        FileOutputStream fileOut = new FileOutputStream("SavedSimulation.bin");
                        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

                        objOut.writeObject(mySim);
                        objOut.flush();
                        objOut.close();
                    }
                    catch (IOException e)
                    {
                        View.exceptionPrinter(e);
                    }
                }

                case 6 -> {
                    View.print("Exiting...\n");
                    System.exit(0);
                }
            }

            View.clearScreen();
        }
    }
}
