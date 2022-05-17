package org.House;

import org.Devices.SmartDevice;
import org.Suppliers.Bill;
import org.jetbrains.annotations.NotNull;
import org.Suppliers.EnergyProvider;

import javax.script.ScriptException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation implements Serializable
{
    /* Class Variables */

    private List<EnergyProvider> energyProviders;
    private Map<Integer, House> houses;
    LocalDate currentDate;

    /* Constructor */

    public Simulation(@NotNull List<EnergyProvider> providers, @NotNull List<House> houses)
    {
        this.energyProviders = new ArrayList<>();
        for (EnergyProvider e : providers) this.energyProviders.add(e.clone());

        this.houses = new HashMap<>();
        for (House h : houses) this.houses.put(h.getOwnerNIF(), h.clone());

        this.currentDate = LocalDate.now();
    }

    /* Getters/Setters */

    public LocalDate getCurrentDate() { return this.currentDate; }

    /* Class Methods */

    public List<Bill> simulate(@NotNull LocalDate jumpTo) throws ScriptException
    {
        List<Bill> bills = new ArrayList<>();
        long daysDifference = ChronoUnit.DAYS.between(this.currentDate, jumpTo);

        for (House house : houses.values())
        {
            Bill houseBill = new Bill();

            houseBill.setDeviceNum(house.getTotalDevices());
            houseBill.setHouseOwner(house.getOwnerNIF());
            houseBill.setFromDate(this.currentDate);
            houseBill.setIssueDate(jumpTo);
            houseBill.setTotalCost(house.calculateBill(daysDifference));

            bills.add(houseBill);
        }

        this.currentDate = jumpTo;
        return bills;
    }

    /* Common Methods */
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        result.append("Providers: \n");
        for (EnergyProvider e : this.energyProviders)
        {
            result.append(e.getNameId())
                    .append(" ")
                    .append(e.getFormula())
                    .append("\n");
        }

        result.append("\nHouses:\n");
        for (House h : this.houses.values())
        {
            result.append(h.getOwnerName()).append(" ").append(h.getOwnerNIF())
                  .append(" ").append(h.getEnergyProvider()
                  .getNameId()).append(h.getEnergyProvider().getFormula()).append(" \n   Rooms:\n");

            for (Room r : h.getRooms())
            {
                result.append("     ").append(r.getName()).append("\n         Devices:\n");

                for (SmartDevice d : r.getDevices().values())
                {
                    result.append("            ").append(d.getClass())
                          .append(" ").append(d.getDeviceId()).append(" ")
                          .append(d.getDeviceName()).append("\n");
                }

                result.append("\n");
            }

            result.append("\n");
        }

        return result.toString();
    }
}
