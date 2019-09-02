package ${package}.consumer;

import ${package}.model.Student;
import io.nebula.messaging.api.Headers;
import io.nebula.messaging.annotation.Consumer;
import io.nebula.messaging.api.MessageListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Consumer(topic = "student")
public class StudentConsumer extends MessageListener<Student> {

    @Override
    public void onMessage(Headers headers, Student student) throws Exception {
        log.info("receive message: {}", student);
    }
}
