package entity;

public class Excercise {
	
	//POLE KLASYme=zadanie+3
	private Integer id;
	private String title;
	private String description;
	
	//KONSTRUKTOR
	public Excercise(Integer id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	//KONSTRUKTOR BEZPARAMETROWY
	public Excercise(){
	}
	
	//GETTERY SETTERY
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Excercise [id=" + id + ", title=" + title + ", description=" + description + "]\n";
	}

}
