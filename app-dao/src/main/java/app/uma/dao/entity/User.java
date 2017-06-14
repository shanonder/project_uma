package app.uma.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GenericGenerator(name = "UUIDGENERATE", strategy = "uuid2")
	@GeneratedValue(generator = "UUIDGENERATE")
	@Column(length = 36)
	private String id;
	
	private int platId;
	
	private String platKey;
	
    private String name;
    
    private int antiAddiction;

//    private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAntiAddiction() {
		return antiAddiction;
	}

	public void setAntiaddiction(int antiAddiction) {
		this.antiAddiction = antiAddiction;
	}

	public int getPlatId() {
		return platId;
	}

	public void setPlatId(int platId) {
		this.platId = platId;
	}

	public String getPlatKey() {
		return platKey;
	}

	public void setPlatKey(String platKey) {
		this.platKey = platKey;
	}

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}


}