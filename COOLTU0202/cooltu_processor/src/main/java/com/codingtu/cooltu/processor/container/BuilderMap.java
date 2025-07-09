package com.codingtu.cooltu.processor.container;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.dealer.builder.ActBaseBuilder;
import com.codingtu.cooltu.processor.dealer.builder.base.BaseBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderMap {

    public static Map<String, ActBaseBuilder> ACT_BASE_BUILDER_MAP = new HashMap<>();
    public static ListValueMap<Integer, BaseBuilder> BUILDER_MAP = new ListValueMap<>();

    public static void putActBaseBuilder(ActBaseBuilder actBaseBuilder) {
        ACT_BASE_BUILDER_MAP.put(actBaseBuilder.javaInfo.fullName, actBaseBuilder);
    }

    public static void put(BaseBuilder builder) {
        put(BuilderType.DEFAULT, builder);
    }

    public static void put(BuilderType builderType, BaseBuilder builder) {
        BUILDER_MAP.get(builderType.ordinal()).add(builder);
    }

    public static void create() {
        Es.maps(BUILDER_MAP).ls(new Es.MapEach<Integer, List<BaseBuilder>>() {
            @Override
            public boolean each(Integer integer, List<BaseBuilder> builders) {
                Es.es(builders).ls(new Es.EachEs<BaseBuilder>() {
                    @Override
                    public boolean each(int position, BaseBuilder builder) {
                        builder.create();
                        return false;
                    }
                });
                return false;
            }
        });
        BUILDER_MAP.clear();
    }
}
