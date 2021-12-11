
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidatePhoneNumberTest {
	//Do Kim Tra 20183999
	private PlaceOrderController playOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		playOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"0123456789,true",
		"012345,false",
		"abc123,false",
		"1234567890,false"
	})

	@Test
	public void test(String phone, boolean expected) {
		boolean isValid = playOrderController.validatePhoneNumber(phone);
		assertEquals(expected,isValid);
	}

}
