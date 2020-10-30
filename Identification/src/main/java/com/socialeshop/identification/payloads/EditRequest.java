package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Inventory;
import com.socialeshop.identification.models.User;

/*
Json Fomat
                    id: orgid,
                    itemName: itemname,
                    cost: price,
                    amount: amount,
                    tag: tag,
                    description: description
 */

public class EditRequest {
    private Long id;
    private String itemname;
    private int cost;
    private int amount;
    private String tag;
    private String description;

    public EditRequest(Long id, String itemName, int cost, int amount, String tag, String description) {
        this.id = id;
        this.itemname = itemName;
        this.cost = cost;
        this.amount = amount;
        this.tag = tag;
        this.description = description;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemname;
    }

    public void setItemName(String itemName) {
        this.itemname = itemName;
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

    public String getTag(){return tag;}

    public void setTag(String tag) {this.tag = tag; }

}
