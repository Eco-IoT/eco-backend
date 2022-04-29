package de.robinmucha.ecobackend.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "user_name_uindex", columnList = "name", unique = true)
}, uniqueConstraints = {
        @UniqueConstraint(name = "user_id_uindex", columnNames = {"id"})
})
public class User {

    /*
     *  COLUMNS
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "password", nullable = false, length = 256)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "active", nullable = false, columnDefinition = "bit default true")
    private Boolean active = true;

    @Column(name = "created", nullable = false, updatable = false)
    private Timestamp created;

    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-deviceTypes")
    private Set<DeviceType> deviceTypes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-eventTypes")
    private Set<EventType> eventTypes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-devices")
    private Set<Device> devices = new LinkedHashSet<>();

    /*
     *  CONSTRUCTORS
     */
    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /*
     *  METHODS
     */
    @PrePersist
    public void onCreate() {
        created = Timestamp.valueOf(LocalDateTime.now());
        setLastModified(Timestamp.valueOf(LocalDateTime.now()));
    }

    @PreUpdate
    public void onUpdate() {
        lastModified = Timestamp.valueOf(LocalDateTime.now());
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

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public Set<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(Set<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }

    public Set<DeviceType> getDeviceTypes() {
        return deviceTypes;
    }

    public void setDeviceTypes(Set<DeviceType> deviceTypes) {
        this.deviceTypes = deviceTypes;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    //@JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}