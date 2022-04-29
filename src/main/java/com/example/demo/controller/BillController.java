package com.example.demo.controller;

import com.example.demo.controller.utils.R;
import com.example.demo.domain.Bill;
import com.example.demo.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private IBillService iBillService;

    /**
     * 新建账单信息
     * @param bill
     * @return Boolean
     */
    @PostMapping
    public R save(@RequestBody Bill bill){
        boolean flag =  iBillService.save(bill);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    /**
     * 查询全部账单信息
     * @return List
     */
    @GetMapping
    public R getAll(){
        return new R(true,iBillService.list());
    }

    /**
     * 预定信息更新
     * @param bill
     * @return Boolean
     */
    @PutMapping
    public R update(@RequestBody Bill bill)throws IOException {
        boolean flag = iBillService.updateById(bill);
        return new R(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    /**
     * 按Id删除预定
     * @param BillId
     * @return Boolean
     */
    @DeleteMapping("{BillId}")
    public R delete(@PathVariable String BillId){
        return new R(iBillService.removeById(BillId));
    }

    /**
     * 按Id查询预定
     * @param BillId
     * @return Reserve
     */
    @GetMapping(value = "{BillId}")
    public R getById(@PathVariable String BillId){
        return new R(true,iBillService.getById(BillId));
    }
}
