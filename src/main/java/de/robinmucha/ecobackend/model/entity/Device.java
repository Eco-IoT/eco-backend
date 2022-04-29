package de.robinmucha.ecobackend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "device")
public class Device {

    /*
     *  COLUMNS
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = false)
    @JsonBackReference(value = "user-devices")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type", nullable = false)
    @JsonBackReference(value = "deviceType-devices")
    private DeviceType deviceType;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "password", nullable = false, length = 256)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "active", nullable = false, columnDefinition = "bit default true")
    private Boolean active = false;

    @Column(name = "created", nullable = false, updatable = false)
    private Timestamp created;

    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "device-events")
    private Set<Event> events = new LinkedHashSet<>();

    /*
     *  CONSTRUCTORS
     */
    public Device() {
    }

    public Device(User owner, DeviceType deviceType, String name, String password, String description) {
        this.owner = owner;
        this.deviceType = deviceType;
        this.name = name;
        this.password = password;
        this.description = description;
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

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}