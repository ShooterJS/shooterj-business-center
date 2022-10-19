package com.shooterj.sys.domain.template.objecttemplate.service;

import com.shooterj.sys.domain.template.objecttemplate.creator.ObjectTemplateCreator;

public interface IObjectTemplateService {

    /**
     * create
     */
    Long createObjectTemplate(ObjectTemplateCreator creator);

}
