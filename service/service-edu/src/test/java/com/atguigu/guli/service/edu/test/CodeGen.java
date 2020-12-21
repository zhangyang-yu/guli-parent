package com.atguigu.guli.service.edu.test;


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
import com.zhangyang.guli.service.base.result.R;
import com.zhangyang.guli.service.edu.feign.OssopenFeignClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.net.idn.Punycode;

import java.nio.file.OpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CodeGen {

   @Test
    public  void  test1(){
       String prefix = "";//用来拼接连接数据库的url地址数据库名称的、根据实际修改
       String moduleName = "edu";//用来拼接包名，当前模块名
       // 1、创建代码生成器
       AutoGenerator mpg = new AutoGenerator();

       // 2、全局配置
       GlobalConfig gc = new GlobalConfig();
       String projectPath = System.getProperty("user.dir");//工作所在的项目路径，可以使用绝对路径
       gc.setOutputDir(projectPath + "/src/main/java");//设置生成的代码存储的路径
       gc.setAuthor("zhangyang");
       gc.setOpen(false); //生成后是否打开资源管理器
//        gc.setFileOverride(true); //重新生成时文件是否覆盖
       gc.setServiceName("%sService");//默认Service接口会使用I开头，去掉首字母I
       gc.setIdType(IdType.ASSIGN_ID); //主键策略 使用雪花算法
       //设置mysql中的data/datatime 对应java中的类型
       gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型使用java.util.Date
        //给自动生成的类加上开发文档的注解，这样这些类就可以再开发文档上使用
       gc.setSwagger2(true);//开启Swagger2模式:自动生成swagger注解
       mpg.setGlobalConfig(gc);

       // 3、数据源配置
       DataSourceConfig dsc = new DataSourceConfig();
       dsc.setUrl("jdbc:mysql://localhost:3306/" + prefix + "guli_" + moduleName + "?serverTimezone=GMT%2B8");
       dsc.setDriverName("com.mysql.cj.jdbc.Driver");
       dsc.setUsername("root");
       dsc.setPassword("root");
       dsc.setDbType(DbType.MYSQL);//数据库类型
       mpg.setDataSource(dsc);

       // 4、包配置  设置生成的bean存储在哪些文件夹下
       PackageConfig pc = new PackageConfig();
       pc.setModuleName(moduleName); //模块名
       pc.setParent("com.zhangyang.guli.service");
       pc.setController("controller");
       pc.setEntity("entity");
       pc.setService("service");
       pc.setMapper("mapper");
       mpg.setPackageInfo(pc);

       // 5、策略配置
       StrategyConfig strategy = new StrategyConfig();
       strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
       //就是数据库字段上-之前的字母在生成的javabean中都不要
       strategy.setTablePrefix(moduleName + "_");//设置表前缀不生成

       strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
       strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作    启用lombok ，支持bean可以进行链式存储

       strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
       strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀

       //自动填充
       TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT); //设置自动填充的字段，就是给生成bean的字段加上注解(设置自动填充的注解)
       TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
       ArrayList<TableFill> tableFills = new ArrayList<>();
       tableFills.add(gmtCreate);
       tableFills.add(gmtModified);
       strategy.setTableFillList(tableFills);

       strategy.setRestControllerStyle(true); //restful api风格控制器
       strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
       //设置BaseEntity
       strategy.setSuperEntityClass("com.zhangyang.guli.service.base.model.BaseEntity");//这个类是用来存储每个entrty都存在的字段
       // 填写BaseEntity中的公共字段
       strategy.setSuperEntityColumns("id", "gmt_create", "gmt_modified");//这个是设置每张表都要存在的公共字段
       mpg.setStrategy(strategy);

       // 6、执行
       mpg.execute();
  }
  @Test
   public  void   calTest()
  {
     Calendar calendar=Calendar.getInstance();
     calendar.add(Calendar.MONTH,-12);
     SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
     System.out.println(simpleDateFormat.format(calendar.getTime()));
  }


}
