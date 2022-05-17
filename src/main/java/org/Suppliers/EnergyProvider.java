package org.Suppliers;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnergyProvider implements Serializable
{
    private String nameId;
    private double baseCost;
    private double taxMargin;
    private String formula = new PriceFormulas("/home/guilherme/Documents/repos/smart-house/src/main/java/org/House/formulas.txt").getRandomFormula();
    private List<Bill> bills;


    public EnergyProvider(double baseCost, double taxMargin, String name, @NotNull List<Bill> bills)
    {
        this.baseCost = baseCost;
        this.taxMargin = taxMargin;
        this.nameId = name;
        this.bills = bills.stream().map(Bill::clone).collect(Collectors.toList());
    }

    public EnergyProvider()
    {
        this.baseCost = 30;
        this.taxMargin = 7;
        this.nameId = "";
        this.bills = new ArrayList<>();
    }

    public EnergyProvider(@NotNull EnergyProvider other)
    {
        this.baseCost = other.getBaseCost();
        this.taxMargin = other.getTaxMargin();
        this.formula = other.getFormula();
        this.nameId = other.getNameId();
        this.bills = other.getBills().stream().map(Bill::clone).collect(Collectors.toList());
    }

    public double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(double baseCost) {
        this.baseCost = baseCost;
    }

    public double getTaxMargin() {
        return taxMargin;
    }

    public void setTaxMargin(double taxMargin) {
        this.taxMargin = taxMargin;
    }

    public String getFormula() {
        return formula;
    }

    public String getNameId() { return nameId; }
    public void setNameId(String nameId) { this.nameId = nameId; }

    public List<Bill> getBills()
    {
        return this.bills.stream().map(Bill::clone).collect(Collectors.toList());
    }

    public void setBiils(@NotNull List<Bill> bills)
    {
        this.bills = bills.stream().map(Bill::clone).collect(Collectors.toList());
    }
    @Override
    public EnergyProvider clone() { return new EnergyProvider(this); }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnergyProvider that = (EnergyProvider) o;
        return this.formula.equals(that.getFormula()) &&
               this.taxMargin == that.getTaxMargin()  &&
               this.baseCost  == that.getBaseCost();
    }
}
