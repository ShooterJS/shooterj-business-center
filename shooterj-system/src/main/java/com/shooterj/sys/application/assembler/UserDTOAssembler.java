package com.shooterj.sys.application.assembler;


import com.shooterj.common.domain.StatusEnum;
import com.shooterj.sys.application.command.UserCommand;
import com.shooterj.sys.application.dto.UserDTO;
import com.shooterj.sys.domain.model.role.RoleId;
import com.shooterj.sys.domain.model.user.User;
import com.shooterj.sys.domain.model.user.UserId;
import com.shooterj.sys.domain.model.user.UserName;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户Assembler
 **/
public class UserDTOAssembler {

    public static User toUser(final UserCommand userCommand) {
        List<String> roleIds = userCommand.getRoleIdList();
        List<RoleId> roleIdList = null;
        if(roleIds != null && !roleIds.isEmpty()) {
            roleIdList = new ArrayList<>();
            for(String roleId : roleIds) {
                roleIdList.add(new RoleId(roleId));
            }
        }
        UserName userName = null;
        if(userCommand.getUserName() != null) {
            userName = new UserName(userCommand.getUserName());
        }
        return new User(new UserId(userCommand.getId()),userName, StatusEnum.ENABLE,null,null, roleIdList);
    }

    public static UserDTO fromUser(final User user) {
        List<String> roleIdList = new ArrayList<>();
        if(user.getRoleIds() != null) {
            user.getRoleIds().forEach(roleId -> {
                roleIdList.add(roleId.getId());
            });
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId() == null?null:user.getUserId().getId());
        userDTO.setUserName(user.getUserName() == null?null:user.getUserName().getName());
        userDTO.setEmail(user.getAccount().getEmail() == null?null:user.getAccount().getEmail().getEmail());
        userDTO.setMobile(user.getAccount().getMobile() == null?null:user.getAccount().getMobile().getMobile());
        userDTO.setRoleIdList(roleIdList);
        userDTO.setStatus(user.getStatus() == null?null:user.getStatus().getName());
        return userDTO;
    }
}
