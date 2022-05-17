package org.Suppliers;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class Bill
{
    /* Class Variables */

    private long deviceNum;
    private double powerUsed;
    private int houseOwner;
    private LocalDate fromDate;
    private LocalDate issueDate;
    private double totalCost;

    /* Class Constructors */

    public Bill()
    {
        this.deviceNum = 0;
        this.powerUsed = 0;
        this.houseOwner = 0;
        this.fromDate = LocalDate.now();
        this.issueDate = LocalDate.now();
        this.totalCost = 0;
    }

    public Bill(long deviceNo, double powerUsed, int houseOwner, LocalDate issueDate, LocalDate fromDate ,double totalCost)
    {
        this.deviceNum = deviceNo;
        this.powerUsed = powerUsed;
        this.houseOwner = houseOwner;
        this.fromDate = fromDate;
        this.issueDate = issueDate;
        this.totalCost = totalCost;
    }

    public Bill(@NotNull Bill input)
    {
        this.deviceNum = input.getDeviceNum();
        this.powerUsed = input.getPowerUsed();
        this.houseOwner = input.getHouseOwner();
        this.fromDate = input.getFromDate();
        this.issueDate = input.getIssueDate();
        this.totalCost = input.getTotalCost();
    }

    /* Getters/Setters */

    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public long getDeviceNum() {
        return deviceNum;
    }
    public void setDeviceNum(long deviceNum) {
        this.deviceNum = deviceNum;
    }

    public double getPowerUsed() {
        return powerUsed;
    }
    public void setPowerUsed(double powerUsed) {
        this.powerUsed = powerUsed;
    }

    public int getHouseOwner() {
        return houseOwner;
    }
    public void setHouseOwner(int houseOwner) {
        this.houseOwner = houseOwner;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /* Common Methods */

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill invoice = (Bill) o;
        return (deviceNum == invoice.deviceNum &&
                Double.compare(invoice.powerUsed, powerUsed) == 0 &&
                houseOwner == invoice.houseOwner &&
                issueDate.equals(invoice.issueDate));
    }

    @Override
    public Bill clone() { return new Bill(this); }

    @Override
    public int hashCode() {
        return Objects.hash(deviceNum, powerUsed, houseOwner, issueDate);
    }

    @Override
    public String toString()
    {
        return "Invoice{" +
                "deviceNo=" + deviceNum +
                ", powerUsed=" + powerUsed +
                ", houseOwner=" + houseOwner +
                ", issueDate=" + issueDate +
                '}';
    }
}
