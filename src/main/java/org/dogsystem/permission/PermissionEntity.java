package org.dogsystem.permission;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.dogsystem.utils.BaseEntity;

@Entity
@Table(name = "tb_permission")
@AttributeOverride(name = "id", column = @Column(name = "cod_per"))
public class PermissionEntity extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "role", length = 45, nullable = false, unique = true)
	private String role;
	
	public PermissionEntity() {
	}

	public PermissionEntity(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}	
