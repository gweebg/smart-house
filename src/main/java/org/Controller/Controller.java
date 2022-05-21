package org.Controller;

import org.Devices.SmartDevice;
import org.Exceptions.*;
import org.House.House;
import org.House.Simulation;
import org.Suppliers.Bill;
import org.Suppliers.EnergyProvider;
import org.Utils.FileLoader;
import org.View.View;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Controller
{
    public static Simulation loadData()
    {

        Simulation mySimulation = null;

        Scanner userInput = new Scanner(System.in);
        View.loadMenu();
        int option = userInput.nextInt();

        switch (option)
        {
            /*
              [1] From binary file.
              [2] From text file.
              [3] Quit from program.
            */

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

            case 3 -> View.exitMessage();
        }

        return mySimulation;
    }

    public static void listMenu(Simulation sim, int option)
    {
        switch (option)
        {
            case 1 -> View.print(sim.listHouses()          );
            case 2 -> View.print(sim.listHousesRooms()     );
            case 3 -> View.print(sim.listAll()             );
            case 4 -> View.print(sim.listEnergyProviders() );
            case 5 -> {
                Scanner listInput = new Scanner(System.in);
                View.printProviderBillPrompt();
                View.print(sim.listBillsFromProvider(listInput.nextLine()));
            }
        }

        View.promptEnterKey();
    }

    public static void executeQuery(Simulation sim, int option)
    {
        Scanner queryInput = new Scanner(System.in);

        switch (option)
        {
            case 1 -> {
                House resultHouse = sim.queryHouseSpentMore();
                String queryResult = sim.displayQueryResultOne(resultHouse);

                View.print(queryResult);
            }

            case 2 -> {
                EnergyProvider resultProvider = sim.getProviderMostBills();
                String queryResult = sim.displayQueryResultTwo(resultProvider);

                View.print(queryResult);
            }

            case 3 -> {
                View.printProviderBillPrompt();

                String provider = queryInput.nextLine();
                View.print(sim.listBillsFromProvider(provider));
            }

            case 4 -> {
                try
                {
                    View.displayCurrentDate(sim.getCurrentDate());

                    View.forthQueryPrompt(true);
                    LocalDate dateStart = LocalDate.parse(queryInput.nextLine());

                    View.forthQueryPrompt(false);
                    LocalDate dateEnd   = LocalDate.parse(queryInput.nextLine());

                    Map<Integer, Double> result = sim.largestConsumerOnTimeInterval(dateStart, dateEnd);
                    String queryResult = sim.displayQueryResultThree(result);

                    View.print(queryResult);
                }
                catch (DateTimeParseException | InvalidDateIntervalException e)
                {
                    View.exceptionPrinter(e);
                }
            }
        }

        View.promptEnterKey();
    }

    public static void editMenu(Simulation simulation, int option)
    {
        /*
          [1] Energy provider.
          [2] House.
        */

        Scanner editMenuInput = new Scanner(System.in);

        try
        {
            View.editOption(option);
            if (option == 1)
            {
                /*
                  [?] Choose a provider:
                */

                String availableProviders = simulation.getAvailableProvidersAsString();
                View.print(availableProviders);

                String chosenProvider = editMenuInput.nextLine();

                /*
                  [1] Edit base cost (double).
                  [2] Edit tax value (double).
                  [3] Edit formula (string).
                */

                View.editProvider();
                int providerOption = editMenuInput.nextInt();

                switch (providerOption)
                {
                    case 1 -> {
                        View.print("New value (double): ");

                        double newBaseCost = editMenuInput.nextDouble();
                        simulation.changeBaseCostProvider(chosenProvider, newBaseCost);
                    }

                    case 2 -> {
                        View.print("New value (double): ");

                        double newTaxValue = editMenuInput.nextDouble();
                        simulation.changeTaxProvider(chosenProvider, newTaxValue);
                    }

                    case 3 -> {
                        View.print("New value (string): ");

                        editMenuInput.nextLine();
                        String newFormula = editMenuInput.nextLine();
                        simulation.changeFormulaProvider(chosenProvider, newFormula);
                    }
                }
            }

            if (option == 2)
            {
                String availableHouses = simulation.getAvailableHousesAsString();
                View.print(availableHouses);

                int chosenHouseNIF = editMenuInput.nextInt();

                String availableRoomsFromHouse = simulation.getAvailableRoomsFromHouseAsString(chosenHouseNIF);
                View.print(availableRoomsFromHouse);

                editMenuInput.nextLine();
                String roomName = editMenuInput.nextLine();

                String availableDevicesFromHouseRoom = simulation.getAvailableDevicesFromRoom(chosenHouseNIF, roomName);
                View.print(availableDevicesFromHouseRoom);

                int deviceId = editMenuInput.nextInt();

                View.print("[>] Change device state to (on/off): ");
                editMenuInput.nextLine();
                String stateString = editMenuInput.nextLine();

                SmartDevice.State state = SmartDevice.State.OFF;
                if (stateString.equals("on")) state = SmartDevice.State.ON;

                simulation.setDeviceToStateByHouse(chosenHouseNIF, roomName, deviceId, state);
            }

            if (option == 3)
            {
                String availableHouses = simulation.getAvailableHousesAsString();
                View.print(availableHouses);

                int houseNIF = editMenuInput.nextInt();

                View.editHouse();
                int editOption = editMenuInput.nextInt();

                if (editOption == 1)
                {
                    String availableProviders = simulation.getAvailableProvidersAsString();
                    View.print(availableProviders);

                    editMenuInput.nextLine();
                    String newProvider = editMenuInput.nextLine();

                    simulation.setHouseProviderById(houseNIF, newProvider);
                }
            }
        }
        catch ( NonexistentDeviceException | NonexistentRoomException | NonExistentHouseException | NonexistentProviderException | InputMismatchException e)
        {
            View.exceptionPrinter(e);
        }
    }

    public static void run()
    {
        Scanner user = new Scanner(System.in);
        Simulation simulation = Controller.loadData();

        if (simulation == null)
        {
            View.print("Error while loading simulation data, please check if the files are well made or the binary file was not overwritten.\n");
            System.exit(1);
        }

        while (true)
        {
            View.mainMenu();

            try
            {
                int option = user.nextInt();
                switch (option)
                {
                    /*
                      [1] Edit data.
                      [2] Simulate passage of time.
                      [3] List stored data.
                      [4] Execute queries.
                      [5] Save current state.
                      [6] Quit from program.
                    */

                    case 1 -> {

                        /*
                          [1] Energy provider.
                          [2] House.
                        */

                        View.editPrompt();
                        int section = user.nextInt();
                        Controller.editMenu(simulation, section);
                    }

                    case 2 -> {

                        View.simulationPrompt(simulation.getCurrentDate());
                        user.nextLine();
                        String nextDate = user.nextLine();

                        try
                        {
                            simulation.simulate(LocalDate.parse(nextDate));
                            View.print("\nSimulation completed, time to check the results!\n\n");
                        }
                        catch (InvalidDateIntervalException | DateTimeParseException e)
                        {
                            View.exceptionPrinter(e);
                        }

                        View.promptEnterKey();
                    }

                    case 3 -> {

                        /*
                          [1] List houses only.
                          [2] List houses and it's rooms only.
                          [3] List houses, rooms and devices.
                          [4] List energy providers.
                          [5] List bills sent by an energy provider.
                        */

                        View.listPrompt();
                        Controller.listMenu(simulation, user.nextInt());
                    }

                    case 4 -> {

                        /*
                          [1] What house used more energy during the simulation period.
                          [2] Which energy provider has the larger volume of billing.
                          [3] List bills by a given energy provider.
                          [4] Sort the largest energy users on a period of time.
                        */

                        View.queryPrompt();
                        Controller.executeQuery(simulation, user.nextInt());
                    }

                    case 5 -> {

                        View.savingPrompt();

                        try
                        {
                            user.nextLine();
                            String newFileName = user.nextLine();

                            FileOutputStream fileOut = new FileOutputStream(newFileName);
                            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

                            objOut.writeObject(simulation);
                            objOut.flush();
                            objOut.close();
                        }
                        catch (InputMismatchException | IOException exception)
                        {
                            View.exceptionPrinter(exception);
                        }
                    }

                    case 6 -> View.exitMessage();
                }
            }
            catch (InputMismatchException exception)
            {
                user.nextLine();
                View.print("Exception: Expected a number but received something else non compatible.\n");
            }

        }
    }
}
