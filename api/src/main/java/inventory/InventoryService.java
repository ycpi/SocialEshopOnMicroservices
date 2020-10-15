package inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import user.User;

public interface InventoryService {
    @GetMapping(
            value    = "/category/{inventoryId}",
            produces = "application/json")
    User getUser(@PathVariable int inventoryId);
}
