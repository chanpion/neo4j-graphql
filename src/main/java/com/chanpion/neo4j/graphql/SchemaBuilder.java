package com.chanpion.neo4j.graphql;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.net.URL;

/**
 * @author April Chen
 * @date 2021/3/24 14:33
 */
public class SchemaBuilder {

    public static GraphQLSchema buildSchema(String sdl, SchemaConfig config, DataFetchingInterceptor dataFetchingInterceptor) {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(sdl);
        return buildSchema(typeDefinitionRegistry, config, dataFetchingInterceptor);
    }

    public static GraphQLSchema buildSchema(TypeDefinitionRegistry typeDefinitionRegistry, SchemaConfig config, DataFetchingInterceptor dataFetchingInterceptor) {
        TypeDefinitionRegistry enhancedRegistry = typeDefinitionRegistry.merge(getNeo4jEnhancements());
        ensureRootQueryTypeExists(enhancedRegistry);
        RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring();
        return null;
    }

    private static void ensureRootQueryTypeExists(TypeDefinitionRegistry enhancedRegistry) {
    }

    private static TypeDefinitionRegistry getNeo4jEnhancements() {
        URL neo4jTypes = SchemaBuilder.class.getResource("/neo4j_types.graphql");
        URL libDirectives = SchemaBuilder.class.getResource("/lib_directives.graphql");
        String directivesSdl = TextUtils.readText(neo4jTypes) + TextUtils.readText(libDirectives);
        return null;
    }


}
