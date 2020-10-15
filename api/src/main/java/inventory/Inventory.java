package inventory;

import user.User;

public class Inventory {

    private Long id;

    private String itemName;

    private int cost;

    private int amount;

    private String description;

    public Inventory(Long id, String itemName, int cost, int amount, String description) {
        this.id = id;
        this.itemName = itemName;
        this.cost = cost;
        this.amount = amount;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
