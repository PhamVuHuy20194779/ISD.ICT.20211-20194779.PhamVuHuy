package PlaceOrderTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidateNameTest {
	
	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	 @ParameterizedTest
	 @CsvSource({
	            "nguyenlm,true",
	            "nguyen01234,false",
	            "$#nguyen,false",
	            ",false"
	})
	 
	@Test
    public void testName(String name, boolean expected) {
        boolean isValid = placeOrderController.validateName(name);
        assertEquals(expected,isValid);
    }

}
