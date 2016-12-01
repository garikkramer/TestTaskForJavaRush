package com.javarush.testapp.service;

import com.javarush.testapp.domain.DealEntity;

import java.util.List;

/**
 * Created by Asus on 30.11.2016.
 */

public interface IDealService {
    List<DealEntity> getAllDeal(Integer startId, Integer maxCount, Boolean isDone);
    DealEntity getDealByID(Integer id);
    Long getCountDeal(Boolean isDone);
    void addDeal(DealEntity deal);
    void updateDeal(DealEntity deal);
    void deleteDeal(Integer id);
}
