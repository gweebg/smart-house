package org.House;

import org.Suppliers.Bill;
import org.jetbrains.annotations.NotNull;
import org.Suppliers.EnergyProvider;

import javax.script.ScriptException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation
{
    /* Class Variables */

    private List<EnergyProvider> energyProviders;
    private Map<Integer, House> houses;
    LocalDate currentDate;
    double baseCost;
    double tax;

    /* Constructor */

    public Simulation(@NotNull List<EnergyProvider> providers, @NotNull List<House> houses, double baseCost, double tax)
    {
        this.energyProviders = new ArrayList<>();
        for (EnergyProvider e : providers) this.energyProviders.add(e.clone());

        this.houses = new HashMap<>();
        for (House h : houses) this.houses.put(h.getOwnerNIF(), h.clone());

        this.currentDate = LocalDate.now();
        this.baseCost = baseCost;
        this.tax = tax;
    }

    /* Getters/Setters */

    /* Class Methods */
    public List<Bill> simulate(@NotNull LocalDate jumpTo) throws ScriptException
    {
        List<Bill> bills = new ArrayList<>();
        long daysDifference = ChronoUnit.DAYS.between(this.currentDate, jumpTo);

        for (House house : houses.values())
        {
            house.calculateBill(this.baseCost, this.tax, daysDifference);
        }

        this.currentDate = jumpTo;
        return bills;
    }

    /* Common Methods */
}
