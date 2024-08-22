package P1;

class Guest {
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public Guest(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Guest other = (Guest) obj;
        if (this.lastName.equals(other.lastName) && this.firstName.equals(other.firstName) &&
                this.email.equals(other.email) && this.phoneNumber.equals(other.phoneNumber)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + ", Email: " +
                this.getEmail() + ", Phone number: " + this.getPhoneNumber();
    }

    public String fullName() {
        return this.firstName + " " + this.lastName;
    }

    public void notifyGuest(int orderNo) {
        if (orderNo < 0) {
            System.out.println("["+ this.firstName + " " + this.lastName +
                    "] Congratulations! Your attendance at the event has been confirmed. We look forward to welcoming you!");
        } else {
            System.out.println("["+ this.firstName + " " + this.lastName
                    + "] You have successfully been added to the waiting list and received order number " +
                    orderNo + ". We will notify you promptly if a spot becomes available.");
        }
    }
}