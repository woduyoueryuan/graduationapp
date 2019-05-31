package cqupt.weixin.app.service.impl;

import cqupt.weixin.app.dao.AdminMapper;
import cqupt.weixin.app.entity.Admin;
import cqupt.weixin.app.entity.Teacher;
import cqupt.weixin.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminserviceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        adminMapper.addTeacher(teacher);
    }
}
