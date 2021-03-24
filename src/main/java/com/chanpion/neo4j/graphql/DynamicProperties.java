package com.chanpion.neo4j.graphql;

import graphql.Assert;
import graphql.language.*;
import graphql.schema.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author April Chen
 * @date 2021/3/24 16:20
 */
public class DynamicProperties {
    GraphQLScalarType INSTANCE = GraphQLScalarType.newScalar()
            .name("DynamicProperties")
            .coercing(new Coercing<Object, Object>() {
                @Override
                public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
                    return dataFetcherResult;
                }

                @Override
                public Object parseValue(Object input) throws CoercingParseValueException {
                    return input;
                }

                @Override
                public Object parseLiteral(Object input) throws CoercingParseLiteralException {
                    return parse(input, Collections.emptyMap());
                }
            })

            .build();

    private Object parse(Object input, Map<Object, Object> variables) throws CoercingParseLiteralException {
        if (!(input instanceof Value)) {
            throw new CoercingParseLiteralException("Expected AST type 'StringValue' but was " + input.getClass());
        } else if (input instanceof NullValue) {
            return null;
        } else if (input instanceof FloatValue) {
            return ((FloatValue) input).getValue();
        } else if (input instanceof StringValue) {
            return ((StringValue) input).getValue();
        } else if (input instanceof IntValue) {
            return ((IntValue) input).getValue();
        } else if (input instanceof BooleanValue) {
            return ((BooleanValue) input).isValue();
        } else if (input instanceof EnumValue) {
            return ((EnumValue) input).getName();
        } else if (input instanceof VariableReference) {
            String varName = ((VariableReference) input).getName();
            return variables.get(varName);
        } else {

            if (input instanceof ArrayValue) {
                List<?> values = ((ArrayValue) input).getValues();
                values = values.stream().map(v -> parse(v, variables)).collect(Collectors.toList());
                return values;
            } else if (input instanceof ObjectValue) {
                throw new IllegalArgumentException("deep structures not supported for dynamic properties");
            } else {
                Assert.assertShouldNeverHappen("We have covered all Value types");
            }
        }
        return null;
    }
}