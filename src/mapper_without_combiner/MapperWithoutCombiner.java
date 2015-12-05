package mapper_without_combiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.INPUT_STREAM;

import reducer.Reducer;

public class MapperWithoutCombiner {
	private int count_transfer = 0;
	private ArrayList<String> key = new ArrayList<String>();
	private ArrayList<Integer> value = new ArrayList<Integer>();
	public void mapping(ArrayList<String> data){
		for(String item : data){
			key.add(item);
			value.add(1);
		}
	}
	
	public void mapOutput(){
		for(int i = 0; i< key.size(); i++){
			System.out.println("("+ key.get(i) + ", " + value.get(i) + ")");
		}
	}
	
	public ArrayList<Reducer> reducerInput(ArrayList<Reducer> listReducer){
		Reducer reducerWithoutCombiner1 = listReducer.get(0);
		Reducer reducerWithoutCombiner2 = listReducer.get(1);
		Reducer reducerWithoutCombiner3 = listReducer.get(2);
		for(int i=0; i< key.size(); i++){
			// p less than term 
			if(key.get(i).compareTo("p") > 0){
				HashMap<String, List<Integer>>input = reducerInput(reducerWithoutCombiner3, i);
				reducerWithoutCombiner3.setInput(input);
			}
			// k greater than term
			else if(key.get(i).compareTo("k") < 0){
				HashMap<String, List<Integer>>input = reducerInput(reducerWithoutCombiner1, i);
				reducerWithoutCombiner1.setInput(input);
			}
			// k equal term
			else{
				HashMap<String, List<Integer>>input = reducerInput(reducerWithoutCombiner2, i);
				reducerWithoutCombiner2.setInput(input);
			}
			count_transfer++;
		}
		return listReducer;
	}
	
	public HashMap<String, List<Integer>> reducerInput(Reducer reducer, int i){
		HashMap<String, List<Integer>> input = reducer.getInput();
		List<Integer> val = new ArrayList<Integer>();
		if(input.containsKey(key.get(i))){
			input.get(key.get(i)).add(1);
		}
		else{
			val.add(1);
			input.put(key.get(i), val);
		}
		return input;
	}

	public ArrayList<String> getKey() {
		return key;
	}

	public ArrayList<Integer> getValue() {
		return value;
	}

	public int getCount_transfer() {
		return count_transfer;
	}
	
	
}
