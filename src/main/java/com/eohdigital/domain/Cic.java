package com.eohdigital.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Created by Ziyanda Mnguni on 2018-05-09.
 */

@Entity
@Table(name = "cic")
@Data
public class Cic {

  @Id
  @Column(name = "cic_id")
  private Long cicId;

  @Column(name = "cic_type")
  private String cicType;

  @Column(name = "subject")
  private String subject;

  @Column(name = "body")
  private String body;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "entity_id")
  private com.eohdigital.domain.Entity entity;

}
