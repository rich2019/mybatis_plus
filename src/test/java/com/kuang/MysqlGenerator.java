package com.kuang;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class MysqlGenerator {
    public static void main(String[] args) {
        //代码自动生成器对象
        AutoGenerator generator = new AutoGenerator();

        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String path = System.getProperty("user.dir");
        gc.setOutputDir(path+"/src/main/java");
        gc.setAuthor("cdd");
        gc.setOpen(true); //完成后打开文件夹
        gc.setFileOverride(false);  //是否覆盖
//        gc.setServiceName("%sService");  //去service的I前缀
        gc.setIdType(IdType.INPUT);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        generator.setGlobalConfig(gc);

        //2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:33060/mybatis_plus?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setDbType(DbType.MYSQL);
        generator.setDataSource(dsc);


        //3.包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("mybatis_plus");
        pc.setParent("com.kuang");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        generator.setPackageInfo(pc);


        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user");  //设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);  //自动lombok

        strategy.setLogicDeleteFieldName("deleted");  //逻辑删除字段

        //自动填充
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);

        //乐观锁
        strategy.setVersionFieldName("version");


        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2  url采用下划线风格

        generator.setStrategy(strategy);




        generator.execute();
    }
}
