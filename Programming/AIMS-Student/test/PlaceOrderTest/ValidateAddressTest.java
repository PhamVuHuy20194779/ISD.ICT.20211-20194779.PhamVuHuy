package PlaceOrderTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidateAddressTest {
	
	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
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
        boolean isValid = placeOrderController.validateAddress(address);
        assertEquals(expected,isValid);
    }
}
