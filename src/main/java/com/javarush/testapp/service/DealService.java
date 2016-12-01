package com.javarush.testapp.service;

import com.javarush.testapp.dao.IDealDAO;
import com.javarush.testapp.domain.DealEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Asus on 30.11.2016.
 */
@Service
public class DealService implements IDealService{

    @Autowired
    private IDealDAO dealDAO;

    public List<DealEntity> getAllDeal(Integer startId, Integer maxCount, Boolean isDone) {
        return dealDAO.getAllDeal(startId, maxCount, isDone);
    }

    public DealEntity getDealByID(Integer id) {
        return dealDAO.getDealByID(id);
    }

    public void addDeal(DealEntity deal) {
//        нужно проверять нет ли уже объека в БД, если проверять придется синхронизировать блок
        dealDAO.addDeal(deal);
    }

    public Long getCountDeal(Boolean isDone){
        return dealDAO.getCountDeal(isDone);
    }

    public void updateDeal(DealEntity deal) {
        dealDAO.updateDeal(deal);
    }

    public void deleteDeal(Integer id) {
        dealDAO.deleteDeal(id);
    }
}
