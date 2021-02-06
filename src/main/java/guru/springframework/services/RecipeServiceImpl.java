package guru.springframework.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository repo;

	public RecipeServiceImpl(RecipeRepository repo) {
		this.repo = repo;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> out = new HashSet<>();
		repo.findAll().iterator().forEachRemaining(v -> out.add(v));
		return out;
	}

}
