package app.uma.dao.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity 
@Cacheable(true)
public class UserUma {
	@Id
	@GenericGenerator(name = "UUIDGENERATE", strategy = "uuid2")
	@GeneratedValue(generator = "UUIDGENERATE")
	@Column(length = 36)
	private String id;

	@Column(length = 36 , unique = true , nullable = false)
	private String username;
	
	@Column(length = 36)
    private String name;
	
	@Column(length = 50, unique = true , nullable = false)
    private String password;

	@Column(length = 50)
    private String email;

    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}