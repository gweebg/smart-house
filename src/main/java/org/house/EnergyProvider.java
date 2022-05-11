package org.house;

import org.jetbrains.annotations.NotNull;

public class EnergyProvider
{
    private String nameId;
    private double baseCost;
    private double taxMargin;
    private String formula = new PriceFormulas("src/main/java/org/house/formulas.txt").getRandomFormula();

    public EnergyProvider(double baseCost, double taxMargin, String name)
    {
        this.baseCost = baseCost;
        this.taxMargin = taxMargin;
        this.nameId = name;
    }

    public EnergyProvider()
    {
        this.baseCost = 30;
        this.taxMargin = 7;
        this.nameId = "";
    }

    public EnergyProvider(@NotNull EnergyProvider other)
    {
        this.baseCost = other.getBaseCost();
        this.taxMargin = other.getTaxMargin();
        this.formula = other.getFormula();
        this.nameId = other.getNameId();
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
