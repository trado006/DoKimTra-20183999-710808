import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidateAddressTest {
	//Do Kim Tra 20183999
	private PlaceOrderController playOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		playOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"hanoi,true",
		"so 15 Hai Ba Trung Ha Noi,true",
		"$# Hanoi,false",
		"null,false"
	})

	@Test
	public void test(String address, boolean expected) {
		boolean isValid = playOrderController.validateAddress(address);
		assertEquals(expected,isValid);
	}

}

