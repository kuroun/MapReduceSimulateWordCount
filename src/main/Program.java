package main;

import java.util.ArrayList;

import reducer.Reducer;
import mapper_with_combiner.MapperWithCombiner;
import mapper_without_combiner.MapperWithoutCombiner;

public class Program {

	public static void main(String[] args) {
		
		DataNode d1 = new DataNode();
		ArrayList<String> rawData1 = d1.readFile(System.getProperty("user.dir") + "\\src\\main\\doc1.txt");
		DataNode d2 = new DataNode();
		ArrayList<String> rawData2 = d2.readFile(System.getProperty("user.dir") + "\\src\\main\\doc2.txt");
		DataNode d3 = new DataNode();
		ArrayList<String> rawData3 = d3.readFile(System.getProperty("user.dir") + "\\src\\main\\doc3.txt");
		
		
		System.out.println("=========== without combiner ==========\n");
		//3 Mappers
		MapperWithoutCombiner withoutCombiner1 = new MapperWithoutCombiner();
		MapperWithoutCombiner withoutCombiner2 = new MapperWithoutCombiner();
		MapperWithoutCombiner withoutCombiner3 = new MapperWithoutCombiner();
		
		// 3 Reducers
		Reducer reducerWithoutCombiner1 = new Reducer();
		Reducer reducerWithoutCombiner2 = new Reducer();
		Reducer reducerWithoutCombiner3 = new Reducer();
		ArrayList<Reducer> listReducer = new ArrayList<Reducer>();
		listReducer.add(reducerWithoutCombiner1);
		listReducer.add(reducerWithoutCombiner2);
		listReducer.add(reducerWithoutCombiner3);
		
		System.out.println("Mapper 1 output without combiner");
		withoutCombiner1.mapping(rawData1);
		withoutCombiner1.mapOutput();
		
		System.out.println("Mapper 2 output without combiner");
		withoutCombiner2.mapping(rawData2);
		withoutCombiner2.mapOutput();
		
		System.out.println("Mapper 3 output without combiner");
		withoutCombiner3.mapping(rawData3);
		withoutCombiner3.mapOutput();
		
		withoutCombiner1.reducerInput(listReducer);
		withoutCombiner2.reducerInput(listReducer);
		withoutCombiner3.reducerInput(listReducer);
		
		for(int i = 0 ; i< listReducer.size(); i++){
			System.out.println("Reducer " + (i+1) + " input:");
			listReducer.get(i).showReducerInput();
		}
		int countWithoutCombiner = withoutCombiner1.getCount_transfer() + withoutCombiner2.getCount_transfer() + withoutCombiner3.getCount_transfer();
		System.out.println("\nTotal number of tokens that needs to be transported across the network: " + countWithoutCombiner);
		
		System.out.println("\n=========== with combiner ==========\n");
		
		//3 Mappers
		MapperWithCombiner withCombiner1 = new MapperWithCombiner();
		MapperWithCombiner withCombiner2 = new MapperWithCombiner();
		MapperWithCombiner withCombiner3 = new MapperWithCombiner();
		
		// 3 Reducers
		Reducer reducerCombiner1 = new Reducer();
		Reducer reducerCombiner2 = new Reducer();
		Reducer reducerCombiner3 = new Reducer();
		ArrayList<Reducer> listReducer2 = new ArrayList<Reducer>();
		listReducer2.add(reducerCombiner1);
		listReducer2.add(reducerCombiner2);
		listReducer2.add(reducerCombiner3);
		
		System.out.println("Mapper 1 output with combiner");
		withCombiner1.mapping(rawData1);
		withCombiner1.mapOutput();
		
		System.out.println("Mapper 2 output with combiner");
		withCombiner2.mapping(rawData2);
		withCombiner2.mapOutput();
		
		System.out.println("Mapper 3 output with combiner");
		withCombiner3.mapping(rawData3);
		withCombiner3.mapOutput();
		
		withCombiner1.reducerInput(listReducer2);
		withCombiner2.reducerInput(listReducer2);
		withCombiner3.reducerInput(listReducer2);
		
		for(int i = 0 ; i< listReducer2.size(); i++){
			System.out.println("Reducer " + (i+1) + " input:");
			listReducer2.get(i).showReducerInput();
		}
		int countWithCombiner = withCombiner1.getCount_transfer() + withCombiner2.getCount_transfer() + withCombiner3.getCount_transfer();
		System.out.println("\nTotal number of tokens that needs to be transported across the network: " + countWithCombiner);

	}

}
