package com.shooterj.sys.controller;


//import com.shooterj.security.util.SpringSecurityUtils;
import com.shooterj.common.model.JsonObject;
import com.shooterj.sys.domain.template.objecttemplate.creator.ObjectTemplateCreator;
import com.shooterj.sys.domain.template.objecttemplate.mapper.ObjectTemplateMapper;
import com.shooterj.sys.domain.template.objecttemplate.request.ObjectTemplateCreateRequest;
import com.shooterj.sys.domain.template.objecttemplate.service.IObjectTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("template/objectTemplate/v1")
@RequiredArgsConstructor
public class ObjectTemplateController {

  private final IObjectTemplateService objectTemplateService;

  /**
   * createRequest
   */
  @PostMapping("createObjectTemplate")
  public JsonObject<Long> createObjectTemplate(@RequestBody ObjectTemplateCreateRequest request) {
    ObjectTemplateCreator creator = ObjectTemplateMapper.INSTANCE.request2Dto(request);
    //TODO
    //creator.setCreateUser(SpringSecurityUtils.getCurrentUsername());
    return JsonObject.success(objectTemplateService.createObjectTemplate(creator));
  }


}
