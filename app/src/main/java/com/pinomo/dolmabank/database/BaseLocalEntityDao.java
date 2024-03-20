package com.pinomo.dolmabank.database;

import java.util.ArrayList;

public interface BaseLocalEntityDao<T extends LocalPayloadEntity>{
ArrayList<T> getAll();

T getById(Long uid);

Long insert(T entity);

void delete(T entity);
}
