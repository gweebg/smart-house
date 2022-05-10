package suppliers;

import org.jetbrains.annotations.NotNull;
import org.house.House;
import java.util.HashMap;
import java.util.Objects;

public class Supplier{
    private float baseCost;
    private float taxMargin;
    private HashMap<Integer, House> houseList;

    public Supplier(float baseCost, float taxMargin) {
        this.baseCost = baseCost;
        this.taxMargin = taxMargin;
        this.houseList = new HashMap<>();
    }

    public Supplier(){
        this.baseCost = (float)0;
        this.taxMargin = (float)0;
        this.houseList = new HashMap<>();
    }

    public Supplier(@NotNull Supplier input){
        this.baseCost = input.getBaseCost();
        this.taxMargin = input.getTaxMargin();
        this.houseList = input.getHouseList();
    }

    public void setBaseCost(float baseCost) {
        this.baseCost = baseCost;
    }

    public void setTaxMargin(float taxMargin) {
        this.taxMargin = taxMargin;
    }

    public void setHouseList(HashMap<Integer, House> houseList) {
        this.houseList = houseList;
    }

    public float getBaseCost() {
        return baseCost;
    }

    public float getTaxMargin() {
        return taxMargin;
    }

    public HashMap<Integer, House> getHouseList() {
        return houseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Float.compare(supplier.baseCost, baseCost) == 0 && Float.compare(supplier.taxMargin, taxMargin) == 0 && Objects.equals(houseList, supplier.houseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseCost, taxMargin, houseList);
    }

    @Override
    public String toString() {
        return "supplier{" +
                "baseCost=" + baseCost +
                ", taxMargin=" + taxMargin +
                '}';
    }

    
}
