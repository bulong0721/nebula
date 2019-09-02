package ${package}.producer;

import ${package}.model.Student;
import io.nebula.messaging.annotation.Producer;
import io.nebula.messaging.api.MessageSender;

@Producer(topic = "student")
public interface StudentProducer extends MessageSender<Student> {
}
