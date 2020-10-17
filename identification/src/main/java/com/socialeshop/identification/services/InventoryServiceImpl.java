package com.socialeshop.identification.services;

import com.socialEshop.util.http.ServiceUtil;
import com.socialeshop.api.core.inventory.Inventory;
import com.socialeshop.api.core.inventory.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryServiceImpl implements InventoryService {
    private  ServiceUtil serviceUtil;
    private static final Logger LOG = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    public InventoryServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Inventory getInventory(int inventoryId) {
        LOG.debug("/product return the found inventory for inventoryID={}", inventoryId);

        return new Inventory();
    }
}
