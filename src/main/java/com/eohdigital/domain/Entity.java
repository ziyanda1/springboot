package com.eohdigital.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Created by Ziyanda Mnguni on 2018-05-09.
 */

@Table(name = "entity")
@Data
@javax.persistence.Entity
public class Entity {

  @Id
  @GeneratedValue
  @Column(name = "entity_id")
  private Long entityId;

  @Column(name = "entity_name")
  private String entityName;

  @Column(name = "email_address")
  private String emailAdress;
}
