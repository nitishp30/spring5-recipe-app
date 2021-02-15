package guru.springframework.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository uom;

	@Test
	@DirtiesContext
	public void findByDescription() {
		Optional<UnitOfMeasure> val = uom.findByDescription("Teaspoon");
		assertEquals("Teaspoon", val.get().getDescription());
	}
	
	@Test
	public void findByDescriptionCup() {
		Optional<UnitOfMeasure> val = uom.findByDescription("Cup");
		assertEquals("Cup", val.get().getDescription());
	}

}
