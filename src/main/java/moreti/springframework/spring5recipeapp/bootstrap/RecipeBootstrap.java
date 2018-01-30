package moreti.springframework.spring5recipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import moreti.springframework.spring5recipeapp.domain.*;
import moreti.springframework.spring5recipeapp.repository.CategoryRepository;
import moreti.springframework.spring5recipeapp.repository.RecipeRepository;
import moreti.springframework.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Profile("default")
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        // get UOMs
        Optional<UnitOfMeasure> eachUOMOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tablespoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaspoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByDescription("Pint");

        if (!pintOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        // get optionals
        UnitOfMeasure eachUOM = eachUOMOptional.get();
        UnitOfMeasure tableSpoonUOM = tablespoonOptional.get();
        UnitOfMeasure teaSpoonUOM = teaspoonOptional.get();
        UnitOfMeasure dashUOM = dashOptional.get();
        UnitOfMeasure pintUOM = pintOptional.get();
        UnitOfMeasure cupUOM = cupOptional.get();

        Optional<Category> americanOptional = categoryRepository.findByDescription("American");

        if (!americanOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<Category> italianOptional = categoryRepository.findByDescription("Italian");

        if (!italianOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<Category> fastFoodOptional = categoryRepository.findByDescription("Fast Food");

        if (!fastFoodOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Category americanUOM = americanOptional.get();
        Category mexicanUOM = mexicanOptional.get();
        Category italianUOM = italianOptional.get();
        Category fastFoodUOM = fastFoodOptional.get();

        // Yummy Gauc
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl." +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4zP4L7eQv");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4zP4w9fQ8");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUOM))
                .addIngredient(new Ingredient("Kosher salt", new BigDecimal(.5), teaSpoonUOM))
                .addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUOM))
                .addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUOM))
                .addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(.5), eachUOM))
                .addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tableSpoonUOM))
                .addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUOM))
                .addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(.5), eachUOM));

        guacRecipe.getCategories().add(americanUOM);
        guacRecipe.getCategories().add(mexicanUOM);

        guacRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply Recipes");


        recipes.add(guacRecipe);


        // Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setPrepTime(9);
        tacosRecipe.setCookTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4zP8yh9N6");
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4zP9DIPdg");

        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tableSpoonUOM))
                .addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaSpoonUOM))
                .addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaSpoonUOM))
                .addIngredient(new Ingredient("sugar", new BigDecimal(1), teaSpoonUOM))
                .addIngredient(new Ingredient("salt", new BigDecimal(.5), teaSpoonUOM))
                .addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUOM))
                .addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUOM))
                .addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUOM))
                .addIngredient(new Ingredient("olive oil", new BigDecimal(2), tableSpoonUOM))
                .addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(4), eachUOM))
                .addIngredient(new Ingredient("corn tortillas", new BigDecimal(8), teaSpoonUOM))
                .addIngredient(new Ingredient("packed baby arugula (3 ounces)", new BigDecimal(3), cupUOM))
                .addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUOM))
                .addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUOM))
                .addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(.5), pintUOM))
                .addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(.25), eachUOM))
                .addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUOM))
                .addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(.5), cupUOM))
                .addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), eachUOM));

        tacosRecipe.getCategories().add(americanUOM);
        tacosRecipe.getCategories().add(mexicanUOM);

        recipes.add(tacosRecipe);

        return recipes;
    }
}
