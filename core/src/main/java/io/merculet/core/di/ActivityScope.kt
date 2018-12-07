package io.merculet.core.di

import java.lang.annotation.Retention

import javax.inject.Scope

import java.lang.annotation.RetentionPolicy.RUNTIME

@Scope
@Retention(RUNTIME)
annotation class ActivityScope