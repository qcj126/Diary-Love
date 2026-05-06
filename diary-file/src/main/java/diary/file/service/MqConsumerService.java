package diary.file.service;

import com.rabbitmq.client.Channel;
import diary.common.entity.file.po.OssUploadSuccessMsg;
import org.springframework.amqp.core.Message;

public interface MqConsumerService {
    void handleUploadSuccess(OssUploadSuccessMsg message, Message amqpMessage, Channel channel);

    void handleDeadLetter(OssUploadSuccessMsg message, Message amqpMessage, Channel channel);
}
