package com.eventbooking.service.mapper;

import com.eventbooking.domain.User;
import com.eventbooking.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
