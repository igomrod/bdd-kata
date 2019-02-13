package org.gradiant.bddkata;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.DataTableType;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Stepdefs {
    private static final String SOME_NAME = "some name";
    private GildedRose gildedRose;
    private Inventory inventory;
    private Item currentItem;

    @Before
    public void beforeAll() {
        inventory = new Inventory();
        gildedRose = new GildedRose(inventory);
    }

    @Given("^an item with quality (\\d+) and sell by date (.*)")
    public void anItemWithQualityAndSellByDate(int quality, String relativeDay) throws Throwable {
        currentItem = new Item(SOME_NAME, parseRelativeDay(relativeDay), quality);
        inventory.addItem(currentItem);
    }

    @Given("the following items")
    public void theFollowingItems(List<Item> items) {
        items.forEach(item -> inventory.addItem(item));
    }

    @When("^(\\d+) day passed$")
    public void dayPassed(int days) throws Throwable {
        for (int n = 0; n < days; n++) {
            gildedRose.updateQuality();
        }
    }

    @Then("^the item has quality (\\d+)$")
    public void theItemHasQuality(int quality) throws Throwable {
        assertEquals(quality, currentItem.getQuality());
    }

    @And("^the item has sell by date (.*)")
    public void theItemHasSellByDate(String relativeDay) throws Throwable {
        assertEquals(parseRelativeDay(relativeDay), currentItem.getSellIn());
    }

    @Then("(.*) has quality (\\d+) and sell by date (.*)")
    public void item_has_quality_and_sell_by_date_today(String name, int quality,  String relativeDay) {
        Item currentItem = inventory.getItems().stream()
                                .filter(item -> item.getName().equals(name))
                                .findAny().get();


        assertEquals(quality, currentItem.getQuality());
        assertEquals(parseRelativeDay(relativeDay), currentItem.getSellIn());
    }

    private int parseRelativeDay(String relativeDay) {
        if (relativeDay.equalsIgnoreCase("yesterday")) {
            return -1;
        }
        if (relativeDay.equalsIgnoreCase("today")) {
            return 0;
        }
        if (relativeDay.equalsIgnoreCase("tomorrow")) {
            return 1;
        }
        throw new IllegalArgumentException(relativeDay + " is not understood.");
    }

}
