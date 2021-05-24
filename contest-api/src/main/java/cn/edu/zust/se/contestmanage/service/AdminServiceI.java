package cn.edu.zust.se.contestmanage.service;

import cn.edu.zust.se.contestmanage.dto.AdminDto;

/**
 * @author zy 2021/5/24
 */
public interface AdminServiceI {
    public AdminDto updateAdminPassword(int adminId, String password);
}
