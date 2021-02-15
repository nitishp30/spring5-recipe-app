package guru.springframework.services;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl service;

	@Mock
	RecipeRepository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new RecipeServiceImpl(repository);
	}

	@Test
	public void test() {
		Recipe recipe = new Recipe();
		Set<Recipe> out = new HashSet<>();
		out.add(recipe);

		Mockito.when(repository.findAll()).thenReturn(out);

		assertEquals(service.getRecipes().size(), 1);
		Mockito.verify(repository, Mockito.times(1)).findAll();
	}

}
