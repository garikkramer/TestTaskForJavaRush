package com.javarush.testapp.dao;

import com.javarush.testapp.domain.DealEntity;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Asus on 30.11.2016.
 */
@Transactional
@Repository
public class DealDAO implements IDealDAO {

    @Autowired
    private HibernateTemplate template;

    public Long getCountDeal( Boolean isDone) {
        String isDoneString = "";

        if (isDone != null)
            if(isDone)
                isDoneString = "WHERE IS_DONE = TRUE";
            else
                isDoneString = "WHERE IS_DONE = FALSE";

        Query query = template.
                getSessionFactory().
                getCurrentSession().
                createQuery("select count(*) from DealEntity " + isDoneString);

        return (Long)query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<DealEntity> getAllDeal(Integer startId, Integer maxCount, Boolean isDone) {
        String isDoneString = "";

        if (isDone != null)
        if(isDone)
            isDoneString = "WHERE d.IS_DONE = TRUE";
        else
            isDoneString = "WHERE d.IS_DONE = FALSE";
        
        

        String sql = String.format(
                        "SELECT * FROM deal as d %s ORDER BY d.ID ASC LIMIT %d, %d",
                        isDoneString.toString(),
                        startId,
                        startId + maxCount);


        Query query = template.getSessionFactory().getCurrentSession().createSQLQuery(sql);

        return convertResult(query);
    }
    private List<DealEntity> convertResult(Query query){
        List<DealEntity> result = new LinkedList<DealEntity>();
        for (Object obj : query.list()) {
            Object[] array = (Object[])obj;
            DealEntity deal = new DealEntity();
            deal.setId((Integer)array[0]);
            deal.setName((String)array[1]);
            deal.setCreatedDate((Date)array[2]);
            deal.setDescription((String)array[3]);
            deal.setDone((Boolean)array[4]);
            result.add(deal);
        }
        return result;
    }

    public DealEntity getDealByID(Integer id) {
        return template.get(DealEntity.class, id);
    }

    public void addDeal(DealEntity deal) {
        template.save(deal);
    }

    public void updateDeal(DealEntity deal) {
        template.flush();
        DealEntity temp = getDealByID(deal.getId());
        temp.setName(deal.getName());
        temp.setDescription(deal.getDescription());
        temp.setDone(deal.getDone());
        template.update(temp);
    }

    public void deleteDeal(Integer id) {
        template.delete(getDealByID(id));
    }
}
