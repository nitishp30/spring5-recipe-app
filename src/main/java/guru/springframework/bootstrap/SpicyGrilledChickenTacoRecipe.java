package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpicyGrilledChickenTacoRecipe {

	private RecipeRepository recipeRepo;
	private CategoryRepository catRepo;
	private UnitOfMeasureRepository uomRepo;
	public static final String DESCRIPTION = "Spicy Grilled Chicken Taco Recipe";

	public SpicyGrilledChickenTacoRecipe(RecipeRepository recipeRepo, CategoryRepository catRepo,
			UnitOfMeasureRepository uomRepo) {
		this.recipeRepo = recipeRepo;
		this.catRepo = catRepo;
		this.uomRepo = uomRepo;
	}

	public Recipe getRecipe() {
		log.debug("Getting recipe for Spicy Grilled Chicken Taco");
		Optional<Recipe> out = recipeRepo.findByDescription(DESCRIPTION);
		if (out.isPresent()) {
			return out.get();
		} else {
			Recipe recipe = new Recipe();
			recipe.setDescription(DESCRIPTION);
			recipe.setPrepTime(20);
			recipe.setCookTime(15);
			recipe.setServings(6);
			recipe.setDifficulty(Difficulty.MODERATE);
			recipe.setDirections(getDirections());
			recipe.setCategories(getCategories());
			for (Ingredient ingr : addIngredients()) {
				recipe.addIngrediant(ingr);
			}
			recipe.setNotes(getNotes());
			recipeRepo.save(recipe);
			return recipe;
		}
	}

	private Notes getNotes() {
		return new Notes();
	}

	private String getDirections() {
		return "1 Prepare a gas or charcoal grill for medium-high, direct heat.\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over."
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side."
				+ "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.";
	}

	private Set<Category> getCategories() {
		Set<Category> catg = new HashSet<>();
		catg.add(catRepo.findByDescription("Mexican").get());
		return catg;
	}

	private Set<Ingredient> addIngredients() {
		Set<Ingredient> list = new HashSet<>();
		list.add(newInstance(new BigDecimal(2), //
				uomRepo.findByDescription("Tablespoon").get(), //
				"2 tablespoons ancho chili powder"));
		list.add(newInstance(new BigDecimal(1), //
				uomRepo.findByDescription("Teaspoon").get(), //
				"1 teaspoon dried oregano"));
		list.add(newInstance(new BigDecimal(1), //
				uomRepo.findByDescription("Teaspoon").get(), //
				"1 teaspoon dried cumin"));
		list.add(newInstance(new BigDecimal(1), //
				uomRepo.findByDescription("Teaspoon").get(), //
				"1 teaspoon sugar"));
		list.add(newInstance(new BigDecimal(0.5), //
				uomRepo.findByDescription("Teaspoon").get(), //
				"1/2 teaspoon salt"));
		list.add(newInstance(new BigDecimal(0), //
				null, //
				"1 clove garlic, finely chopped"));
		list.add(newInstance(new BigDecimal(1), //
				uomRepo.findByDescription("Tablespoon").get(), //
				"1 tablespoon finely grated orange zest"));
		list.add(newInstance(new BigDecimal(3), //
				uomRepo.findByDescription("Tablespoon").get(), //
				"3 tablespoons fresh-squeezed orange juice"));
		list.add(newInstance(new BigDecimal(2), //
				uomRepo.findByDescription("Tablespoon").get(), //
				"2 tablespoons olive oil"));
		list.add(newInstance(new BigDecimal(0), //
				null, //
				"4 to 6 skinless, boneless chicken thighs (1 1/4 pounds)"));
		return list;
	}

	private Ingredient newInstance(BigDecimal amount, //
			UnitOfMeasure uom, String description) {
		Ingredient ingd = new Ingredient();
		ingd.setAmount(amount);
		ingd.setUom(uom);
		ingd.setDescription(description);
		return ingd;
	}
}
