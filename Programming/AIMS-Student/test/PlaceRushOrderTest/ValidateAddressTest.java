package PlaceRushOrderTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceRushOrderController;

class ValidateAddressTest {

	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
    @CsvSource({
            "hanoi,true",
            "so 15 hai ba trung,true",
            "$# Hanoi,false",
            ",false"
    })
	
	@Test
    public void testAddress(String address, boolean expected) {
        boolean isValid = placeRushOrderController.validateAddress(address);
        assertEquals(expected,isValid);
    }

}
