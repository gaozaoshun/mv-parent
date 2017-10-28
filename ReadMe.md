># 高可用的服务注册中心
>* 提供多个服务器
>* 提供多个服务注册中心，这里为mv-eureka-ha-server1、mv-eureka-ha-server2
>* 服务注册中心配置需要开启register-with-eureka自身注册、fetch-registry获取服务
>* 顺序启动后报错很正常，因为两server启动时需要相互注册，当一服务启动时，另一服务还没启动，所以此时会报http访问异常。
>* 服务只需注册到其中一个服务注册中心即可，无需配置多个
>* 宕掉注册中心A，注册中心B保存原有的所有服务。
>* 不过因收不到A服务的心跳包，所以此时注册中心B处于安全模式。

># 安全身份认证
>* 添加依赖spring-boot-starter-security
>* 修改配置：<br/>
        
        eureka:
            client:
                service-url:
                    defaultZone: http://admin@123456@127.0.0.1:10001/eureka/
        security:
            basic:
                enabled: true
        user:
            name: admin
            password: 123456
             
># 服务消费者Ribbon
>* Ribbon是个负载均衡的客户端
>* 对新建RestTemplate对象进行依赖注入@Bean、@LoadBalance
>* RestTemplate会自动对获取到的服务提供者进行负载均衡
>* 启动类依赖注入@EnableDiscoveryClient表示自动注册到服务注册中心
             
             
># 服务消费者Feign
>* 默认实现了LoadBalance
>* 启动类依赖注入@EnableDiscoveryClient、@EnableFeignClients           
>* 通过接口+注解的方式注入服务提供者          
>* 在接口上添加@FeignClient("服务名")来指定调用服务          
>* 在接口方法上添加@RequestMapping来指定服务下的API          


># 服务熔断Hystrix
>* 防止因单个服务出现问题时，导致整个微服务系统"雪崩"
>* 服务高可用的最后一道防线
>* 当服务调用失败次数达到阀值时，后面的请求将快速失败，断路器open,过一段时间后默认5s,断路器half-open,将尝试请求后端服务，成功则断路器close,否则open，如此循环。
>* Ribbon:
>* 启动类上@EnableHystrix,再调用的服务上@HystrixCommand(fallbackMethod=熔断方法名)          
>* 在当前类中添加熔断方法
>* Feign:          
>* 配置feign.hystrix.enabled=true          
>* 服务接口@FeignClient(fallback=熔断类)          
>* 实现该服务接口并依赖注入@Component
>* 覆写熔断方法。
          
># 断路器仪表盘
>* 引入dashboard、actuator依赖
>* 启动类添加注解@EnableHystrixDashboard(Feign还需要添加@EnableCircuitBreaker)         
>* open：http://127.0.0.1:10001/hystrix          
>* input：http://127.0.0.1:10001/hystrix.stream         
>* click：monitor stream         
>* open:  http://127.0.0.1:10001/api/**        
>* 即可查看监控断路器状态。
         
># 断路器聚合监控
>* 这里Ribbon和Feign都实现了断路器仪表盘，所以能进行集群监控
>* 新建项目导入依赖spring-cloud-starter-turbine、spring-cloud-netflix-turbine、spring-boot-starter-actuator、 spring-cloud-starter-hystrix-dashboard        
>* 启动类添加@EnableTurbine、@EnableHystrixDashboard
>* 配置默认集群：
>* turbine.appConfig=mv-consumer-feign,mv-consumer-ribbon
>* turbine.clusterNameExpression=new String("default")
>* open：http://127.0.0.1:10001/hystrix
>* open：http://127.0.0.1:10001/turbine.stream

># 网关路由
>* 外部系统访问内部服务的一道墙，直接与调用方通信进行权限控制
>* 可做权限认证、反向代理、负载均衡的API gateway
>* 导入zuul、eureka依赖包
>* 启动类添加@EnableZuulProxy
>* 配置：eureka.client.service-url
>* zuul将eureka中所有注册的服务实现了反向代理
>* 默认实现了负载均衡，只需在其他服务器上配置相同的应用集群。
>* 访问：http://127.0.0.1:10009/[serviceId]/mv/order/{id}
>* 实现ZuulFilter即可实现权限认证

># 高可用配置中心
>* 配置server端：用于连接获取Github上的配置文件信息,并将配置信息clone到本地内存
>* 引入依赖spring-cloud-starter-config-server
>* 配置spring.cloud.config.server.git.uri|search-paths|username|password
>* spring.cloud.config.label
>* 启动类添加@EnableConfigServer

>* 配置client端：从config-server端读取该服务名下profile域下的配置信息
>* 引入依赖spring-cloud-starter-config
>* 把application.yml放到github上并命名为{application}-{profile}.yml.
>* 新建bootstrap.properties:
>* spring.application.name=mv-consumer-feign
>* spring.cloud.config.label=master
>* spring.cloud.config.profile=dev
>* spring.cloud.config.uri=http://127.0.0.1:10010
>* 启动后会先访问http://127.0.0.1:10010/master/mv-consumer-feign-dev.yml文件
>* 并作为application.yml进行解析。

>* 高可用集群:
>* 把相同server端部署到不同服务器上，并作为服务注册到Eureka注册中心
>* 客户端只需添加配置：
>* spring.cloud.config.discovery.enabled=true
>* spring.cloud.config.discovery.serviceId=config-server
>* eureka.client.service-url.defaultZone=http://admin:123456@127.0.0.1:10001/eureka/
>* 用于客户端发现config-server,实现负载均衡，从而高可用。

>* 客户端Refresh最新配置：
>* 添加依赖actuator监控包
>* 在带有@Value的类上添加@RefreshScope
>* 配置management.security.enabled=false 
>* 刷新时关闭安全认证。
>* post访问客户端的/refresh
>* 即刷新@Value的值。

># 消息总线实现所有客户端配置更新
>* 服务器安装rabbitMQ
>* 客户端添加依赖spring-cloud-starter-bus-amqp
>* 配置如下：
>* management.security.enabled=false
>* spring.cloud.bus.trace.enabled=true
>* spring.rabbitmq.host=192.168.66.128
>* spring.rabbitmq.port=5672
>* spring.rabbitmq.username=admin
>* spring.rabbitmq.password=123456
>* 启动后访问post访问任意客户端/bus/refresh即可刷新全部客户端的配置@Value

># <span style="color:red">注意：</span>
>* 1.配置文件不要出现多余的空格。 