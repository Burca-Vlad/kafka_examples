package com.linkit.datarest.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "TST_MAIN")
public class MainEntity {

    @Id
    @GenericGenerator(name="main_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "main_seq"),
                    @Parameter(name = "initial_value", value = "0"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="main_seq")
    @Column(name = "ID", nullable = false, unique = true)
    private Long resourceId;

    @Column(name = "MAIN_NAME")
    private String name;

    @Column(name = "MAIN_DESCRIPTION")
    private String description;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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

    @Override
    public String toString() {
        return "MainEntity{" +
                "resourceId=" + resourceId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
