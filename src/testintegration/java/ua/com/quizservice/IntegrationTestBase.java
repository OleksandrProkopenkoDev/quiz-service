package ua.com.quizservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ua.com.config.MongoContainerConfig;
import ua.com.config.PostgresContainerConfig;

/**
 * Base class for all Integration test classes.
 *
 * @author Artyom Kondratenko
 * @since 1/10/24
 */
@Transactional
@ActiveProfiles("IT")
@Import({PostgresContainerConfig.class, MongoContainerConfig.class})
@SpringBootTest(classes = QuizServiceApplication.class)
public abstract class IntegrationTestBase {}
