package com.shooterj.sys.domain.template.objecttemplate.service;

import com.shooterj.common.constants.CodeEnum;
import com.shooterj.common.exception.BusinessException;
import com.shooterj.jpa.support.EntityOperations;
import com.shooterj.sys.domain.template.objecttemplate.ObjectTemplate;
import com.shooterj.sys.domain.template.objecttemplate.TemplateCategory;
import com.shooterj.sys.domain.template.objecttemplate.creator.ObjectTemplateCreator;
import com.shooterj.sys.domain.template.objecttemplate.mapper.ObjectTemplateMapper;
import com.shooterj.sys.domain.template.objecttemplate.repository.ObjectTemplateRepository;
import com.shooterj.sys.domain.template.objecttemplate.repository.TemplateCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class ObjectTemplateServiceImpl implements IObjectTemplateService{
    private final TemplateCategoryRepository categoryRepository;
    private final ObjectTemplateRepository objectTemplateRepository;

    @Override
    public Long createObjectTemplate(ObjectTemplateCreator creator) {
        Optional<TemplateCategory> category = categoryRepository.findById(creator.getCategoryId());
        if (!category.isPresent()) {
            throw new BusinessException(CodeEnum.NotFindError);
        }
        creator.setCategoryCode(category.get().getCode());
        Optional<ObjectTemplate> objectTemplate = EntityOperations.doCreate(objectTemplateRepository)
                .create(() -> ObjectTemplateMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        return objectTemplate.isPresent() ? objectTemplate.get().getId() : 0;
    }
}
