package guru.springframework.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository repo;

	public RecipeServiceImpl(RecipeRepository repo) {
		this.repo = repo;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I'm in the service");
		Set<Recipe> out = new HashSet<>();
		repo.findAll().iterator().forEachRemaining(v -> out.add(v));
		return out;
	}

	@Override
	public Recipe findById(Long id) {
		log.debug("Searching for recipe with id = " + id);
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Recipe-" + id + " not found."));
	}

}
