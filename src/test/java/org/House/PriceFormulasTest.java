package org.House;

import org.junit.jupiter.api.Test;
import org.Suppliers.PriceFormulas;

import static org.junit.jupiter.api.Assertions.*;

class PriceFormulasTest {

    private final PriceFormulas formulas = new PriceFormulas("src/main/java/org/house/formulas.txt");

    @Test
    void constructorTest()
    {
        System.out.println(formulas);
    }

    @Test
    void getRandomFormula()
    {
        String formula = formulas.getRandomFormula();
        System.out.println(formula);
        assertTrue(formulas.getFormulas().contains(formula));
    }
}