package org.Suppliers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Esta classe é responsável por armazenar diversas fórmulas
 * para a decição de preços dos dispositivos por casas.
 * @author guilherme
 * @version 0.11
 */
public class PriceFormulas
{
    private List<String> formulas;

    PriceFormulas()
    {
        this.formulas = new ArrayList<>();
    }

    public PriceFormulas(String formulasFile)
    {
        this.formulas = new ArrayList<>();

        try
        {
            File file = new File(formulasFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;

            while((line = bufReader.readLine()) != null)
            {
                this.formulas.add(line);
            }
            fileReader.close();
        }
        catch (IOException error)
        {
            error.printStackTrace();
        }
    }

    public List<String> getFormulas()
    {
        return new ArrayList<>(this.formulas);
    }

    public int getSize()
    {
        return this.formulas.size();
    }

    public String getRandomFormula()
    {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, this.formulas.size());
        return this.formulas.get(randomIndex);
    }

    public void addFormula(String formula)
    {
        this.formulas.add(formula);
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (String formula : this.formulas)
        {
            result.append(formula);
            result.append("\n");
        }
        return result.toString();
    }

}
