package com.ojdgaf.tasks.digis.entities;

import java.util.Date;
import java.util.Objects;
import java.text.SimpleDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 16)
    private String login;

    @Column(name = "full_name")
    @NotBlank
    @Size(min = 2, max = 100)
    private String fullName;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    @Past
    private Date dateOfBirth;

    @Column
    @NotBlank
    @Size(min = 2, max = 100)
    // This may also be a boolean attribute but who knows how many genders we have nowadays...
    private String gender;

    public User() {
    }

    public User(String login, String fullName, Date dateOfBirth, String gender) {
        this.login = login;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public User(Integer id, String login, String fullName, Date dateOfBirth, String gender) {
        this.id = id;
        this.login = login;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        return 4 * (getLogin() == null ? super.hashCode() : getLogin().hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof User)) return false;

        return Objects.equals(getLogin(), ((User) o).getLogin());
    }

    @Override
    public String toString() {
        return getId() + " -> "
                + getLogin() + " (" + getFullName() + "), "
                + new SimpleDateFormat("dd-MM-yyyy").format(getDateOfBirth()) + ", "
                + getGender();
    }
}
