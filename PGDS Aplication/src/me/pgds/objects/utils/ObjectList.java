package me.pgds.objects.utils;

import javax.swing.ImageIcon;

import lombok.Getter;

@Getter
public class ObjectList {

	private String name, desc;
	private ImageIcon icon;
	private Button select, delete;
	private Boolean canSelect, canDelete;
	
	public ObjectList(Boolean canDelete, Boolean canSelect, Button select, Button delete) {
		this.canDelete = canDelete;
		this.canSelect = canSelect;
		this.select = select;
		this.delete = delete;
	}
	
}
