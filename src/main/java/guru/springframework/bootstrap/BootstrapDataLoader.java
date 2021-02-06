package guru.springframework.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class BootstrapDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private RecipeRepository recipeRepo;
	private CategoryRepository catRepo;
	private UnitOfMeasureRepository uomRepo;

	public BootstrapDataLoader(RecipeRepository recipeRepo, CategoryRepository catRepo,
			UnitOfMeasureRepository uomRepo) {
		this.recipeRepo = recipeRepo;
		this.catRepo = catRepo;
		this.uomRepo = uomRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// create the recipe.
		System.out.println("Created the recipe: "
				+ new PerfectGuacamoleRecipe(recipeRepo, catRepo, uomRepo).getRecipe().getDescription());
		System.out.println("Created the recipe: "
				+ new SpicyGrilledChickenTacoRecipe(recipeRepo, catRepo, uomRepo).getRecipe().getDescription());

	}

}
