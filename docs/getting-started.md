# 快速开始

在此之前我们假设您已经:
- 拥有 Java 开发环境以及相应 IDE
- 熟悉 Spring Boot
- 熟悉 Maven

# 初始化工程

创建一个空的 Spring Boot 工程
> 可以使用 [Spring Initializr](https://start.spring.io/) 快速初始化一个 Spring Boot 工程，后续会提供Archetype模板

为工程创建以下子模块:
- **xxx-api:** Spring Cloud 的客户端接口
- **xxx-service:** 业务逻辑代码
- **xxx-web:** Spring Cloud 服务导出模块
- **xxx-job:** 分布式任务模块（非必须）
- **xxx-consumer:** 队列消费模块（非必须）

# 添加依赖

给系统引入 rhea 依赖管理:
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.rhea</groupId>
            <artifactId>rhea-dependencies</artifactId>
            <version>0.7.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

给xxx-api模块添加依赖：
```xml
<dependencies>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-kernel-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
</dependencies>
```

给xxx-service模块添加依赖：
```xml
<dependencies>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-kernel-service</artifactId>
    </dependency>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-starter-sharding</artifactId>
    </dependency>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-starter-framework</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.45</version>
    </dependency>
</dependencies>
```

给xxx-web模块添加依赖：
```xml
<dependencies>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>user-service</artifactId>
        <version>0.1.0</version>
    </dependency>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-kernel-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-starter-springcloud</artifactId>
    </dependency>
</dependencies>
```

给xxx-job模块添加依赖：
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-starter-framework</artifactId>
    </dependency>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-starter-elasticjob</artifactId>
    </dependency>
</dependencies>
```

给xxx-consumer模块添加依赖：
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-starter-framework</artifactId>
    </dependency>
    <dependency>
        <groupId>com.rhea</groupId>
        <artifactId>rhea-starter-mq</artifactId>
    </dependency>
</dependencies>
```

# 配置

> 为了给 Cat 、 Apollo 标识当前项目，需要在resources\META-INF目录下创建app.properties
```properties
# 用于 Cat 识别项目名
app.id=xxx
# 用于 Apollo 识别项目名
app.name=xxx
```

> 在 application.yaml 配置文件中添加，如果需要覆盖框架提供的默认值在再次文件中配置（也可配置在 Apollo 中）

```yaml
spring:
  main:
    allow-bean-definition-overriding: true
  application:
    name: xxx-web
  autoconfigure:
    exclude: org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration

server:
  port: 9090
```

> 在 application.properties 配置文件中添加数据源，没有分库分表使用 Spring Boot 数据源配置即可

```properties
sharding.jdbc.datasource.names=ds

sharding.jdbc.datasource.ds.type=com.zaxxer.hikari.HikariDataSource
sharding.jdbc.datasource.ds.driver-class-name=com.mysql.jdbc.Driver
sharding.jdbc.datasource.ds.jdbcUrl=jdbc:mysql://10.200.111.135:3306/rhea-examples?characterEncoding=utf8
sharding.jdbc.datasource.ds.username=xxxx
sharding.jdbc.datasource.ds.password=xxxx

sharding.jdbc.config.sharding.tables.order.actual-data-nodes=ds.order_$->{0..1}
sharding.jdbc.config.sharding.tables.order.table-strategy.inline.sharding-column=order_id
sharding.jdbc.config.sharding.tables.order.table-strategy.inline.algorithm-expression=order_$->{order_id % 2}
sharding.jdbc.config.sharding.tables.order.key-generator-column-name=order_id
```

# 编码

用工具生成 Model、 Example、 Mapper 类
```java
@Data
@ToString
@Table(name = "order")
public class Order extends BaseEntity {
    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;
}

public class OrderExample implements Serializable {
    // 自动生成的代码
}

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
```

编写 Service 类
```java
public interface OrderService extends BaseService<Order, OrderExample> {
}

@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderExample> implements OrderService {
}
```

编写 Controller 类
```java
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/batch_add", method = RequestMethod.POST)
    public String batchAdd(Long id, int count) {
        List<Order> orderList = Lists.newArrayListWithCapacity(count);
        for (int i = 0; i < count; i++) {
            Order order = new Order();
            order.setOrderId(id + i);
            order.setOrderName("order_" + id + i);
            orderList.add(order);
        }
        orderService.insert(orderList);
        return "success";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insert(@RequestBody Order order) {
        // 订单实体校验
        ComplexResult result = FluentValidator.checkAll().failOver()
            .on(order.getOrderName(), new NotNullValidator("orderName"))
            .doValidate()
            .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            throw new rheaException("");
        }
        return orderService.insertSelective(order);
    }
}
```

# 开始使用

添加 Spring Boot 入口类

```java
@EnableFramework
@EnableDiscoveryClient
@EnableFeignClients(value = "com.rhea")
@MapperScan(value = "com.rhea.*.mapper", factoryBean= BatchFactoryBean.class)
@ComponentScan(value = "com.rhea")
public class OrderWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderWebApplication.class);
    }

}
```

> 配置jvm options：-Denv=dev|pre|uat|pro 使用rhea预设的 Spring Cloud 配置

> 完整的代码示例请移步: [rhea-example](https://github.com/bulong0721/rhea-examples.git)

