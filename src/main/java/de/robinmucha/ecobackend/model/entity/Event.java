package de.robinmucha.ecobackend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event {

    /*
     *  COLUMNS
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator", nullable = false)
    @JsonBackReference(value = "device-events")
    private Device creator;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type", nullable = false)
    @JsonBackReference(value = "eventType-events")
    private EventType eventType;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "created", nullable = false, updatable = false)
    private Timestamp created;

    /*
     *  CONSTRUCTORS
     */
    public Event() {
    }

    public Event(Device creator, EventType eventType, String name, String description) {
        this.creator = creator;
        this.eventType = eventType;
        this.name = name;
        this.description = description;
    }

    /*
     *  METHODS
     */
    @PrePersist
    public void onCreate() {
        created = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Device getCreator() {
        return creator;
    }

    public void setCreator(Device creator) {
        this.creator = creator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}