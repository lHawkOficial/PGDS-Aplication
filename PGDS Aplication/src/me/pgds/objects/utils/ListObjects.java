package me.pgds.objects.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import me.pgds.utils.RowCreator;

@Getter
public class ListObjects {	

	private RowCreator rowcreator;
	private List<Object> objects = new ArrayList<>();
	private int x, y, maxObjects;
	private Text text;
	
	public ListObjects(int x, int y, int maxObjects, Text text) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.maxObjects = maxObjects;
	}
	
	public void build() {
		
	}
	
}
