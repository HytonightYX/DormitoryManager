package me.hsy.test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class Person {
        private final StringProperty firstName;
        private final StringProperty lastName;
        private final StringProperty email;

        public Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() { return firstName.get(); }
        public void setFirstName(String fName) { firstName.set(fName); }
        public StringProperty firstNameProperty() { return firstName; }
        public String getLastName() { return lastName.get(); }
        public void setLastName(String lName) { lastName.set(lName); }
        public StringProperty lastNameProperty() { return lastName; }
        public String getEmail() { return email.get(); }
        public void setEmail(String inMail) { email.set(inMail); }
        public StringProperty emailProperty() { return email; }  // if this method is commented out then the tableview will not refresh when the email is set.
    }