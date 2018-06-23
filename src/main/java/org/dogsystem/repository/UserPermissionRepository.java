package org.dogsystem.repository;

import org.dogsystem.entity.UserPermissionEntity;
import org.dogsystem.entity.UserPermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity,UserPermissionKey> {

}
