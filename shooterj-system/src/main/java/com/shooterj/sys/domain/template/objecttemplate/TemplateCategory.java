package com.shooterj.sys.domain.template.objecttemplate;

import com.shooterj.common.annotation.FieldDesc;
import com.shooterj.common.constants.ValidStatus;
import com.shooterj.jpa.converter.ValidStatusConverter;
import com.shooterj.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "template_category")
@Data
public class TemplateCategory extends BaseJpaAggregate {

  @FieldDesc(name = "分类名称")
  private String name;

  /**
   * 分类编码约定规律，这时业务系统好使用
   */
  @FieldDesc(name = "分类编码")
  private String code;

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
