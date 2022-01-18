package com.sachindrarodrigo.express_delivery_server.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomSecurityContextFactory.class)
public @interface WithCustomUser {

    String username() default "lahiru@gmail.com";

    String name() default "lahiru@gmail.com";
}
