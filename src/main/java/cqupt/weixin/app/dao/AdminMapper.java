package cqupt.weixin.app.dao;

import cqupt.weixin.app.entity.Admin;
import cqupt.weixin.app.entity.Teacher;

public interface AdminMapper {
    Admin login(Admin admin);

    void addTeacher(Teacher teacher);
}
