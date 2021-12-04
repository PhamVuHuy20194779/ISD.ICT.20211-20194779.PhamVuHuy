package PlaceRushOrderTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceRushOrderController;

class ValidatePhoneNumberTest {

	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"0123456789, true",
		"01234,false",
		"abc123,false",
		"1234567890,false"
	})

	@Test
	void test(String phone, boolean expected) {
		boolean isValid = placeRushOrderController.validatePhoneNumber(phone);
		assertEquals(expected, isValid);
	}

}
