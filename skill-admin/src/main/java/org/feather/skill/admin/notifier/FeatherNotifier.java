package org.feather.skill.admin.notifier;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @projectName: skill
 * @package: org.feather.admin.notifier
 * @className: FeatherNotifier
 * @author: feather
 * @description: 自定义告警
 * @since: 11-Jan-24 10:42 PM
 * @version: 1.0
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class FeatherNotifier  extends AbstractEventNotifier {
    protected FeatherNotifier(InstanceRepository repository) {
        super(repository);
    }

    /**
     * <h2>实现对事件的通知</h2>
     * */
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {

        return Mono.fromRunnable(() -> {

            if (event instanceof InstanceStatusChangedEvent) {
                // TODO 发送短信  发送钉钉消息等
                log.info("Instance Status Change: [{}], [{}], [{}]",
                        instance.getRegistration().getName(), event.getInstance(),
                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());
            } else {
                log.info("Instance Info: [{}], [{}], [{}]",
                        instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }

        });
    }
}
