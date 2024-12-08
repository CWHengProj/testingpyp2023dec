package vttp.ssf.assessment.eventmanagement.models;

import java.time.LocalDate;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {
    @NotBlank(message="This is a required field.")
    @Size(min=5, max=25, message="Your name can contain between 5 and 25 letters.")
    private String fullName;
    @DateTimeFormat(pattern="YYYY-MM-DD")
    @Past(message="can't be from the future, can you?")
    private LocalDate dob;
    @NotBlank(message="This is a required field.")
    @Email(message="Please enter a valid email address.")
    @Size(max=50, message="Your email address should not be longer than 50 characters.")
    private String email;
    @NotBlank(message = "This field must not be empty.")
    @Pattern( regexp = "(8|9)[0-9]{7}", message = "Phone number must start with 8 or 9 followed by 7 digits")
    private String phoneNumber;
    private String gender;
    @Range(min=1, max=3, message="You must get at least 1 ticket")
    @Min(value=1, message = "You must get at least 1 ticket")
    @Max(value=3, message = "You can get up to 3")
    private Integer numTickets;
    public User() {
    }
    
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
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
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Integer getNumTickets() {
        return numTickets;
    }
    public void setNumTickets(Integer numTickets) {
        this.numTickets = numTickets;
    }
    @Override
    public String toString() {
        return "User [fullName=" + fullName + ", dob=" + dob + ", email=" + email + ", phoneNumber=" + phoneNumber
                + ", gender=" + gender + ", numTickets=" + numTickets + "]";
    }
    
}
