package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.INPUT)  //对应数据库中的自增  默认为ID_WORKER, 全局唯一,雪花算法
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version  //乐观锁version注解
    private Integer version;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
