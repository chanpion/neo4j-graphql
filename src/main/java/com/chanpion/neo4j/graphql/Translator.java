package com.chanpion.neo4j.graphql;

import graphql.schema.GraphQLSchema;

/**
 * @author April Chen
 * @date 2021/3/24 11:23
 */
public class Translator {
    private GraphQLSchema schema;

    public Translator(GraphQLSchema schema) {
        this.schema = schema;
    }


    class CypherArgument {
        private String name;
        private String propertyName;
        private Object value;
        private Neo4jTypes.Neo4jConverter converter;
        private String cypherParam;

        public String toCypherString(String variable, Boolean asJson) {
            String separator = asJson ? ": " : " = ";
            return null;
        }
    }
}
