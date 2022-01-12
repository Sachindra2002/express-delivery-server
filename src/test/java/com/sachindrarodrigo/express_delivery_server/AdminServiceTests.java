package com.sachindrarodrigo.express_delivery_server;

import com.sachindrarodrigo.express_delivery_server.repository.DocumentsRepository;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.ServiceCenterRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AdminServiceTests {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  MailRepository mailRepository;
    @Autowired
    private  DocumentsRepository documentsRepository;
    @Autowired
    private  ServiceCenterRepository serviceCenterRepository;

}
