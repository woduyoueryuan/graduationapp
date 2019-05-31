package cqupt.weixin.app.service;

import cqupt.weixin.app.entity.Admin;
import cqupt.weixin.app.entity.Teacher;

public interface AdminService {
    Admin login(Admin admin);

    void addTeacher(Teacher teacher);
}
