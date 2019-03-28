package net.greatsoft.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * spring boot定时器
 * @author litian
 *
 */
@Component
public class ScheduledTasks {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Value("${file.path.temp}")
	private String tempPath;
	
	/**
	 * 每天子时执行任务
	 */
	@Scheduled(cron="0 0 0 ? * *")
	//@Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        System.out.println("当前时间:" + dateFormat.format(new Date())+",执行清空任务附件表临时保存文件夹下的所有文件");
        long start = System.currentTimeMillis();
        FileUtils.delAllFile(tempPath);
        long end = System.currentTimeMillis();
        System.out.println("当前时间:" + dateFormat.format(new Date())+",执行清空任务结束,耗时:"+(end-start)+"毫秒");
	}
}
