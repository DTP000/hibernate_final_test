package tk.dtp000.sprhibnmvctest.kthpfall.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Customers")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="customer_code")
	private String customerCode;
	
	@ManyToOne
    @JoinColumn(name="customerType_id")
	private CustomerType customerType;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="birthDay")
	private String birthDay;
	
	@Column(name="id_card")
	private String indentification_card;
	
	@NotNull(message = "is required")
    @Email(message = "Invalid email! Please enter valid email")
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
}
