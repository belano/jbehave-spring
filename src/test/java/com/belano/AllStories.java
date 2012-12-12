package com.belano;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.HTML_TEMPLATE;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import java.net.URL;
import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;

import com.belano.util.Springs;
import com.belano.util.UTF8StoryLoader;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class AllStories extends JUnitStories {

    private final CrossReference xref = new CrossReference();

    public AllStories() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true).useThreads(2).useStoryTimeoutInSecs(60);
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        URL codeLocation = CodeLocations.codeLocationFromClass(embeddableClass);
        StoryReporterBuilder storyReporter = new StoryReporterBuilder().withCodeLocation(codeLocation)
                .withDefaultFormats().withFormats(CONSOLE, TXT, HTML, HTML_TEMPLATE, XML).withFailureTrace(true) //
                .withFailureTraceCompression(true) //
                .withCrossReference(xref);
        return new MostUsefulConfiguration().useStoryLoader(new UTF8StoryLoader(embeddableClass))
                .useStoryReporterBuilder(storyReporter).usePendingStepStrategy(new FailingUponPendingStep())
                .useStepMonitor(xref.getStepMonitor());
    }

    @Override
    protected List<String> storyPaths() {
        URL searchInURL = CodeLocations.codeLocationFromClass(this.getClass());
        return new StoryFinder().findPaths(searchInURL, "**/*.story", "");
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        // Locate steps either from spring context file or annotation config
        // ApplicationContext context = new SpringApplicationContextFactory("my_steps.xml").createApplicationContext();
        ApplicationContext context = Springs.createAnnotatedContextFromBasePackages("com.belano.steps");
        return new SpringStepsFactory(configuration(), context);
    }

}
