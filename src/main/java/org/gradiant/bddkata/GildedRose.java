package org.gradiant.bddkata;

import java.util.List;


public class GildedRose {

    private Inventory inventory = new Inventory();

    public static void main(String[] args) {
        new GildedRose();
    }

    public GildedRose() {
        System.out.println("OMGHAI!");

        updateQuality();
    }

    public GildedRose(Inventory inventory) {
        this.inventory = inventory;
    }

    public void updateQuality() {
        List<Item> items = inventory.getItems();

        for (int i = 0; i < items.size(); i++) {
            int currentQuality = items.get(i).getQuality();
            int currentSellIn = items.get(i).getSellIn();

            items.get(i).setQuality(currentQuality-1);
            items.get(i).setSellIn(currentSellIn-1);
        }
    }

}