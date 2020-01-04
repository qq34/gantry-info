package com.eagle.cloud.route.config;

import com.eagle.cloud.route.dao.SysScreenDao;
import com.eagle.cloud.route.service.ScreenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@EnableScheduling
@Slf4j
@Component
public class ScheduledTask {

    @Autowired
    private ScreenService screenService;

    @Autowired
    private SysScreenDao sysScreenDao;

////    @Scheduled(fixedRate = 1000 * 60 * 2)
//    @Async
//    @Scheduled(cron = "0 0/2 * * * ?")
//    public void twoTesk() {
//        log.info("每 2 分钟执行一次");
//        screenService.getGDReadInfo();
//        //计算section 时间和距离
//        screenService.getSectionCountInfo();
//    }

@Async
@Scheduled(cron = "*/5 * * * * ?")
public void twoTesk() {
    log.info("每5s执行一次");
    List<Map> today_money = sysScreenDao.getDataFormGantry_info();
    for (Map map:today_money){
        int money = (int)map.get("today_money");
        sysScreenDao.updateWithGantryID((String) map.get("gantryID"),"today_money",money+120);
        sysScreenDao.updateWithGantryID((String) map.get("gantryID"),"today_car_number",(int)map.get("today_car_number")+20);
    }

}




}

