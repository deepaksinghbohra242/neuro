package com.neuromed.auth.mapper;

import com.neuromed.auth.entity.User;
import com.neuromed.auth.entity.UserModel;

public class UserMapper {

    public static UserModel toModel(User user) {
        if (user == null) return null;
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setEmail(user.getEmail());
        model.setPassword(null);
        model.setRoles(user.getRoles());
        model.setAge(user.getAge());
        return model;
    }

    public static User toEntity(UserModel model) {
        if (model == null) return null;
        User user = new User();
        user.setId(model.getId());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());
        user.setRoles(model.getRoles());
        user.setAge(model.getAge());
        return user;
    }
}
