package com.example.market.service.role;

import com.example.market.model.auth.Role;
import com.example.market.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
