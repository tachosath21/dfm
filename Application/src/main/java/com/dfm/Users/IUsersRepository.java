package com.dfm.Users;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dfm.Users.Entities.UserModel;
import com.dfm.Users.Entities.UserRoleEntity;

public interface IUsersRepository extends JpaRepository<UserModel, Integer>
{
    public Optional<UserModel> findByEmail(String email);

    @Query("""
        select      Users
        from        UserModel Users
        where       Users.user_role = :user_role""")
    public Optional<List<UserModel>> findByUserRole(@Param("user_role") UserRoleEntity user_role);
}
