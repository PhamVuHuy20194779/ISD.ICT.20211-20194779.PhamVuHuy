package PlaceRushOrderTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceRushOrderController;

class ValidateNameTest {

	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	 @ParameterizedTest
	 @CsvSource({
	            "nguyenlmao,true",
	            "nguyen01234,false",
	            "$#nguyen,false",
	            ",false"
	})
	 
	@Test
    public void testName(String name, boolean expected) {
        boolean isValid = placeRushOrderController.validateName(name);
        assertEquals(expected,isValid);
    }

}
