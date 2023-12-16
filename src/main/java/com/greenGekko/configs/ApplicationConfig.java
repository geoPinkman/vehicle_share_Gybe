package com.greenGekko.configs;

import com.greenGekko.GreenGekkoApplication;
import com.greenGekko.servicies.*;
import com.greenGekko.servicies.user_service.UserRoleService;
import com.greenGekko.servicies.user_service.UsersService;
import com.greenGekko.utils.ValidationPhoneNumber;
import org.jooq.DSLContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableAsync
@Configuration
@ConditionalOnBean(GreenGekkoApplication.class)

public class ApplicationConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }

    @Bean
    public VehiclePatternService vehiclePatternService(DSLContext dslContext) {
        return new VehiclePatternService(dslContext);
    }
    @Bean
    public UsersService usersService(DSLContext dslContext,
                                     RolesService rolesService,
                                     UserRoleService userRoleService,
                                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new UsersService(dslContext, rolesService, userRoleService, bCryptPasswordEncoder);
    }
    @Bean
    public OwnerService ownerService(DSLContext dslContext,
            VehicleService vehicleService,
            OwnerOptionsService optionsService,
            ValidationPhoneNumber validationPhoneNumber
    //       , BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        return new OwnerService(dslContext,
                vehicleService,
                optionsService,
                validationPhoneNumber
        //       , bCryptPasswordEncoder
        );

    }
}
