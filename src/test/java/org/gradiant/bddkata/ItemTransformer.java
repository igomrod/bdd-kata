package org.gradiant.bddkata;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;
import java.util.Map;

public class ItemTransformer implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(Item.class,
                        (Map<String, String> row) -> {

                            String name = row.get("name");
                            int quality = Integer.parseInt(row.get("quality"));
                            int sellIn = parseRelativeDay(row.get("sellIn"));

                            return new Item(name, sellIn, quality);
                        }
                )
        );
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
