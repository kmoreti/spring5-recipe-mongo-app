package moreti.springframework.spring5recipeapp.services;

import moreti.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import moreti.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import moreti.springframework.spring5recipeapp.domain.UnitOfMeasure;
import moreti.springframework.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private UnitOfMeasureService unitOfMeasureService;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() {
        // given
        Set<UnitOfMeasure> uoms = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setId("2");

        uoms.add(uom1);
        uoms.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(uoms);

        //when
        Set<UnitOfMeasureCommand> unitOfMeasureCommands = unitOfMeasureService.listAllUoms();

        //then
        assertEquals(2, uoms.size());
        verify(unitOfMeasureRepository, times(1)).findAll();

    }
}