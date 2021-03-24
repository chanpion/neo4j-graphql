package com.chanpion.neo4j.graphql;

import graphql.schema.GraphQLType;

import java.util.Collections;
import java.util.Map;

/**
 * @author April Chen
 * @date 2021/3/24 13:39
 */
public class Cypher {
    private final String query;
    private final Map<String, Object> params;
    private GraphQLType type;

    public static final Cypher EMPTY = new Cypher("", Collections.emptyMap(), null);

    public Cypher(String query, Map<String, Object> params, GraphQLType type) {
        this.query = query;
        this.params = params;
        this.type = type;
    }

    public final Cypher with(Map<String, Object> p) {
        if (p == null) {
            throw new NullPointerException("p");
        }
        p.putAll(this.params);
        return copy("", p, this.type);
    }

    public final Cypher copy(String query, Map<String, Object> params, GraphQLType type) {
        return new Cypher(query, params, type);
    }

    public String escapedQuery() {
        return query.replace("\"", "\\\"").replace("'", "\\'");
    }
}
