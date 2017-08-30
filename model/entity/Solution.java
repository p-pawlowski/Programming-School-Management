package entity;

import java.sql.*;
import java.time.LocalDate;

public class Solution {
	
	//POLE KLASY
	private Integer id;
	private Timestamp created;
	private Timestamp updated;
	private String description;
	private Integer excerciseId;
	private Integer userId;
	
	//KONSTRUKTOR
	public Solution(Integer id, Timestamp created, Timestamp updated, String description, Integer excerciseId,
			Integer userId) {
		this.id = id;
		this.created = created;
		this.updated = updated;
		this.description = description;
		this.excerciseId = excerciseId;
		this.userId = userId;
	}
	
	//KONSTRUKTOR DOMYSLNY
	public Solution(){
	}
	
	//GETTERY SETTERY
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getCreated() {
		return created;
	}
	public LocalDate getCreatedDate (){
		return created.toLocalDateTime().toLocalDate();
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public LocalDate getUpdatedDate() {
		return updated.toLocalDateTime().toLocalDate();
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getExcerciseId() {
		return excerciseId;
	}
	public void setExcerciseId(Integer excerciseId) {
		this.excerciseId = excerciseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Solution [id=" + id + ", created=" + created + ", updated=" + updated + ", description=" + description
				+ ", excerciseId=" + excerciseId + ", userId=" + userId + "]\n";
	}

	
	
	
	
	
}
