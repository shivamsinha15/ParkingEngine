package com.parkingengine.persistance.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.parkingengine.domain.entities.PESpace;
import com.parkingengine.persistance.PERuleDAO;
import com.parkingengine.persistance.PESpaceDAO;

@Repository
public class PESpaceDAOImpl extends GenericEjb3DAO<PESpace> implements PESpaceDAO {
  private static final Logger LOG = LoggerFactory.getLogger(PESpaceDAOImpl.class);
  private static final String SELECT_RULE_ID =
      "SELECT pe_meter_rule.per_id FROM pe_space, pe_space_meter, pe_meter, pe_meter_rule "
          + "WHERE pe_space.pes_id = pe_space_meter.pes_id AND pe_space_meter.pem_id = pe_meter.pem_id "
          + "AND pe_meter.pem_id = pe_meter_rule.pem_id  AND pe_space.pes_id = ?";

  @Inject
  PERuleDAO peRuleDAOImpl;

  @SuppressWarnings("unchecked")
  public List<PESpace> findAll() {
    return entityManager.createQuery("from " + getEntityBeanType().getName()).getResultList();
  }

  @PersistenceContext(unitName = "EconDatesDB")
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;

  }

  // http://stackoverflow.com/questions/4227578/does-jpa-have-an-equivalent-to-hibernate-sqlquery-addscalar
  /* Example of How To return a Native Query & Map DataTypes */
  public List<Long> getRuleIds(long spaceId) {
    try {
      Query q = entityManager.createNativeQuery(SELECT_RULE_ID);
      q.setParameter(1, spaceId);
      q.unwrap(SQLQuery.class).addScalar("pe_meter_rule.per_id", StandardBasicTypes.LONG);
      List<Long> peRuleIds = (List<Long>) q.getResultList();
      return peRuleIds;
    } catch (NoResultException nre) {
      LOG.info("Country does not exist in Database ");
      return null;
    }
  }



}
