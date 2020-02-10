package com.discoverybank.balancedispense.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.discoverybank.balancedispense.model.dto.ClientAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private Logger logger = LoggerFactory.getLogger(ReportManagementServiceImpl.class);

    // we're scheduling a task to be executed at 10:15 AM on the 1th day of every month.
    @Scheduled(cron = "0 1 10 15 * ?")
    public void scheduleTaskUsingCronExpression() {
        long now = System.currentTimeMillis() / 1000;
        logger.info("Timestamp: "+ now+" \nOn a monthly basis a report is generated and emailed to support@thebank.com");

        //Todo send an email to the support
    }

}
