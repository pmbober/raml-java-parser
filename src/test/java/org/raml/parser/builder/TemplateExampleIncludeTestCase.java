/*
 * Copyright 2013 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.parser.builder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.raml.model.ActionType.GET;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.raml.model.Raml;

public class TemplateExampleIncludeTestCase extends AbstractRamlTestCase
{

    private static final String ramlSource = "org/raml/types/template-example-include.yaml";
    private static final String includedExample = "org/raml/types/toys-example.json";
    private static Raml raml;

    @BeforeClass
    public static void init()
    {
        raml = parseRaml(ramlSource);
    }

    @Test
    public void resourceTypeExampleInclude() throws IOException
    {
    	String example = IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream(includedExample));
    	assertThat(raml.getResources().get("/toys").getAction("get").getResponses().get("200").getBody().get("application/json").getExample(),
    			is(example));

    }

  
    @Test
    public void validate()
    {
        validateRamlNoErrors(ramlSource);
    }
}
