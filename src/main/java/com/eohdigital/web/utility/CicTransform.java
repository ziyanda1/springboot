package com.eohdigital.web.utility;

import com.eohdigital.domain.Cic;
import com.eohdigital.web.dto.CicDto;
import java.util.function.Function;

/**
 * Created by Ziyanda Mnguni on 2018-05-09.
 */
public class CicTransform {

  private CicTransform() { }

  public static Function<Cic, CicDto> toDto = cic -> {
    CicDto dto = new CicDto();
    dto.setCicId(cic.getCicId());
    dto.setCicType(cic.getCicType());
    dto.setSubject(cic.getSubject());
    dto.setBody(cic.getBody());
    return dto;
  };

  public static Function<CicDto, Cic> fromDto = dto -> {
    Cic cic = new Cic();
    cic.setCicId(dto.getCicId());
    cic.setCicType(dto.getCicType());
    cic.setSubject(dto.getSubject());
    cic.setBody(dto.getBody());
    return cic;
  };
}
