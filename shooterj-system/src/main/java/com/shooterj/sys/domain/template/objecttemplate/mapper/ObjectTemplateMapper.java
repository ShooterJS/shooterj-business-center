package com.shooterj.sys.domain.template.objecttemplate.mapper;

import com.shooterj.common.mapper.DateMapper;
import com.shooterj.common.mapper.GenericEnumMapper;
import com.shooterj.sys.domain.template.objecttemplate.ObjectTemplate;
import com.shooterj.sys.domain.template.objecttemplate.creator.ObjectTemplateCreator;
import com.shooterj.sys.domain.template.objecttemplate.request.ObjectTemplateCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                GenericEnumMapper.class,
                DateMapper.class
        }
)
public interface  ObjectTemplateMapper {

        ObjectTemplateMapper INSTANCE = Mappers.getMapper(ObjectTemplateMapper.class);

        ObjectTemplate dtoToEntity(ObjectTemplateCreator dto);

        ObjectTemplateCreator request2Dto(ObjectTemplateCreateRequest request);

}
