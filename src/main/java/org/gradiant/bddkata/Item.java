package org.gradiant.bddkata;

public class Item {
    private String name;
    private int sellIn;
    private int quality;


    public Item(String name, int sellIn, int quality) {
        this.quality = quality;
        this.name = name;
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }
}
