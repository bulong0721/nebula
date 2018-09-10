package com.rhea.messaging.annotation;

public @interface EnableMessaging {

    boolean produceOn() default true;

    boolean consumeOn() default false;
}
