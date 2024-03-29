package com.shooterj.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.CaseFormat;
import com.shooterj.common.exception.InvalidDataFieldException;
import com.shooterj.common.object.Tuple2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 负责Model数据操作、类型转换和关系关联等行为的工具类。
 *
 * @author shooterj
 */
@Slf4j
public class MyModelUtil {

    /**
     * 数值型字段。
     */
    public static final Integer NUMERIC_FIELD_TYPE = 0;
    /**
     * 字符型字段。
     */
    public static final Integer STRING_FIELD_TYPE = 1;
    /**
     * 日期型字段。
     */
    public static final Integer DATE_FIELD_TYPE = 2;
    /**
     * 整个工程的实体对象中，创建者Id字段的Java对象名。
     */
    public static final String CREATE_USER_ID_FIELD_NAME = "createUserId";
    /**
     * 整个工程的实体对象中，创建时间字段的Java对象名。
     */
    public static final String CREATE_TIME_FIELD_NAME = "createTime";
    /**
     * 整个工程的实体对象中，更新者Id字段的Java对象名。
     */
    public static final String UPDATE_USER_ID_FIELD_NAME = "updateUserId";
    /**
     * 整个工程的实体对象中，更新时间字段的Java对象名。
     */
    public static final String UPDATE_TIME_FIELD_NAME = "updateTime";
    /**
     * mapToColumnName和mapToColumnInfo使用的缓存。
     */
    private static final Map<String, Tuple2<String, Integer>> CACHED_COLUMNINFO_MAP = new ConcurrentHashMap<>();

    /**
     * 将Bean的数据列表转换为Map列表。
     *
     * @param dataList Bean数据列表。
     * @param <T>      Bean对象类型。
     * @return 转换后的Map列表。
     */
    public static <T> List<Map<String, Object>> beanToMapList(List<T> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return new LinkedList<>();
        }
        List<Map<String, Object>> resultList = new LinkedList<>();
        dataList.forEach(data -> resultList.add(BeanUtil.beanToMap(data)));
        return resultList;
    }

    /**
     * 将Map的数据列表转换为Bean列表。
     *
     * @param dataList Map数据列表。
     * @param <T>      Bean对象类型。
     * @return 转换后的Bean对象列表。
     */
    public static <T> List<T> mapToBeanList(List<Map<String, Object>> dataList, Class<T> clazz) {
        if (CollectionUtils.isEmpty(dataList)) {
            return new LinkedList<>();
        }
        List<T> resultList = new LinkedList<>();
        dataList.forEach(data -> resultList.add(BeanUtil.toBeanIgnoreError(data, clazz)));
        return resultList;
    }

    /**
     * 拷贝源类型的集合数据到目标类型的集合中，其中源类型和目标类型中的对象字段类型完全相同。
     * NOTE: 该函数主要应用于框架中，Dto和Model之间的copy，特别针对一对一关联的深度copy。
     * 在Dto中，一对一对象可以使用Map来表示，而不需要使用从表对象的Dto。
     *
     * @param sourceCollection 源类型集合。
     * @param targetClazz      目标类型的Class对象。
     * @param <S>              源类型。
     * @param <T>              目标类型。
     * @return copy后的目标类型对象集合。
     */
    public static <S, T> List<T> copyCollectionTo(Collection<S> sourceCollection, Class<T> targetClazz) {
        if (sourceCollection == null) {
            return null;
        }
        List<T> targetList = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(sourceCollection)) {
            for (S source : sourceCollection) {
                try {
                    T target = targetClazz.newInstance();
                    BeanUtil.copyProperties(source, target);
                    targetList.add(target);
                } catch (Exception e) {
                    log.error("Failed to call MyModelUtil.copyCollectionTo", e);
                    return Collections.emptyList();
                }
            }
        }
        return targetList;
    }

    /**
     * 拷贝源类型的对象数据到目标类型的对象中，其中源类型和目标类型中的对象字段类型完全相同。
     * NOTE: 该函数主要应用于框架中，Dto和Model之间的copy，特别针对一对一关联的深度copy。
     * 在Dto中，一对一对象可以使用Map来表示，而不需要使用从表对象的Dto。
     *
     * @param source      源类型对象。
     * @param targetClazz 目标类型的Class对象。
     * @param <S>         源类型。
     * @param <T>         目标类型。
     * @return copy后的目标类型对象。
     */
    public static <S, T> T copyTo(S source, Class<T> targetClazz) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClazz.newInstance();
            BeanUtil.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            log.error("Failed to call MyModelUtil.copyTo", e);
            return null;
        }
    }

    /**
     * 映射Model对象的字段反射对象，获取与该字段对应的数据库列名称。
     *
     * @param field      字段反射对象。
     * @param modelClazz Model对象的Class类。
     * @return 该字段所对应的数据表列名称。
     */
    public static String mapToColumnName(Field field, Class<?> modelClazz) {
        return mapToColumnName(field.getName(), modelClazz);
    }

    /**
     * 映射Model对象的字段名称，获取与该字段对应的数据库列名称。
     *
     * @param fieldName  字段名称。
     * @param modelClazz Model对象的Class类。
     * @return 该字段所对应的数据表列名称。
     */
    public static String mapToColumnName(String fieldName, Class<?> modelClazz) {
        Tuple2<String, Integer> columnInfo = mapToColumnInfo(fieldName, modelClazz);
        return columnInfo == null ? null : columnInfo.getFirst();
    }

    /**
     * 映射Model对象的字段反射对象，获取与该字段对应的数据库列名称。
     * 如果没有匹配到ColumnName，则立刻抛出异常。
     *
     * @param field      字段反射对象。
     * @param modelClazz Model对象的Class类。
     * @return 该字段所对应的数据表列名称。
     */
    public static String safeMapToColumnName(Field field, Class<?> modelClazz) {
        return safeMapToColumnName(field.getName(), modelClazz);
    }

    /**
     * 映射Model对象的字段名称，获取与该字段对应的数据库列名称。
     * 如果没有匹配到ColumnName，则立刻抛出异常。
     *
     * @param fieldName  字段名称。
     * @param modelClazz Model对象的Class类。
     * @return 该字段所对应的数据表列名称。
     */
    public static String safeMapToColumnName(String fieldName, Class<?> modelClazz) {
        String columnName = mapToColumnName(fieldName, modelClazz);
        if (columnName == null) {
            throw new InvalidDataFieldException(modelClazz.getSimpleName(), fieldName);
        }
        return columnName;
    }

    /**
     * 映射Model对象的字段名称，获取与该字段对应的数据库列名称和字段类型。
     *
     * @param fieldName  字段名称。
     * @param modelClazz Model对象的Class类。
     * @return 该字段所对应的数据表列名称和Java字段类型。
     */
    public static Tuple2<String, Integer> mapToColumnInfo(String fieldName, Class<?> modelClazz) {
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append(modelClazz.getName()).append("-#-").append(fieldName);
        Tuple2<String, Integer> columnInfo = CACHED_COLUMNINFO_MAP.get(sb.toString());
        if (columnInfo == null) {
            Field field = ReflectUtil.getField(modelClazz, fieldName);
            if (field == null) {
                return null;
            }
            TableField c = field.getAnnotation(TableField.class);
            String columnName = null;
            if (c == null) {
                TableId id = field.getAnnotation(TableId.class);
                if (id != null) {
                    columnName = id.value();
                }
            }
            if (columnName == null) {
                columnName = c == null ? CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName) : c.value();
                if (StringUtils.isBlank(columnName)) {
                    columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
                }
            }
            // 这里缺省情况下都是按照整型去处理，因为他覆盖太多的类型了。
            // 如Integer/Long/Double/BigDecimal，可根据实际情况完善和扩充。
            String typeName = field.getType().getSimpleName();
            Integer type = NUMERIC_FIELD_TYPE;
            if (String.class.getSimpleName().equals(typeName)) {
                type = STRING_FIELD_TYPE;
            } else if (Date.class.getSimpleName().equals(typeName)) {
                type = DATE_FIELD_TYPE;
            }
            columnInfo = new Tuple2<>(columnName, type);
            CACHED_COLUMNINFO_MAP.put(sb.toString(), columnInfo);
        }
        return columnInfo;
    }

    /**
     * 映射Model主对象的Class名称，到Model所对应的表名称。
     *
     * @param modelClazz Model主对象的Class。
     * @return Model对象对应的数据表名称。
     */
    public static String mapToTableName(Class<?> modelClazz) {
        TableName t = modelClazz.getAnnotation(TableName.class);
        return t == null ? null : t.value();
    }

    /**
     * 根据参数中的数据列表和字段提取函数，封装stream api的方式返回指定字段的数据。
     *
     * @param dataList        数据对象列表。
     * @param fieldGetterFunc 字段获取函数。
     * @param <T>             数据对象类型。
     * @param <K>             返回字段类型。
     * @return 指定字段Set集合。
     */
    public static <T, K> Set<K> retrieveFieldSet(Collection<T> dataList, Function<T, K> fieldGetterFunc) {
        return dataList.stream().map(fieldGetterFunc).collect(Collectors.toSet());
    }



    /**
     * 根据主对象和关联对象各自的关联Id函数，将主对象列表和关联对象列表中的数据关联到一起，并将关联对象
     * 设置到主对象的指定关联字段中。
     * NOTE: 用于主对象关联字段中，没有包含RelationOneToOne注解的场景。
     *
     * @param thisClazz         主对象的Class对象。
     * @param thisModelList     主对象列表。
     * @param thisIdGetterFunc  主对象Id的Getter函数。
     * @param thatModelList     关联对象列表。
     * @param thatIdGetterFunc  关联对象Id的Getter函数。
     * @param thisRelationField 主对象中保存被关联对象的字段名称。
     * @param <T>               主表对象类型。
     * @param <R>               从表对象类型。
     */
    public static <T, R> void makeOneToOneRelation(
            Class<T> thisClazz,
            List<T> thisModelList,
            Function<T, Object> thisIdGetterFunc,
            List<R> thatModelList,
            Function<R, Object> thatIdGetterFunc,
            String thisRelationField) {
        makeOneToOneRelation(thisClazz, thisModelList,
                thisIdGetterFunc, thatModelList, thatIdGetterFunc, thisRelationField, false);
    }

    /**
     * 根据主对象和关联对象各自的关联Id函数，将主对象列表和关联对象列表中的数据关联到一起，并将关联对象
     * 设置到主对象的指定关联字段中。
     * NOTE: 用于主对象关联字段中，没有包含RelationOneToOne注解的场景。
     *
     * @param thisClazz         主对象的Class对象。
     * @param thisModelList     主对象列表。
     * @param thisIdGetterFunc  主对象Id的Getter函数。
     * @param thatModelList     关联对象列表。
     * @param thatIdGetterFunc  关联对象Id的Getter函数。
     * @param thisRelationField 主对象中保存被关联对象的字段名称。
     * @param orderByThatList   如果为true，则按照ThatModelList的顺序输出。同时thisModelList被排序后的新列表替换。
     * @param <T>               主表对象类型。
     * @param <R>               从表对象类型。
     */
    public static <T, R> void makeOneToOneRelation(
            Class<T> thisClazz,
            List<T> thisModelList,
            Function<T, Object> thisIdGetterFunc,
            List<R> thatModelList,
            Function<R, Object> thatIdGetterFunc,
            String thisRelationField,
            boolean orderByThatList) {
        if (CollectionUtils.isEmpty(thisModelList)) {
            return;
        }
        Field thisTargetField = ReflectUtil.getField(thisClazz, thisRelationField);
        boolean isMap = thisTargetField.getType().equals(Map.class);
        if (orderByThatList) {
            List<T> newThisModelList = new LinkedList<>();
            Map<Object, ? extends T> thisModelMap =
                    thisModelList.stream().collect(Collectors.toMap(thisIdGetterFunc, c -> c));
            thatModelList.forEach(thatModel -> {
                Object thatId = thatIdGetterFunc.apply(thatModel);
                T thisModel = thisModelMap.get(thatId);
                if (thisModel != null) {
                    ReflectUtil.setFieldValue(thisModel, thisTargetField, normalize(isMap, thatModel));
                    newThisModelList.add(thisModel);
                }
            });
            thisModelList.clear();
            thisModelList.addAll(newThisModelList);
        } else {
            Map<Object, R> thatMadelMap =
                    thatModelList.stream().collect(Collectors.toMap(thatIdGetterFunc, c -> c));
            thisModelList.forEach(thisModel -> {
                Object thisId = thisIdGetterFunc.apply(thisModel);
                R thatModel = thatMadelMap.get(thisId);
                if (thatModel != null) {
                    ReflectUtil.setFieldValue(thisModel, thisTargetField, normalize(isMap, thatModel));
                }
            });
        }
    }



    private static <M> Object normalize(boolean isMap, M model) {
        return isMap ? BeanUtil.beanToMap(model) : model;
    }





    /**
     * 为实体对象字段设置缺省值。如果data对象中指定字段的值为NULL，则设置缺省值，否则跳过。
     * @param data         实体对象。
     * @param fieldName    实体对象字段名。
     * @param defaultValue 缺省值。
     * @param <M> 实体对象类型。
     * @param <V> 缺省值类型。
     */
    public static <M, V> void setDefaultValue(M data, String fieldName, V defaultValue) {
        Object v = ReflectUtil.getFieldValue(data, fieldName);
        if (v == null) {
            ReflectUtil.setFieldValue(data, fieldName, defaultValue);
        }
    }



    /**
     * 私有构造函数，明确标识该常量类的作用。
     */
    private MyModelUtil() {
    }
}
