import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.Test;


class DataReaderTest {

	@Test
	void testReadHomeDataOneBedroom() {
		DataReader dr = new DataReader();
		dr.readHomeData("Zip_Zhvi_1bedroom.csv",1);
		
		HashMap<String, ZipCode> hashOfHomePrices = dr.getHashMapOfHomePrices();
		int oneBedroomPrice = hashOfHomePrices.get("93242").getOneBedroomPrice();
		
		
		assertEquals(oneBedroomPrice, 110809);
	}
	
	@Test
	void testReadHomeDataTwoBedroom() {
		DataReader dr = new DataReader();
		dr.readHomeData("Zip_Zhvi_1bedroom.csv",2);
		
		HashMap<String, ZipCode> hashOfHomePrices = dr.getHashMapOfHomePrices();
		int twoBedroomPrice = hashOfHomePrices.get("90806").getTwoBedroomPrice();
		
		
		assertEquals(twoBedroomPrice, 357615);
	}

}
