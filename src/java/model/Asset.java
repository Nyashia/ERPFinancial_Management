/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Nyash
 */
public class Asset {
    private int assetId;
    private String name;
    private Date purchaseDate;
    private double cost;
    private double depreciation;

    public Asset() {}

    public Asset(int assetId, String name, Date purchaseDate, double cost, double depreciation) {
        this.assetId = assetId;
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.cost = cost;
        this.depreciation = depreciation;
    }

    public int getAssetId() { return assetId; }
    public void setAssetId(int assetId) { this.assetId = assetId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public double getDepreciation() { return depreciation; }
    public void setDepreciation(double depreciation) { this.depreciation = depreciation; }
}