package moreti.springframework.spring5recipeapp.converters;

import moreti.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import moreti.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    public static final String DESCRIPTION = "description";
    public static final String STRING_VALUE = "1";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpytObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(STRING_VALUE);
        command.setDescription(DESCRIPTION);

        UnitOfMeasure uom = converter.convert(command);

        assertNotNull(uom);
        assertEquals(STRING_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }
}