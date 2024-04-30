package de.dhbw.softwareengineering.contactddd.domain.values;

import java.util.Objects;
import java.util.UUID;

public class ContactId {

  private final String id;

  public ContactId() {
    this.id = UUID.randomUUID().toString();
  }

  public ContactId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ContactId contactId = (ContactId) o;
    return Objects.equals(id, contactId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return id;
  }
}
