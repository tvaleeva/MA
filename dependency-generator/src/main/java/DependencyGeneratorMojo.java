import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * @author alexanderdemin.
 */
@Mojo(name = "generate-dependency", defaultPhase = LifecyclePhase.COMPILE)
public class DependencyGeneratorMojo extends AbstractMojo {

    @Component
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        Log log = getLog();
        log.info("Hi!");
        log.info(project.getArtifactId());
        for (Object dep : project.getDependencies()){
            Dependency dependency = (Dependency) dep;
            log.info(dependency.getGroupId() + ":" + dependency.getArtifactId() + "=" + dependency.getVersion());
        }
    }

}
