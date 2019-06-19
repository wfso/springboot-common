package com.yioks.springboot.common.base.service;

public interface Service<T, ID> extends QueryService<T, ID>, CURDService<T, ID> {
}
