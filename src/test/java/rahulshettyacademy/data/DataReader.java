package rahulshettyacademy.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonDatToMap()
	{
		//if u passs json file it will scan the entire content of json  and convert that into one 
		//string variavle
		//here we are reading json to string
	String jsonContent=	FileUtils.readFileToString(new File(System.getProperty("user.dir")
			+"src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"));
	
	// convert json content to hashmap.. basically we are convertingstring to hashmap
	// with dependency Jackson Databind we can convert spring content to hashmap
	//download and add to pom .xml
	
	ObjectMapper mapper= new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
	});
	
	// data is a list with two argument
	
	return data;
	}
	
	
	
	
	}
}
