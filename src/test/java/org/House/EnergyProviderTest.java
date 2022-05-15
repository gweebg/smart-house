package org.House;

import org.junit.jupiter.api.Test;
import org.Suppliers.EnergyProvider;
import org.Suppliers.PriceFormulas;

import static org.junit.jupiter.api.Assertions.*;

class EnergyProviderTest
{
    private final EnergyProvider provider = new EnergyProvider(5.00, 12, "Iberdrola");

    @Test
    void getFormulaTest()
    {
        //String filePath = "src/main/java/org/house/formulas.txt";
        //PriceFormulas formulas = new PriceFormulas(filePath);
        //String formula = provider.getFormula();
        //assertTrue(formulas.getFormulas().contains(formula));
    }
}