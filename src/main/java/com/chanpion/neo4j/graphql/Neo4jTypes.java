package com.chanpion.neo4j.graphql;

import graphql.language.TypeDefinition;

import java.util.Arrays;
import java.util.List;

/**
 * @author April Chen
 * @date 2021/3/24 15:56
 */
public class Neo4jTypes {
    public static final String NEO4j_FORMATTED_PROPERTY_KEY = "formatted";
    public static final String NEO4j_POINT_DISTANCE_FILTER = "_Neo4jPointDistanceFilter";
    public static final String NEO4j_POINT_DISTANCE_FILTER_SUFFIX = "_distance";

    public static final List<TypeDefinition> neo4jTypeDefinitions = Arrays.asList(
            new TypeDefinition("LocalTime", "_Neo4jTime"),
            new TypeDefinition("Date", "_Neo4jDate"),
            new TypeDefinition("DateTime", "_Neo4jDateTime"),
            new TypeDefinition("Time", "_Neo4jLocalTime"),
            new TypeDefinition("LocalDateTime", "_Neo4jLocalDateTime"),
            new TypeDefinition("Point", "_Neo4jPoint"));

    static class TypeDefinition {
        String name;
        String typeDefinition;
        String inputDefinition;

        public TypeDefinition() {
        }

        public TypeDefinition(String name, String typeDefinition) {
            this.name = name;
            this.typeDefinition = typeDefinition;
            this.inputDefinition = typeDefinition + "Input";
        }

        public TypeDefinition(String name, String typeDefinition, String inputDefinition) {
            this.name = name;
            this.typeDefinition = typeDefinition;
            this.inputDefinition = inputDefinition;
        }
    }

    public static class Neo4jConverter {

    }
}
