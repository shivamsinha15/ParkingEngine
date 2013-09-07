package com.parkingengine.persistance.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.parkingengine.domain.entities.PEMeter;
import com.parkingengine.persistance.PEMeterDAO;

@Repository
public class PEMeterDAOImpl extends GenericEjb3DAO<PEMeter> implements PEMeterDAO {


  private final static String PEMETER_PERULE =
      "SELECT pe_rule.per_id FROM pe_meter, pe_meter_rule, pe_rule "
          + "WHERE pe_meter.pem_id = pe_meter_rule.pem_id AND "
          + "pe_meter_rule.per_id = pe_rule.per_id AND pe_meter.pem_id = ?";

  private final static String PEMETER_PESPACE =
      "SELECT pe_space.pes_id FROM pe_meter, pe_space_meter, pe_space "
          + "WHERE pe_meter.pem_id = pe_space_meter.pem_id AND "
          + "pe_space_meter.pes_id = pe_space.pes_id AND pe_meter.pem_id = ?";

  @SuppressWarnings("unchecked")
  public List<PEMeter> findAll() {
    return entityManager.createQuery("from " + getEntityBeanType().getName()).getResultList();
  }

  @Override
  @PersistenceContext(unitName = "ParkDB")
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;

  }

  public List<Long> getRuleIds(long meterId) {
    try {
      Query q = entityManager.createNativeQuery(PEMETER_PERULE);
      q.setParameter(1, meterId);
      q.unwrap(SQLQuery.class).addScalar("pe_rule.per_id", StandardBasicTypes.LONG);
      List<Long> peRuleIds = (List<Long>) q.getResultList();
      return peRuleIds;
    } catch (NoResultException nre) {
      // Do Some Type Of Logging
      return null;
    }
  }

  public List<Long> getSpaceIds(long meterId) {
    try {
      Query q = entityManager.createNativeQuery(PEMETER_PESPACE);
      q.setParameter(1, meterId);
      q.unwrap(SQLQuery.class).addScalar("pe_space.pes_id", StandardBasicTypes.LONG);
      List<Long> peSpaceIds = (List<Long>) q.getResultList();
      return peSpaceIds;
    } catch (NoResultException nre) {
      // Do Some Type Of Logging
      return null;
    }
  }



}
