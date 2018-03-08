package org.dogsystem.repository;

import org.dogsystem.permission.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity,Long> {

}
