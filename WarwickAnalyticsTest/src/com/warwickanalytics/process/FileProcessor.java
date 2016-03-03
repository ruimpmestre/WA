package com.warwickanalytics.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileProcessor {

	class Record implements Comparable<Record> {
		
		private String id;
		private List<Integer> vars= new ArrayList<Integer>();
		private boolean decision;
		
		Record(String[] components) throws Exception {
			
			this.setId(components[0]);
			this.setDecision(Integer.parseInt(components[components.length-1]) == 1);
			for(int i = 1, size = components.length-1; i<size; ++i) {
				this.addVar(Integer.parseInt(components[i]));
			}
		}

		public boolean isDecision() {
			return decision;
		}

		public void setDecision(boolean decision) {
			this.decision = decision;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public List<Integer> getVars() {
			return vars;
		}

		public Integer getVar(int index) {
			return vars.get(index);
		}

		
		public void addVar(Integer var) {
			this.vars.add(var);
		}
		
		public int getNVars() {
			return this.vars.size();
		}

		@Override
		public int compareTo(Record o) {
			return ((Integer)Integer.parseInt(this.id)).compareTo(Integer.parseInt(o.id));
		}

	
	}

	private String data;
	
	private int nVars = -1;
	
	public FileProcessor(String data) {
		this.data = data;
	}
	
	public String processData() {
		String result = data;
		
		try {
			ArrayList<Record> resultingRecords = processRecords(fetchRecords(this.data));
			result = outputRecords(resultingRecords);
		} catch (Exception e) {
			result = "Error processing file: " + e.getMessage();
		}
		
		return result;
	}
	
	private ArrayList<Record> fetchRecords(String data) throws Exception {
		ArrayList<Record> records = new ArrayList<FileProcessor.Record>();
		String[] lines = data.split("\n");
				
		for(int i = 1, size=lines.length; i<size; ++i) { //start at 1, ignore header

			String[] components = lines[i].split(",");
						
			Record record = new Record(components);
			records.add(record);
		}
		
		return records;
	}
	
	
	private ArrayList<Record> processRecords(ArrayList<Record> records) {

		Set<Record> filteredRecords = new HashSet<FileProcessor.Record>();
		
		
		for(int i = 0, count = getNrVars(records); i<count; ++i) {
			
			//compute limits
			Integer min = Integer.MAX_VALUE;
			Integer max = Integer.MIN_VALUE;
		
			for(Record record: records) {
				if(record.decision) {
					min = Math.min(record.getVar(i), min);
					max = Math.max(record.getVar(i), max);
				}
			}
					
			for(Record record: records) {
				if(considerRecord(record, i, min, max)) {
					filteredRecords.add(record);
				}
			}
			
		}
		
		ArrayList<Record> result = new ArrayList<FileProcessor.Record>(filteredRecords);
		// Sorting
		Collections.sort(result);

		return result;
				
	}
	
	private int getNrVars(ArrayList<Record> records) {
		if(this.nVars==-1) {
			int result = 0;
			if(records.size()>0) {
				result = records.get(0).getNVars();
			}
			this.nVars=result;
		}
		return this.nVars;
	}
	
	private boolean considerRecord(Record record, int index, Integer min, Integer max) {
		return record.decision || (record.getVar(index)>=min && record.getVar(index)<=max);
	}
	
	private String outputRecords(ArrayList<Record> records) {
		String result = outputHeader(getNrVars(records));
		for(Record record:records) {
			result+="\n"+outputRecord(record);
		}

		return result;
		
	}
	
	private String outputHeader(int nVars) {
		String header = "Id";
		for (int i =0; i<nVars; ++i) {
			header+=",Var"+(i+1);
		}
		header+=",Decision";
		return header;
	}
	
	private String outputRecord(Record record) {
		String output = record.getId();
		for (int i =0; i<record.getNVars(); ++i) {
			output+=","+record.getVar(i);
		}
		output+=","+(record.isDecision()?"1":"0");
		return output;
	}
	
}
