package moreti.springframework.spring5recipeapp.repository;

import moreti.springframework.spring5recipeapp.bootstrap.RecipeBootstrap;
import moreti.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryTestIT {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    RecipeBootstrap recipeBootstrap;

    @Before
    public void setUp() throws Exception {
        categoryRepository.deleteAll();
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        recipeBootstrap = new RecipeBootstrap(categoryRepository, recipeRepository, unitOfMeasureRepository);
        recipeBootstrap.onApplicationEvent(null);


    }

    @Test
    public void findByDescription() throws Exception {
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", unitOfMeasure.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() throws Exception {
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", unitOfMeasure.get().getDescription());
    }

}