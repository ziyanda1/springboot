package com.eohdigital.repository;

import com.eohdigital.domain.Cic;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ziyanda Mnguni on 2018-05-09.
 */
public interface CicRepository extends JpaRepository<Cic, Long> {

  Cic save(Cic cic);

  Optional<Cic> findById(Long cicId);

}
