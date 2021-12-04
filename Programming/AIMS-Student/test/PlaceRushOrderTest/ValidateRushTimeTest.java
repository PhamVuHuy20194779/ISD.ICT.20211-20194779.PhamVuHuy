package PlaceRushOrderTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceRushOrderController;

class ValidateRushTimeTest {

	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
    @CsvSource({
            "25/07/2001,false",
            "31/12/2021,true",
            "abc,false",
            ",false"
    })
	
	@Test
    public void testRushTime(String time, boolean expected) {
        boolean isValid = placeRushOrderController.validateRushTime(time);
        assertEquals(expected,isValid);
    }

}
