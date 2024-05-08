package de.dhbw.softwareengineering.contactddd.domain.values;

import java.util.Objects;
import java.util.UUID;

public record ContactId(String id) {

    public ContactId() {
        this(UUID.randomUUID().toString());
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
