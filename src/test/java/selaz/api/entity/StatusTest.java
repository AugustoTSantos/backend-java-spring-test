package selaz.api.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import jakarta.persistence.Table;

public class StatusTest {

    @Test
    public void test_entity_mapping_to_status_table() {
        Status status = new Status();
        assertNotNull(status);
        assertEquals("Status", status.getClass().getAnnotation(Table.class).name());
    }

    @Test
    public void test_description_column_allows_null() {
        Status status = new Status();
        status.setDescription(null);
        assertNull(status.getDescription());
    }

}
