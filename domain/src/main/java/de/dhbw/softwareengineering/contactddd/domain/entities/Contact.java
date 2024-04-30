package de.dhbw.softwareengineering.contactddd.domain.entities;

import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "contacts")
public class Contact {
  @Id private ContactId id;

  @Field("name")
  private String name;

  @Field("email")
  private String email;

  @Field("phone_number")
  private String phoneNumber;

  public Contact() {
    // Default-Konstruktor für Spring Data
  }

  public Contact(String name, String email, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  // Standard-Getter und Setter
  public ContactId getId() {
    return id;
  }

  public void setId(ContactId id) {
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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
