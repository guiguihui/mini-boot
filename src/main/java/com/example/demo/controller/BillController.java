package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.controller.dto.BillDTO;
import com.example.demo.controller.utils.R;
import com.example.demo.domain.Bill;
import com.example.demo.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private IBillService iBillService;

    /**
     * 新建账单信息
     */
    @PostMapping
    public R save(@RequestBody Bill bill){
        boolean flag =  iBillService.save(bill);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    /**
     * 查询全部账单信息
     */
    @GetMapping
    public R getAll(){
        return new R(true,iBillService.list());
    }

    /**
     * 预定信息更新
     */
    @PutMapping
    public R update(@RequestBody Bill bill)throws IOException {
        boolean flag = iBillService.updateById(bill);
        return new R(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    /**
     * 按Id删除预定
     */
    @DeleteMapping("{BillId}")
    public R delete(@PathVariable String BillId){
        return new R(iBillService.removeById(BillId));
    }

    /**
     * 按Id查询预定
     */
    @GetMapping("{BillId}")
    public R getById(@PathVariable String BillId){
        return new R(true,iBillService.getById(BillId));
    }

    @GetMapping("/car/{CarId}")
    public List<Bill> getByCarId(@PathVariable String CarId){
        String carId = CarId;

        if(carId==null){
            return null;
        }
        return iBillService.getByCarId(carId);
    }

    @GetMapping("/search/billCount")
    public List<BillDTO> search(@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        if (start==null || end==null) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            Date d = c.getTime();
            String day = format.format(d);
            end = day;
            c.add(Calendar.DATE, - 15);
            d = c.getTime();
            day = format.format(d);
            start = day;
        }

        List<BillDTO> billDTOList = new ArrayList<>();
        for(int i=Integer.parseInt(start);i<Integer.parseInt(end)+1;i++){
            QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
            queryWrapper.ge("Bill_id",String.valueOf(i));
            queryWrapper.le("Bill_id",String.valueOf(i+1));
            BillDTO billDTO = new BillDTO();
            billDTO.setDate(String.valueOf(i));
            billDTO.setBillCount(iBillService.count(queryWrapper));

            QueryWrapper<Bill> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.select("IFNULL(sum(Bill_fee),0) as totalBillSum")
                    .ge("Bill_id",String.valueOf(i))
                    .le("Bill_id",String.valueOf(i+1));

            Map<String, Object> map = iBillService.getMap(queryWrapper2);
            double sum = (double) map.get("totalBillSum");
            billDTO.setBillSum((float) sum);

            billDTOList.add(billDTO);

        }
        return billDTOList;
    }
}
