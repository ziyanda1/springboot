package com.eohdigital.Service;

import com.eohdigital.domain.Cic;
import com.eohdigital.repository.CicRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ziyanda Mnguni on 2018-05-09.
 */

@Service
public class CicService {

  @Autowired
  private CicRepository cicRepository;

  public Cic registerCic(Cic cic){
    return cicRepository.save(cic);
  }

  public Cic retrieveCicInfo(Long cidId){
   Optional<Cic> cicOptional = cicRepository.findById(cidId);
   return cicOptional.isPresent() ? cicOptional.get() : null;
  }
}
