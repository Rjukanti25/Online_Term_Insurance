package com.example.insurance.entity;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonManagedReference;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
 
	//@NotBlank(message = "User Identification number cannot be blank")
 
	@Column
	@NotBlank(message = "Username cannot be blank")
    @Size(min = 1, max = 255, message = "Username must be between 1 and 255 characters")
	private String username;
 
	@Column
	@NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^[\\d]{10}$", message = "Password must be 10 digits") 
	private String password;
 
	@Column
	@NotBlank(message = "Email cannot be blank")
    @Email(message = "Enter a valid email")
	private String email;
 
	@Column
	@NotBlank(message = "First name cannot be blank")
    @Size(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
	private String firstName;
 
	@Column
	@NotBlank(message = "Last name cannot be blank")
    @Size(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
	private String lastName;
 
	@Column
	private LocalDate dateOfBirth;
 
	@Column
	@NotBlank(message = "Address cannot be blank")
	private String address;
 
	@Column
	@NotBlank(message = "City cannot be blank")
	private String city;
 
	@Column
	@NotBlank(message = "State cannot be blank")
	private String state;
 
	@Column
	@NotBlank(message = "Zip code cannot be blank")
    @Pattern(regexp = "^[\\d]{6}$", message = "Zip code must be 6 digits")
	private String zipCode;
 
	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Payment> payments;
	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Transaction> transactions;
	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Beneficiary> beneficiaries;
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	List<Policy> policies;
	public void addPolicy (Policy p) {
		if (policies == null) {
			policies = new ArrayList<>();
		}
		policies.add(p);
	}
	public void removeBeneficiary(Beneficiary b) {
		beneficiaries.remove(b);
	}
	public void removePolicy(Policy p) {
		policies.remove(p);
	}
}