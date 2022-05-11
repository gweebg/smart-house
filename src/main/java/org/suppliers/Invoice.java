package org.suppliers;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice
{

    private int deviceNo;
    private double powerUsed;
    private int houseOwner;
    private LocalDate issueDate;
    private double totalCost;

    public Invoice()
    {
        this.deviceNo = 0;
        this.powerUsed = 0;
        this.houseOwner = 0;
        this.issueDate = LocalDate.of(1970,01,01);
        this.totalCost = 0;
    }

    public Invoice(int deviceNo, double powerUsed, int houseOwner, LocalDate issueDate, double totalCost)
    {
        this.deviceNo = deviceNo;
        this.powerUsed = powerUsed;
        this.houseOwner = houseOwner;
        this.issueDate = issueDate;
        this.totalCost = totalCost;
    }

    public Invoice(@NotNull Invoice input)
    {
        this.deviceNo = input.getDeviceNo();
        this.powerUsed = input.getPowerUsed();
        this.houseOwner = input.getHouseOwner();
        this.issueDate = input.getIssueDate();
        this.totalCost = input.getTotalCost();
    }

    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getDeviceNo() {
        return deviceNo;
    }
    public void setDeviceNo(int deviceNo) {
        this.deviceNo = deviceNo;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;
        return (deviceNo == invoice.deviceNo &&
                Double.compare(invoice.powerUsed, powerUsed) == 0 &&
                houseOwner == invoice.houseOwner &&
                issueDate.equals(invoice.issueDate));
    }

    @Override
    public Invoice clone() {return new Invoice(this); }

    @Override
    public int hashCode() {
        return Objects.hash(deviceNo, powerUsed, houseOwner, issueDate);
    }

    @Override
    public String toString()
    {
        return "Invoice{" +
                "deviceNo=" + deviceNo +
                ", powerUsed=" + powerUsed +
                ", houseOwner=" + houseOwner +
                ", issueDate=" + issueDate +
                '}';
    }
}
