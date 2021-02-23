package com.agilewriters.workshop.{names};

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "{names}")
public class {Name} {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    {declarations:`private ${opts.Type} ${field};\n`}
    {accessors:`public ${opts.Type} get${Field}() { return this.${field};}\npublic void set${Field}(${opts.Type} ${field}) { this.${field} = ${field}; }\n`}

    @Override
    public String toString() {
        String s = "{tostring:`${field}="+${field}+",`}";
        return "{Name} [" +s+ "]";
    }

}
