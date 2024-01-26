# Integration tests

## Description

This module intended for integration tests.

- When tests are running flyway are applying all migration from project. Avoid adding migration to
  test root.
- Extend your class from the base class of the layer under test.
  For exaple: `NewControllerTest extends ControllerIntegrationTestBase`,
  `NewServiceControllerTest extends ServiceIntegrationTestBase`

## Overview

- [Description](#structure)
- [Structure](#structure)
- [Method Naming](#method-naming-convension)
- [Writing tests](#writing-tests)
- [Using SQL scripts](#using-sql-scripts)
- [Creating SQL scripts](#creating-sql-scripts)
- [Constants](#constants)
- [Data generation](#data-generation)
- [Sample](#sample)

## Structure

  ```
  ├── java
  │   └── ua
  │       └── com
  │           ├── config - Test context configurations
  │           ├── constant - Constans for reusing in tests
  │           ├── datagenerator - Classes which returning Entity and Dto for testing
  │           └── quizservice
  │               ├── controller -- Tests for controller
  │               ├── mapper -- Tests for customized mappers
  │               ├── service -- Test for services
  │               └── IntegrationTestBase.java - Base class for all inegration tests
  │
  ├── resources
  │   └── db - Directory for scripts with ONLY data 
  └── README.md
  ```

## Run integration tests

- `mvn failsafe:integration-test` for run only **integration** tests.
- `mvn verify` for run **integration** and **unit** tests.

## Method naming convension

- **Integration Test** classes must include postfix `IT`. For example: `SomeClassIT`.

- **Test methods** must follow the following naming convention:

  `void methodName_expectedBehavior_stateUnderTest`

For example:

```java
  class Test() extends IntegrationTestBase {

  @Test
  void toEntity_shouldReturnTestTour_whenInputIsValidDto() {
    final TestTour expected = generateEntity();

    final TestTourDetailedDto dto = generateDto();
    final TestTour actual = mapper.toEntity(dto);

    assertThat(actual, equalTo(expected));
  }
}
```

## Writing tests

- Trying to avoid magic strings and
  numbers. [read detailed](https://deviq.com/antipatterns/magic-strings)
- If you are using something data more than one time, make it as constant.
  ```java
  class Test() extends IntegrationTestBase {
  
    /** BAD SOLUTION **/
    @Test
    void updateFromDto_shouldUpdateEntity_whenInputIsValidTestTourDetailedDto() {
        final TestTour expected = generateEntity("new title");

        final TestTour entity = generateEntity();
        final TestTourDetailedDto dto = generateDto("new title");

        mapper.updateFromDto(dto, entity);

        assertThat(entity, equalTo(expected));
    }
  
    /** GOOD SOLUTION **/
    @Test
      void updateFromDto_shouldUpdateEntity_whenInputIsValidTestTourDetailedDto() {
          final TestTour expected = generateEntity(NEW_TITLE);

          final TestTour entity = generateEntity();
          final TestTourDetailedDto dto = generateDto(NEW_TITLE);

          mapper.updateFromDto(dto, entity);

          assertThat(entity, equalTo(expected));
      }
  }
  ```

## Using sql scripts

To add some data into database use SQL scripts.
For add data use `@Sql` annotation at method or class.

```java
class ControllerTest extends ControllerIntegrationTestBase {

  @Sql(TEST_TOUR_SQL)
  @Test
  void create_shouldReturnTestTourTaskDtoAndCode201_whenInputDtoIsValid()
      throws Exception {
    final QuizTestTourTaskDto dto = generateDto();
    final String content = mockMvc.perform(post(BASE_URL)
            .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString();
    final TestTourTaskDto actual = objectMapper.readValue(content, QuizTestTourTaskDto.class);

    assertThat(actual, equalTo(dto));
  }
}
```

## Creating SQL scripts

Try to avoid fat scripts! One script must add only the necessary data for exact test Class.
For example, you want to test UserController. For this task, you need User and transitive data, such
as UserDetails. So script must contain only `insert into User` and `insert into UserDetails`

## Constants

For simplify testing use constants. For sql script you should add path as constant. For simply
describe what data provides in the script add javaDoc with listing of Object links.
See: [TestDataConstant](java/ua/com/constant/TestDataConstant.java)

```java
/**
 * Class for keeping Paths to sql scripts. 
 * @implNote To add new Constant follow the pattern: {SQL_PATH + "scriptName" + POSTFIX}
 * @author Artyom Kondratenko
 * @since 12/16/23
 */

public class TestDataConstant {

  private static final String SQL_PATH = "classpath:/db/";
  private static final String POSTFIX = ".sql";

  /**
   * Sql script for testing TestTour. Included data: {@link Quiz}, {@link Vacancy},
   * {@link TestTour}, {@link QuizTestTourTask}
   */
  public static final String TEST_TOUR_SQL = SQL_PATH + "insertTestTourData" + POSTFIX;
}
```

## Data generation

To get Objects in tests use data generators with needed methods such as `generatoR()`
and `generate Entity()`. In the generators make methods where objects are creating using builder.
See: [TestTestTourDataGenerator](java/ua/com/datagenerator/TestTestTourDataGenerator.java)

## Sample

```java
package ua.com.quizservice.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.com.constant.TestDataConstant.TEST_TOUR_SQL;
import static ua.com.datagenerator.TestQuizTestTourTaskDataGenerator.QUIZ_TEST_TOUR_TASK_DEFAULT_ID;
import static ua.com.datagenerator.TestQuizTestTourTaskDataGenerator.generateDto;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import ua.com.quizservice.dto.testtour.task.QuizTestTourTaskDto;
import ua.com.quizservice.dto.testtour.task.TestTourTaskDto;

/**
 * @author Artyom Kondratenko
 * @since 12/15/23
 */

class TestTourTaskControllerTest extends ControllerIntegrationTestBase {

  private final String BASE_URL = "/api/v1/testTourTask";

  @Autowired
  DataSource dataSource;

  @Sql(TEST_TOUR_SQL)
  @Test
  void getById_shouldReturnTestTourTaskDtoAndCode200_whenFoundById()
      throws Exception {
    final TestTourTaskDto expected = generateDto();

    final String content = mockMvc.perform(get(BASE_URL + "/" + QUIZ_TEST_TOUR_TASK_DEFAULT_ID))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();
    final TestTourTaskDto dto = objectMapper.readValue(content, TestTourTaskDto.class);

    assertThat(dto, instanceOf(QuizTestTourTaskDto.class));
    assertThat(dto, equalTo(expected));
  }

  @Sql(TEST_TOUR_SQL)
  @Test
  void create_shouldReturnTestTourTaskDtoAndCode201_whenInputDtoIsValid()
      throws Exception {
    final QuizTestTourTaskDto dto = generateDto();
    final String content = mockMvc.perform(post(BASE_URL)
            .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString();
    final TestTourTaskDto actual = objectMapper.readValue(content, QuizTestTourTaskDto.class);

    assertThat(actual, equalTo(dto));
  }

  @Sql(TEST_TOUR_SQL)
  @Test
  void updateById_shouldReturnUpdatedDtoAndCode200_whenTestTourTaskFoundAndInputDtoIsValid()
      throws Exception {
    //todo remove magic String
    final QuizTestTourTaskDto dto = generateDto("new title");

    final String content = mockMvc.perform(put(BASE_URL + "/" + QUIZ_TEST_TOUR_TASK_DEFAULT_ID)
            .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    final QuizTestTourTaskDto actual = objectMapper.readValue(content,
        QuizTestTourTaskDto.class);
    assertThat(dto, equalTo(actual));
  }

  @Sql(TEST_TOUR_SQL)
  @Test
  void deleteById_shouldReturnCode204() throws Exception {
    mockMvc.perform(delete(BASE_URL + "/" + QUIZ_TEST_TOUR_TASK_DEFAULT_ID))
        .andExpect(status().isNoContent());
  }

}
```
