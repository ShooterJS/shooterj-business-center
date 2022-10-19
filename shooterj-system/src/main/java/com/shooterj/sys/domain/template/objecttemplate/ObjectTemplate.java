package com.shooterj.sys.domain.template.objecttemplate;

import com.shooterj.common.annotation.FieldDesc;
import com.shooterj.common.constants.ValidStatus;
import com.shooterj.jpa.converter.ValidStatusConverter;
import com.shooterj.jpa.support.BaseJpaAggregate;
import lombok.Data;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "object_template")
//@GenVo(pkgName = "com.shooterj.system.domain.template.objecttemplate.vo")
//@GenCreator(pkgName = "com.shooterj.system.domain.template.objecttemplate.creator")
//@GenUpdater(pkgName = "com.shooterj.system.domain.template.objecttemplate.updater")
//@GenQuery(pkgName = "com.shooterj.system.domain.template.objecttemplate.query")
//@GenController(pkgName = "com.shooterj.system.domain.template.objecttemplate.controller")
//@GenService(pkgName = "com.shooterj.system.domain.template.objecttemplate.service")
//@GenRepository(pkgName = "com.shooterj.system.domain.template.objecttemplate.repository")
//@GenMapper(pkgName = "com.shooterj.system.domain.template.objecttemplate.mapper")
//@GenServiceImpl(pkgName = "com.shooterj.system.domain.template.objecttemplate.service.impl")
@Data
public class ObjectTemplate extends BaseJpaAggregate {

  @FieldDesc(name = "模板名称")
  @NotEmpty(message = "模板名称不能为空")
  private String name;

  @FieldDesc(name = "模板编码")
  @NotEmpty(message = "模板编码不能为空")
  private String code;

  @FieldDesc(name = "创建人")
  private String createUser;

  @FieldDesc(name = "模板code")
  private String categoryCode;

  @FieldDesc(name = "模板id")
  private Long categoryId;

  @FieldDesc(name = "描述信息")
  private String description;

  @Convert(converter = ValidStatusConverter.class)
  //@IgnoreUpdater
  //@IgnoreCreator
  private ValidStatus validStatus;

  public void init() {
    setValidStatus(ValidStatus.VALID);
  }

  public void valid(){
    setValidStatus(ValidStatus.VALID);
  }

  public void invalid(){
    setValidStatus(ValidStatus.INVALID);
  }
}
