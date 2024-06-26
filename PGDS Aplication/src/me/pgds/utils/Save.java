package me.pgds.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;


public class Save implements Serializable {

	private static final long serialVersionUID = 8549640245219903487L;
	private List<Object> saves;
	private File file;
	
	public Save(File file, Object... saves) {
		this.saves = new ArrayList<>();
		for(Object linha : saves) {
			this.saves.add(linha);
		}
		this.file = file;
		
		Gson builder = new GsonBuilder().create();
		String save = builder.toJson(this.saves);
		try {
			FileWriter wr = new FileWriter(file);
			wr.write(save);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Save(File file, List<Object> saves) {
		this.saves = new ArrayList<>();
		this.saves.addAll(saves);
		this.file = file;
		
		Gson builder = new GsonBuilder().create();
		String save = builder.toJson(this.saves);
		try {
			FileWriter wr = new FileWriter(file);
			wr.write(save);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<Object> load(File file) {
		try {
			Gson gson = new GsonBuilder().create();
			FileReader reader = new FileReader(file);
			JsonReader jsonreader = new JsonReader(reader);
			List<Object> list = gson.fromJson(jsonreader, List.class);
			try {
				jsonreader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
		} catch (JsonIOException e) {
			return new ArrayList<>();
		} catch (JsonSyntaxException e) {
			return new ArrayList<>();
		} catch (FileNotFoundException e) {
			return new ArrayList<>();
		}
	}

	public List<Object> getSaves() {
		return saves;
	}

	public void setSaves(List<Object> saves) {
		this.saves = saves;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
}
