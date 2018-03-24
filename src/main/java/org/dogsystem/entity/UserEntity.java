package org.dogsystem.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.dogsystem.permission.PermissionEntity;
import org.dogsystem.utils.BaseEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_user")
@AttributeOverride(name = "id", column = @Column(name = "cod_user"))
public class UserEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "O campo nome não pode ser nulo")
	@NotEmpty(message = "O campo nome não pode ser vazio")
	@Size(min = 3, max = 120,message = "O nome precisa ter de 3 a 120 caracteres")
	@Column(name = "name",length = 120 ,nullable = false)
	private String name;
	
	@CPF(message = "O campo CPF está inválido")
	@NotNull(message = "O campo CPF não pode ser nulo")
	@NotEmpty(message = "O campo CPF não pode ser vazio")
	@Column(name = "cpf", length = 14, unique = true, nullable = false)
	private String cpf;
	
	@NotNull(message = "O campo Telefone não pode ser nulo")
	@NotEmpty(message = "O campo Telefone não pode ser vazio")
	@Column(name= "phone",length = 13, unique = true, nullable = false)
	private String phone;

	@Email(message = "O campo Email está inválido")
	@NotNull(message = "O campo Email não pode ser nulo")
	@NotEmpty(message = "O campo Email não pode ser vazio")
	@Column(name = "email", length = 120, nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "O campo senha não pode ser nulo")
	@NotEmpty(message = "O campo senha não pode ser nulo")
	@Size(min = 6, max = 100, message = "A senha deve ter no mínimo 6 caracteres")
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "number", length = 10, nullable = false)
	private Long number;
	
	@Column(name = "complement", length = 200)
	private String complement;
	
	@ManyToOne
	@JoinColumn(name = "cod_address", nullable = false)
	private AddressEntity address;
	
	@ManyToMany(fetch =  FetchType.EAGER)
	@JoinTable(
			name = "tb_user_permission" , 
			joinColumns = @JoinColumn(name = "cod_user"),
			inverseJoinColumns = @JoinColumn(name = "cod_per")
	)
	private List<PermissionEntity> permissions;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public List<PermissionEntity> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionEntity> permissions) {
		this.permissions = permissions;
	}
}
