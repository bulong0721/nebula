package ${package}.client;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * galaxy.report在ms子项目的application-dev.properties配置
 *
 * 用法：
 *      @Autowired
 *      private ReportService reportService;
 *
 *      reportService.queryBusiness();
 *
 */
//@FeignClient(value = ReportService.SERVICE_NAME, url = "${galaxy.report}")
//public interface ReportService {
//
//    String SERVICE_NAME = "report";
//
//    @RequestLine("GET /display/queryBusiness")
//    String queryBusiness();
//}
