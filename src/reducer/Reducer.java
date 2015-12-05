package reducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Reducer {
	private HashMap<String,List<Integer>> input = new HashMap<>();
	
	public HashMap<String, List<Integer>> getInput() {
		return input;
	}

	public void setInput(HashMap<String, List<Integer>> input) {
		this.input = input;
	}

	public void showReducerInput(){
		for(Entry<String, List<Integer>> entry : input.entrySet()){
			System.out.println("("+ entry.getKey() + ", " + entry.getValue() + ")");
		}
	}
}
