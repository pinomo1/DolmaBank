package com.pinomo.dolmabank.database;

import java.util.ArrayList;
import java.util.List;

public interface BaseLocalEntityDao<T extends LocalPayloadEntity>{
List<T> getAll();

T getById(Long uid);

Long insert(T entity);

void delete(T entity);

void update(T entity);
}
