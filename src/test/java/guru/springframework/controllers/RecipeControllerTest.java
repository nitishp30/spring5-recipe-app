package guru.springframework.controllers;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

public class RecipeControllerTest {

	private RecipeController controller;

	@Mock
	private RecipeService service;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new RecipeController(service);
	}

	@Test
	public void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		when(service.findById(1L)).thenReturn(recipe);

		MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
		mvc.perform(MockMvcRequestBuilders.get("/recipe/show/1")) //
				.andExpect(MockMvcResultMatchers.status().isOk()) //
				.andExpect(MockMvcResultMatchers.view().name("recipe/show"));

	}
}
