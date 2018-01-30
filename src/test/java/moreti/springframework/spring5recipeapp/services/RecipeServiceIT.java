package moreti.springframework.spring5recipeapp.services;

import moreti.springframework.spring5recipeapp.commands.RecipeCommand;
import moreti.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import moreti.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import moreti.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import moreti.springframework.spring5recipeapp.domain.Recipe;
import moreti.springframework.spring5recipeapp.repository.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeServiceImpl recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UnitOfMeasureServiceImpl unitOfMeasureService;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }

}