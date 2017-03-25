package com.scrum.datapersistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WORKITEM")
public class WorkItemEntity {

    @Id
    @Column(nullable = false, name="NAME")
    private String name;

    @Column(nullable = false, name="DESCRIPTION")
    private String description;
    
    @Column(nullable = false, name="STATUS")
    private String status;

    WorkItemEntity() {
    }

    public WorkItemEntity(String name,
                    String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkItemEntity [name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}



}
