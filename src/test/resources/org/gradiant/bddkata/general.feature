Feature: General business rules

  Scenario: Item degrades by one per day
    Given an item with quality 10 and sell by date tomorrow
    When 1 day passed
    Then the item has quality 9
    And the item has sell by date today

  Scenario: Once the sell by date has passed, Quality degrades twice as fast
    Given an item with quality 10 and sell by date today
    When 1 day passed
    Then the item has quality 8
    And the item has sell by date yesterday

  Scenario: The Quality of an item is never negative
    Given an item with quality 1 and sell by date tomorrow
    When 1 day passed
    And 1 day passed
    Then the item has quality 0
    And the item has sell by date yesterday