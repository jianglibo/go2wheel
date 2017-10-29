package com.go2wheel.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accessory", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Accessory extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String cat;

    @NotNull
    private String name;
    
    private String description;
    
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<MtModel> mtModels;

    public void setName(String name) {
		this.name = name;
	}

	public Accessory() {
    };

    public Accessory(String name) {
        setName(name);
    }
    
    public String getName() {
        return name;
    }

    
    @Override
    public String toString() {
    	return getName();
    }

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public Set<MtModel> getMtModels() {
		return mtModels;
	}

	public void setMtModels(Set<MtModel> mtModels) {
		this.mtModels = mtModels;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
