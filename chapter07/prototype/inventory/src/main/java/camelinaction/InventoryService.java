package camelinaction;

import org.apache.camel.converter.IOConverter;

import java.io.IOException;
import java.io.InputStream;

/**
 * Inventory service bean
 */
public class InventoryService {

    public String listInventory() throws IOException {
        // just return fixed items loaded from the classpath
        InputStream is = InventoryService.class.getClassLoader().getResourceAsStream("items.xml");
        return IOConverter.toString(is, null);
    }
}
