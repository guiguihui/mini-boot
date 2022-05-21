package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.utils.R;
import com.example.demo.domain.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     分页
     */
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String userName,
                                @RequestParam(defaultValue = "") String carId,
                                @RequestParam(defaultValue = "") String userPhone) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(userName)) {
            queryWrapper.like("User_name", userName);
        }
        if (!"".equals(carId)) {
            queryWrapper.like("Car_id", carId);
        }
        if (!"".equals(userPhone)) {
            queryWrapper.like("User_phone", userPhone);
        }
        return iUserService.page(page, queryWrapper);
    }

    /**
     * 批量删除
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return iUserService.removeByIds(ids);
    }

    /**
     * 查询全部用户
     */
    @GetMapping
    public R getAll(){
        return new R(true,iUserService.list());
    }

    /**
     * 按Id删除用户
     */
    @DeleteMapping("{UserId}")
    public R delete(@PathVariable Integer UserId){
        return new R(iUserService.removeById(UserId));
    }

    /**
     * 新建用户
     */
    @PostMapping
    public R save(@RequestBody User user){
        boolean flag = iUserService.saveOrUpdate(user);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = iUserService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("UserId", "UserId");
        writer.addHeaderAlias("UserName", "UserName");
        writer.addHeaderAlias("UserPassword", "UserPassword");
        writer.addHeaderAlias("CarId", "CarId");
        writer.addHeaderAlias("UserGender", "UserGender");
        writer.addHeaderAlias("UserPhone", "UserPhone");
        writer.addHeaderAlias("IsDeleted", "IsDeleted");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("userInformation", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
     * excel 导入
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//         方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<User> list = reader.readAll(User.class);
        iUserService.saveBatch(list);
        return true;
    }


    /**
     * 管理员登录
     */
    @PostMapping("/adminLogin")
    public boolean adminLogin(@RequestBody UserDTO userDTO) {

        /*先拿到前台传过来的用户ID和密码*/
        Integer userId = userDTO.getUserId();
        String userPassword = userDTO.getUserPassword();

        /*根据ID查询这个用户是不是管理员 并将结果赋值给userDTO*/
        User user = iUserService.getById(userId);
        Integer isAdmin = 0;
        if( user!= null){
            userDTO.setIsAdmin(user.getIsAdmin());
            isAdmin = userDTO.getIsAdmin();
        }

        /*判断传过来的字符段是否为空，如果是空的话直接返回false*/
        if(StrUtil.isBlank(userPassword) || userId==0 || isAdmin==0){
            return false;
        }else{
            return iUserService.adminLogin(userDTO);
        }

    }

    @GetMapping("/login")
    public boolean login(@RequestParam Integer id, @RequestParam String password) {
        Integer userId = id;
        String userPassword = password;
        if(userId==null || userPassword==null){
            return false;
        }
        return iUserService.login(userId,userPassword);
    }

    @GetMapping("/login2")
    public User login2(@RequestParam Integer id, @RequestParam String password) {
        Integer userId = id;
        String userPassword = password;
        if(userId==null || userPassword==null){
            return null;
        }
        return iUserService.login2(userId,userPassword);
    }

    @GetMapping("/count")
    public int UserCounter(){
        Integer count = iUserService.count();
        return  count;
    }

    /**
     * 用户信息更新
     */
    @PutMapping
    public R update(@RequestBody User user) throws IOException  {
        boolean flag = iUserService.updateById(user);
        return new R(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    @GetMapping(value = "{UserId}")
    public R getById(@PathVariable Integer UserId){
        return new R(true,iUserService.getById(UserId));
    }
}
