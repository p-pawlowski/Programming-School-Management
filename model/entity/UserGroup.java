package entity;

public class UserGroup {
	
	//POLE KLASY
	private Integer id;
	private String name;
	
	//KONSTRUKTOR
	public UserGroup(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//KONSTRUKTOR BEZPARAMETROWY
	public UserGroup(){
	}
	
	//GETTERY SETTERY
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", name=" + name + "]+/n";
	}
	

}
