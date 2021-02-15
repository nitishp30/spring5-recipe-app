package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

public class IndexControllerTest {

	private IndexController controller;

	@Mock
	private RecipeService service;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new IndexController(service);
	}

	@Test
	public void testMockMVC() throws Exception {
		MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
		mvc.perform(MockMvcRequestBuilders.get("/")) //
				.andExpect(MockMvcResultMatchers.status().isOk()) //
				.andExpect(MockMvcResultMatchers.view().name("index"));
	}

	@Test
	public void testGetIndexPage() {
		// given
		Set<Recipe> recipes = new HashSet<>();
		Recipe r1 = new Recipe();
		Recipe r2 = new Recipe();
		r1.setId(1L);
		r1.setId(2L);
		recipes.add(r1);
		recipes.add(r2);

		when(service.getRecipes()).thenReturn(recipes);

		ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);

		// when
		String viewName = controller.getIndexPage(model);

		// then
		assertEquals("index", viewName);
		verify(service, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
		Set<Recipe> tmp = captor.getValue();
		assertEquals(recipes.size(), 2);
		assertEquals(recipes, tmp);
	}

}
