package suppliers;

import org.exceptions.NegativeFormulaIdException;

public class TaxFormulas {
    private float baseCost;
    private float tax;
    private float powerUsed;
    private int formulaID;

    public float getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(float baseCost) {
        this.baseCost = baseCost;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getPowerUsed(){
        return this.powerUsed;
    }

    public void setPowerUsed(float powerUsed){
        this.powerUsed = powerUsed;
    }

    public int getFormulaID(){
        return this.formulaID;
    }

    public void setFormulaID(int formulaID) throws NegativeFormulaIdException{
        if(formulaID < 1) {throw new NegativeFormulaIdException("Negative formula ID.");}
        else {this.formulaID = formulaID;}
    }

    public TaxFormulas(float baseCost, float tax, int formulaID) throws NegativeFormulaIdException{
        this.baseCost = baseCost;
        this.tax = tax;
        if(formulaID < 1) {throw new NegativeFormulaIdException("Formula ID is negative or null.");}
        else {this.formulaID = formulaID;}
    }

    public TaxFormulas(){
        this.baseCost = 0;
        this.tax = 0;
        this.formulaID = 1;
    }

    public TaxFormulas(TaxFormulas input){
        this.tax = input.getTax();
        this.baseCost = input.getBaseCost();
        this.formulaID = input.getFormulaID();
    }

    public double formula1(){
        return (0.9*(1+this.tax)*this.powerUsed + this.baseCost);
    }

    public double formula2(){
        if(powerUsed > 20)
            return 0.75*(1+this.tax) * this.powerUsed + this.baseCost;
        else
            return 0.9*(1+this.tax) * this.powerUsed + this.baseCost;
    }

    public double formula3(){
        double r = (1+this.tax)*this.powerUsed+this.baseCost;
        return r < 200 ? r : (double)200;
    }

    public double getPrice() throws NegativeFormulaIdException{
        double r;
        switch (this.formulaID)
        {
            case 1:
                r = formula1();
                break;
            case 2:
                r = formula2();
                break;
            case 3:
                r = formula3();
                break;
            default:
                throw new NegativeFormulaIdException("Formula ID not within created formulas.");
        }
        return r;
    }
}
