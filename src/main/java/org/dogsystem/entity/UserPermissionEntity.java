package org.dogsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dogsystem.key.UserPermissionKey;
import org.dogsystem.utils.BaseEntity;

@Entity
@Table(name = "tb_user_permission")
public class UserPermissionEntity extends BaseEntity<UserPermissionKey> {
	private static final long serialVersionUID = 3304600547455176019L;

}
