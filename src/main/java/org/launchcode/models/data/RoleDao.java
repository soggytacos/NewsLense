package org.launchcode.models.data;

import org.launchcode.models.Role;
import org.springframework.data.repository.CrudRepository; //specifies the methods that allow database interaction.
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional //all methods should be wrapped with a database transaction.
public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}