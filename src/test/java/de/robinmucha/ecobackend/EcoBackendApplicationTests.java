package de.robinmucha.ecobackend;

import de.robinmucha.ecobackend.model.controller.device.DeviceController;
import de.robinmucha.ecobackend.model.controller.device_type.DeviceTypeController;
import de.robinmucha.ecobackend.model.controller.event.EventController;
import de.robinmucha.ecobackend.model.controller.event_type.EventTypeController;
import de.robinmucha.ecobackend.model.controller.user.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EcoBackendApplicationTests {

    @Autowired
    private UserController userController;
    @Autowired
    private DeviceController deviceController;
    @Autowired
    private DeviceTypeController deviceTypeController;
    @Autowired
    private EventTypeController eventTypeController;
    @Autowired
    private EventController eventController;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
        assertThat(deviceController).isNotNull();
        assertThat(deviceTypeController).isNotNull();
        assertThat(eventTypeController).isNotNull();
        assertThat(eventController).isNotNull();
    }

}
