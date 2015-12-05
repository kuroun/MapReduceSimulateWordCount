package mapper_with_combiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import reducer.Reducer;

public class MapperWithCombiner {
	private int count_transfer = 0;
	private HashMap<String, Integer> result = new HashMap<String,Integer>();
	public void mapping(ArrayList<String> data){
		for(String item : data){
			if(result.containsKey(item)){
				result.put(item, result.get(item)+1);
			}
			else{
				result.put(item, 1);
			}
		}
	}
	
	public void mapOutput(){
		for(Entry<String, Integer> entry : result.entrySet()){
			System.out.println("("+ entry.getKey() + ", " + entry.getValue() + ")");
		}
	}
	
	public ArrayList<Reducer> reducerInput(ArrayList<Reducer> listReducer){
		Reducer reducerWithoutCombiner1 = listReducer.get(0);
		Reducer reducerWithoutCombiner2 = listReducer.get(1);
		Reducer reducerWithoutCombiner3 = listReducer.get(2);
		for(Entry<String, Integer> entry : result.entrySet()){
			// p less than term
			if(entry.getKey().compareTo("p") > 0){
				HashMap<String, List<Integer>> input = reducerInput(reducerWithoutCombiner3, entry);
				reducerWithoutCombiner3.setInput(input);
			}
			// k greater than term
			else if(entry.getKey().compareTo("k") < 0){
				HashMap<String, List<Integer>>input = reducerInput(reducerWithoutCombiner1, entry);
				reducerWithoutCombiner1.setInput(input);
			}
			// k equal term
			else{
				HashMap<String, List<Integer>>input = reducerInput(reducerWithoutCombiner2, entry);
				reducerWithoutCombiner2.setInput(input);
			}
			count_transfer++;
		}		
		return listReducer;
	}
	
	public HashMap<String, List<Integer>> reducerInput(Reducer reducer, Entry<String, Integer> entry){
		HashMap<String, List<Integer>> input = reducer.getInput();
		List<Integer> val = new ArrayList<Integer>();
		if(input.containsKey(entry.getKey())){
			input.get(entry.getKey()).add(entry.getValue());
		}
		else{
			val.add(entry.getValue());
			input.put(entry.getKey(), val);
		}
		return input;
	}

	public HashMap<String, Integer> getResult() {
		return result;
	}

	public int getCount_transfer() {
		return count_transfer;
	}
	
	
}
