import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidateNameTest {
	//Do Kim Tra 20183999
	private PlaceOrderController playOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		playOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"nguyenlm,true",
		"nguyen01234,false",
		"$#nguyen,false",
		"null,false"
	})

	@Test
	public void test(String name, boolean expected) {
		boolean isValid = playOrderController.validateName(name);
		assertEquals(expected,isValid);
	}

}
