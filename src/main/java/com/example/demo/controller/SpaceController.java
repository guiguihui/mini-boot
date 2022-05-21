package com.example.demo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.utils.R;
import com.example.demo.domain.Parking;
import com.example.demo.domain.Space;
import com.example.demo.service.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    @Autowired
    private ISpaceService iSpaceService;

    /**
     分页
     */
    @GetMapping("/page")
    public IPage<Space> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String parkingId,
                                @RequestParam(defaultValue = "") String spaceState,
                                @RequestParam(defaultValue = "") String carId) {
        IPage<Space> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Space> queryWrapper = new QueryWrapper<>();
        if (!"".equals(parkingId)) {
            queryWrapper.like("Parking_id", parkingId);
        }
        if (!"".equals(carId)) {
            queryWrapper.like("Car_id", carId);
        }
        if (!"".equals(spaceState)) {
            queryWrapper.like("Space_state", spaceState);
        }
        return iSpaceService.page(page, queryWrapper);
    }

    /**
     * 批量删除
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return iSpaceService.removeByIds(ids);
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Space> list = iSpaceService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("SpaceId", "SpaceId");
        writer.addHeaderAlias("ParkingId", "ParkingId");
        writer.addHeaderAlias("SpaceState", "SpaceState");
        writer.addHeaderAlias("CarId", "CarId");
        writer.addHeaderAlias("IsDeleted", "IsDeleted");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("SpaceInformation", "UTF-8");
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
        List<Space> list = reader.readAll(Space.class);
        iSpaceService.saveBatch(list);
        return true;
    }

    /**
     * 新建或更新停车位信息
     */
    @PostMapping
    public R save(@RequestBody Space space){
        boolean flag = iSpaceService.saveOrUpdate(space);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    /**
     * 按Id删除停车位
     */
    @DeleteMapping("{SpaceId}")
    public R delete(@PathVariable Integer SpaceId){
        return new R(iSpaceService.removeById(SpaceId));
    }

    /**
     * 查询停车位信息
     */
    @GetMapping("/parkingId")
    public List<Space> getAll(@RequestParam(defaultValue = "") String parkingId){
        QueryWrapper<Space> queryWrapper = new QueryWrapper<>();
        if (!"".equals(parkingId)) {
            queryWrapper.eq("Parking_id", parkingId);
        }
        return iSpaceService.list(queryWrapper);
    }


    @GetMapping("/available")
    public int AvailableSpaceCount() throws UnsupportedEncodingException {
        String name = "空闲";
        name = new String (name.getBytes("gbk"), "utf-8");
        QueryWrapper<Space> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Space_state",name);

        return iSpaceService.count(queryWrapper);
    }


    /**
     * 停车位信息更新
     * @param space
     * @return
     */
    @PutMapping
    public R update(@RequestBody Space space)throws IOException {
        boolean flag = iSpaceService.updateById(space);
        return new R(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    /**
     * 按Id查询停车位
     */
    @GetMapping(value = "{SpaceId}")
    public R getById(@PathVariable Integer SpaceId){
        return new R(true,iSpaceService.getById(SpaceId));
    }
}
