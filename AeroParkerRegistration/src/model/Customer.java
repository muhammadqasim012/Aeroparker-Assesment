package model;

public class Customer {

	private String email;
	private String title;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postCode;
	private String telephone;

	public Customer(String email, String title, String firstName, String lastName, String addressLine1,
			String addressLine2, String city, String postCode, String telephone) {
		super();
		this.email = email;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.postCode = postCode;
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public String getTitle() {
		return title;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getTelephone() {
		return telephone;
	}

}
