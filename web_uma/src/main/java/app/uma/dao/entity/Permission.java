package app.uma.dao.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity 
@Cacheable(true)
public class Permission {

	@Id
	@GenericGenerator(name = "UUIDGENERATE", strategy = "uuid2")
	@GeneratedValue(generator = "UUIDGENERATE")
	@Column(length = 36)
	private String id;
    //权限名称
    private String name;

    //权限描述
    private String descritpion;

    //授权链接
    private String url;
    
    private String method;

    /**父节点id*/
    @Column(length = 36, nullable = false)
    private String fid;

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

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUid() {
        return fid;
    }

    public void setUid(String fid) {
        this.fid = fid;
    }

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


}
