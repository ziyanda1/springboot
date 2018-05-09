package com.eohdigital.web;

import com.eohdigital.Service.CicService;
import com.eohdigital.domain.Cic;
import com.eohdigital.web.dto.CicDto;
import com.eohdigital.web.utility.CicTransform;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ziyanda Mnguni on 2018-05-08.
 */

@RestController
@RequestMapping("/cic")
public class CicWebService {

  @Autowired
  private CicService cicService;

  private Logger log = LoggerFactory.getLogger(CicWebService.class);

  @GetMapping("/{cicId}")
  public ResponseEntity retrieveCicInfo(@PathVariable("cicId") Long cicId){
    log.debug("retrieveCicInfo(..) id={}", cicId);
    Cic cic = cicService.retrieveCicInfo(cicId);
    if (cic == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
          createErrorResponseEntity(String.format("Could not find cic for Id %s", cicId)));
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(CicTransform.toDto.apply(cic));
    }
  }


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createCic(@RequestBody CicDto cicDto){
    log.debug("createCic(..) cic details={}", cicDto);

    Cic retrievedCic  = cicService.retrieveCicInfo(cicDto.getCicId());
    if (retrievedCic != null) {
      return ResponseEntity.ok(CicTransform.toDto.apply(retrievedCic));
    }

    Cic cic = CicTransform.fromDto.apply(cicDto);
    cicService.registerCic(cic);
    return ResponseEntity.status(HttpStatus.CREATED).body(CicTransform.toDto.apply(cic));
  }


  private Object createErrorResponseEntity(String message) {
    return new HashMap<String, Object>() {{
      put("error", message);
    }};
  }
}
