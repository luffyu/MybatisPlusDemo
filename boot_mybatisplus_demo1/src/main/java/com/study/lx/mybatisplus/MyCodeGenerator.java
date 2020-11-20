package com.study.lx.mybatisplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


/**
 * @author rockyu
 * @date 2020/11/6 14:18
 */

public class MyCodeGenerator {

    public static void main(String[] args) {
        create("user_info");
    }

    public static void create(String... tables) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/boot_mybatisplus_demo1/src/main/java");
        gc.setAuthor("rockyu");
        //是否打开输出文件
        gc.setOpen(false);
        //是否覆盖已经存在的文件
        gc.setFileOverride(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3308/study_demo1?useUnicode=true;characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.study.lx.mybatisplus");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 公共父类
        //strategy.setSuperEntityClass(baseEntityClass);
        //strategy.setSuperServiceImplClass(baseServiceImpl);
        //strategy.setSuperServiceClass(baseService);
        //对于controller 是否开启 @RestController注解
        strategy.setRestControllerStyle(true);

        //是否开启 lombok 开启之后 文件中没有get set方法
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tables);
        //写于父类中的公共字段
        //strategy.setSuperEntityColumns(keyId);
        //乐观锁字段
        strategy.setVersionFieldName("version");
        strategy.setLogicDeleteFieldName("del_flag");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        mpg.setStrategy(strategy);


       //设置模版引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
