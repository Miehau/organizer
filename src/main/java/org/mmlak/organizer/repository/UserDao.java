package org.mmlak.organizer.repository;

import org.mmlak.organizer.repository.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, String> {
}
