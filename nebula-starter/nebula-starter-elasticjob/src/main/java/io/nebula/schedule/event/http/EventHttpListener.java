package io.nebula.schedule.event.http;

import com.dangdang.ddframe.job.event.JobEventListener;
import com.dangdang.ddframe.job.event.type.JobExecutionEvent;
import com.dangdang.ddframe.job.event.type.JobStatusTraceEvent;
import io.nebula.schedule.event.EventType;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/28
 */
public class EventHttpListener implements JobEventListener {
    @Override
    public void listen(JobExecutionEvent jobExecutionEvent) {

    }

    @Override
    public void listen(JobStatusTraceEvent jobStatusTraceEvent) {

    }

    @Override
    public String getIdentity() {
        return EventType.HTTP.name();
    }
}
