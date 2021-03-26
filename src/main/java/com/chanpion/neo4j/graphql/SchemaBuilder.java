package com.chanpion.neo4j.graphql;

import graphql.language.TypeDefinition;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Supplier;

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
        typeDefinitionRegistry.scalars().forEach((name, definition) -> {
            GraphQLScalarType scalar;
            if ("DynamicProperties".equals(name)) {
                scalar = DynamicProperties.INSTANCE;
            } else {
                scalar = GraphQLScalarType.newScalar()
                        .name(name)
                        .description(definition.getDescription() != null ? definition.getDescription().getContent() : "Scalar " + name)
                        .withDirectives( * definition.directives.filterIsInstance < GraphQLDirective > ().toTypedArray())
                        .definition(definition)
//                        .coercing(NoOpCoercing)
                        .build();
            }
        });
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
