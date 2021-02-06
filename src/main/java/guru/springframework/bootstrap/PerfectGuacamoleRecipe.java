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

public class PerfectGuacamoleRecipe {

	private RecipeRepository recipeRepo;
	private CategoryRepository catRepo;
	private UnitOfMeasureRepository uomRepo;
	public static final String DESCRIPTION = "Perfect Guacamole Recipe";

	public PerfectGuacamoleRecipe(RecipeRepository recipeRepo, CategoryRepository catRepo,
			UnitOfMeasureRepository uomRepo) {
		this.recipeRepo = recipeRepo;
		this.catRepo = catRepo;
		this.uomRepo = uomRepo;
	}

	public Recipe getRecipe() {
		Optional<Recipe> out = recipeRepo.findByDescription(DESCRIPTION);
		if (out.isPresent()) {
			return out.get();
		} else {
			Recipe recipe = new Recipe();
			recipe.setDescription(DESCRIPTION);
			recipe.setPrepTime(10);
			recipe.setCookTime(30);
			recipe.setServings(3);
			recipe.setDifficulty(Difficulty.EASY);
			recipe.setDirections(getDescription());
			recipe.setCategories(getCategories());
			for(Ingredient ingr: getIngredients()) {
				recipe.addIngrediant(ingr);
			}
			recipe.setNotes(getNotes());
			recipeRepo.save(recipe);
			return recipe;
		}
	}

	private Notes getNotes() {
		Notes note = new Notes();
		note.setRecipeNotes(
				"To store, cover with plastic wrap so that no air touches the guacamole, chill until ready to serve.");
		return note;
	}

	private String getDescription() {
		return "1 Mash the avocados: Mash up the flesh of two avocados with a fork. Don't overdo it, the guacamole should have some texture.\n"
				+ "2 Add lime juice, salt, onion, strawberries, dried cranberries, cilantro: Stir in the lime juice and salt. Stir in the red onion, the chopped dried cranberries, and most of the strawberries (reserve a few chopped strawberries for the top).\n"
				+ "3 Adjust salt and lime juice to taste: Taste and adjust salt and lime juice if needed. Guacamole is all about balance of the creamy flesh of the avocado with the acid in the lime and the salt.\n"
				+ "4 Top with more strawberries: Serve topped with remaining chopped strawberries. Serve with tortilla chip\n";
	}

	private Set<Category> getCategories() {
		Set<Category> catg = new HashSet<>();
		catg.add(catRepo.findByDescription("American").get());
		catg.add(catRepo.findByDescription("Mexican").get());
		return catg;
	}

	private Set<Ingredient> getIngredients() {
		Set<Ingredient> list = new HashSet<>();
		list.add(newInstance(new BigDecimal(2), //
				null, "2 ripe avocados, peeled and seeded"));
		list.add(newInstance(new BigDecimal(0.5), //
				uomRepo.findByDescription("Teaspoon").get(), //
				"1/2 teaspoon salt"));
		list.add(newInstance(new BigDecimal(1), //
				uomRepo.findByDescription("Tablespoon").get(), //
				"1 Tbsp lime or lemon juice"));
		list.add(newInstance(new BigDecimal(0.25), //
				uomRepo.findByDescription("Cup").get(), //
				"1/4 cup minced red onion"));
		list.add(newInstance(new BigDecimal(1), //
				uomRepo.findByDescription("Cup").get(), //
				"1 cup chopped cored strawberries"));
		list.add(newInstance(new BigDecimal(0.25), //
				uomRepo.findByDescription("Cup").get(), //
				"1/4 cup chopped sweetened dried cranberries (omit for paleo version)"));
		list.add(newInstance(new BigDecimal(2), //
				uomRepo.findByDescription("Tablespoon").get(), //
				"2 Tbsp chopped cilantro (optional)"));
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
