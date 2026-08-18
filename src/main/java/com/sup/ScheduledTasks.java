
package com.sup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    final SystemInfo sysInfo;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(SystemInfo sysInfo) {
        this.sysInfo = sysInfo;

    };

	@Scheduled(fixedRate = 1000)
	public void reportCurrentTime() throws IOException {
		log.info("The time is now {}", dateFormat.format(new Date()));
        sysInfo.getInfo();
	}
}