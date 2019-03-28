package net.greatsoft.core.timer;

import net.greatsoft.core.service.system.OrgService;
import net.greatsoft.core.service.system.UserService;
import net.greatsoft.core.service.timer.TransferTimerService;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableScheduling/*添加定时器*/
public class TransferTimer {

    @Resource
    private UserService userService;

    @Resource
    private OrgService orgService;

    @Resource
    private TransferTimerService transferTimerService;

   /* @Scheduled(cron = "0 59 23 * * ?")*/
    public void TransferTimerTask () {
        this.transferTimerService.transferTimerTask();
    }
}
