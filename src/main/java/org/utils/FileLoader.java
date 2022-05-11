package org.utils;

import org.devices.*;
import org.exceptions.ExistingIdException;
import org.exceptions.NegativeDeviceIdException;
import org.house.EnergyProvider;
import org.house.House;
import org.house.Room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader
{
    private List<EnergyProvider> energyProviders;
    private List<House> houses;

    private final double baseCost = 12.0;
    private final double taxMargin = 7.0;

    FileLoader()
    {
        this.energyProviders = new ArrayList<>();
        this.houses = new ArrayList<>();
    }

    public void load(String filePath)
    {
        int currentId = 0;

        try
        {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;

            House currentHouse = null;
            Room currentRoom = null;

            while((line = bufReader.readLine()) != null)
            {
                char firstChar = line.charAt(0);

                if (firstChar == 'F')
                {
                    /* Fornecedor:EDA */
                    String[] parts = line.split(":");
                    energyProviders.add(new EnergyProvider(baseCost, taxMargin, parts[1]));
                }

                if (firstChar == 'C')
                {
                    /* Casa:Vicente de Carvalho Castro,365597405,Iberdrola */

                    if (currentHouse != null && currentRoom != null)
                    {
                        currentHouse.setRoom(currentRoom);
                        houses.add(currentHouse);
                    }

                    String[] parts = line.split(":");
                    String[] args = parts[1].split(",");

                    House newHouse = new House();
                    newHouse.setOwnerName(args[0]);
                    newHouse.setOwnerNIF(Integer.parseInt(args[1]));

                    this.energyProviders
                            .stream()
                            .filter(e -> e.getNameId().equals(args[2]))
                            .findAny().ifPresent(provider -> newHouse.setEnergyProvider(provider.clone()));

                    currentHouse = new House(newHouse);
                }

                if (firstChar == 'D')
                {
                    /* Divisao:Sala de Jantar 1 */
                    Room newRoom = new Room(line);
                    currentRoom = new Room(newRoom);
                    if (currentHouse != null) currentHouse.setRoom(currentRoom);
                }

                String[] parts = line.split(":");
                String[] args  = parts[1].split(",");

                //TODO Adicionar o consumo de cada device.
                switch (parts[0]) {
                    case "SmartBulb" -> {
                        /* SmartBulb:Neutral,7,9.35 */

                        SmartBulb.Tone tone = SmartBulb.Tone.NEUTRAL;
                        if (args[0].equals("Warm")) tone = SmartBulb.Tone.WARM;
                        if (args[0].equals("Cold")) tone = SmartBulb.Tone.COLD;
                        if (currentRoom != null)
                            currentRoom.insertDevice(new SmartBulb(currentId, "Philips Hue", SmartDevice.State.OFF, tone, Float.parseFloat(args[2])));
                        currentId++;
                    }
                    case "SmartCamera" -> {
                        /* SmartCamera:(1280x720),65,3.84 */

                        Resolution res = new Resolution(args[0]);
                        if (currentRoom != null)
                            currentRoom.insertDevice(new SmartCamera(currentId, "Cannon", SmartDevice.State.OFF, res, Integer.parseInt(args[1])));
                        currentId++;
                    }
                    case "SmartSpeaker" -> {
                        /* SmartSpeaker:2,Radio Renascenca,LG,5.54 */

                        if (currentRoom != null)
                            currentRoom.insertDevice(new SmartSpeaker(currentId, args[2], SmartDevice.State.OFF, Integer.parseInt(args[0]), args[1], args[2]));
                        currentId++;
                    }
                }
            }
            fileReader.close();
        }
        catch (IOException | NegativeDeviceIdException | ExistingIdException error)
        {
            error.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        result.append("Providers: \n");
        for (EnergyProvider e : this.energyProviders)
        {
            result.append(e.getNameId());
            result.append(" ");
            result.append(e.getFormula());
            result.append("\n");
        }

        result.append("\nHouses:\n");
        for (House h : this.houses)
        {
            result.append(h.getOwnerName());
            result.append(" ");
            result.append(h.getOwnerNIF());
            result.append("\n");
        }

        return result.toString();

    }
}
