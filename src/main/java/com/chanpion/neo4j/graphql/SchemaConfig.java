package com.chanpion.neo4j.graphql;

import java.util.Collections;
import java.util.List;

/**
 * @author April Chen
 * @date 2021/3/24 14:21
 */
public class SchemaConfig {
    private CRUDConfig query;
    private CRUDConfig mutation;
    /**
     * if true, the top level fields of the Query-type will be capitalized
     */
    private Boolean capitalizeQueryFields;

    public SchemaConfig() {
        this(new CRUDConfig(), new CRUDConfig(), false);
    }

    public SchemaConfig(CRUDConfig query, CRUDConfig mutation, Boolean capitalizeQueryFields) {
        this.query = query;
        this.mutation = mutation;
        this.capitalizeQueryFields = capitalizeQueryFields;
    }

    static class CRUDConfig {
        Boolean enabled;
        List<String> exclude;

        public CRUDConfig() {
            this(true, Collections.emptyList());
        }

        public CRUDConfig(Boolean enabled, List<String> exclude) {
            this.enabled = enabled;
            this.exclude = exclude;
        }
    }
}
