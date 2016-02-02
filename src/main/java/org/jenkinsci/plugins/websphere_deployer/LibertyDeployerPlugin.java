package org.jenkinsci.plugins.websphere_deployer;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;
import hudson.util.FormValidation;
import hudson.util.Scrambler;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author Greg Peters
 */
public class LibertyDeployerPlugin extends Notifier {

    private final String ipAddress;
    private final String port;
    private final String username;
    private final String consolePassword;
    private final String clientTrustFile;
    private final String clientTrustPassword;
    private final String artifacts;

    @DataBoundConstructor
    public LibertyDeployerPlugin(String ipAddress,
                                 String port,
                                 String username,
                                 String consolePassword,
                                 String clientTrustFile,
                                 String clientTrustPassword,
                                 String artifacts) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.username = username;
        this.consolePassword = Scrambler.scramble(consolePassword);
        this.clientTrustFile = clientTrustFile;
        this.clientTrustPassword = Scrambler.scramble(clientTrustPassword);
        this.artifacts = artifacts;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getConsolePassword() {
        return Scrambler.descramble(consolePassword);
    }

    public String getClientTrustFile() {
        return clientTrustFile;
    }

    public String getClientTrustPassword() {
        return Scrambler.descramble(clientTrustPassword);
    }

    public String getArtifacts() {
        return artifacts;
    }

    
    
    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
//        if(build.getResult().equals(Result.SUCCESS)) {
//            LibertyDeploymentService service = new LibertyDeploymentService();
//            try {
//                connect(listener,service);
//                for(FilePath path:gatherArtifactPaths(build, listener)) {
//                    Artifact artifact = createArtifact(path,listener);
//                    stopArtifact(artifact.getAppName(),listener,service);
//                    uninstallArtifact(artifact.getAppName(),listener,service);
//                    deployArtifact(artifact,listener,service);
//                    Thread.sleep(2000); //wait 2 seconds for deployment to settle
//                    startArtifact(artifact.getAppName(),listener,service);
//                }
//            } catch (Exception e) {
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                PrintStream p = new PrintStream(out);
//                e.printStackTrace(p);
//                listener.getLogger().println("Error deploying to IBM WebSphere Liberty Profile: "+new String(out.toByteArray()));
//                build.setResult(Result.FAILURE);
//            } finally {
//                try {
//                    disconnect(listener,service);
//                } catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return true;
    }

//    private Artifact createArtifact(FilePath path,BuildListener listener) {
//        Artifact artifact = new Artifact();
//        if(path.getRemote().endsWith(".ear")) {
//            artifact.setType(Artifact.TYPE_EAR);
//        } else if(path.getRemote().endsWith(".war")) {
//            artifact.setType(Artifact.TYPE_WAR);
//        } else if(path.getRemote().endsWith(".rar")) {
//            artifact.setType(Artifact.TYPE_RAR);
//        } else if(path.getRemote().endsWith(".jar")) {
//            artifact.setType(Artifact.TYPE_JAR);
//        }
//        artifact.setSourcePath(new File(path.getRemote()));
//        artifact.setAppName(path.getBaseName());
//        return artifact;
//    }

//    private void connect(BuildListener listener,LibertyDeploymentService service) throws Exception {
//        listener.getLogger().println("Connecting to IBM WebSphere Liberty Profile...");
//        service.setHost(getIpAddress());
//        service.setPort(getPort());
//        service.setUsername(getUsername());
//        service.setPassword(getConsolePassword());
//        service.setTrustStoreLocation(new File(getClientTrustFile()));
//        service.setTrustStorePassword(getClientTrustPassword());
//        service.connect();
//    }

//    private void disconnect(BuildListener listener,LibertyDeploymentService service) throws Exception {
//        listener.getLogger().println("Disconnecting from IBM WebSphere Liberty Profile...");
//        service.disconnect();
//    }
//
//    private void stopArtifact(String appName,BuildListener listener,LibertyDeploymentService service) throws Exception {
//        if(service.isArtifactInstalled(appName)) {
//            listener.getLogger().println("Stopping Old Application '"+appName+"'...");
//            service.stopArtifact(appName,listener,false);
//        }
//    }
//
//    private void uninstallArtifact(String appName,BuildListener listener,LibertyDeploymentService service) throws Exception {
//        if(service.isArtifactInstalled(appName)) {
//            listener.getLogger().println("Uninstalling Old Application '"+appName+"'...");
//            service.uninstallArtifact(appName,listener,false);
//        }
//    }
//
//    private void deployArtifact(Artifact artifact,BuildListener listener,LibertyDeploymentService service) throws Exception {
//        listener.getLogger().println("Deploying '"+artifact.getAppName()+"' to IBM WebSphere Liberty Profile");
//        service.installArtifact(artifact, new HashMap<String, Object>(),listener,false);
//    }
//
//    private void startArtifact(String appName,BuildListener listener,LibertyDeploymentService service) throws Exception {
//        listener.getLogger().println("Starting Application '"+appName+"'...");
//        service.startArtifact(appName,listener,false);
//    }
//
//    private FilePath[] gatherArtifactPaths(AbstractBuild build,BuildListener listener) throws Exception {
//        FilePath[] paths = build.getWorkspace().getParent().list(getArtifacts());
//        if(paths.length == 0) {
//            listener.getLogger().println("No deployable artifacts found in path: "+build.getWorkspace().getParent()+ File.separator+getArtifacts());
//            throw new Exception("No deployable artifacts found!");
//        } else {
//            listener.getLogger().println("The following artifacts will be deployed in this order...");
//            listener.getLogger().println("-------------------------------------------");
//            for(FilePath path:paths) {
//                listener.getLogger().println(path.getName());
//            }
//            listener.getLogger().println("-------------------------------------------");
//        }
//        return paths;
//    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }
    
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {


        public DescriptorImpl() {
            load();
        }

        public FormValidation doTestConnection(@QueryParameter("ipAddress")String ipAddress,
                                               @QueryParameter("port")String port,
                                               @QueryParameter("username")String username,
                                               @QueryParameter("consolePassword")String password,
                                               @QueryParameter("clientTrustFile")String clientTrustFile,
                                               @QueryParameter("clientTrustPassword")String clientTrustPassword) throws IOException, ServletException {
            
            throw new UnsupportedOperationException("Not supported yet.");
            
//            LibertyDeploymentService service = new LibertyDeploymentService();
//            System.out.println("ClassLoader: "+getClass().getClassLoader());
//            System.out.println("Parent ClassLoader: "+getClass().getClassLoader().getParent());
//            System.out.println("System ClassLoader: "+ClassLoader.getSystemClassLoader());
//            if(!service.isAvailable()) {
//                String destination = System.getProperty("user.home")+File.separator+".jenkins"+File.separator+"plugins"+File.separator+"websphere-deployer"+File.separator+"WEB-INF"+File.separator+"lib"+File.separator;
//                return FormValidation.warning("Cannot find the required IBM WebSphere Liberty jar files in '"+destination+"'. Please copy them from IBM WebSphere Liberty (see plugin documentation)");
//            }
//            try {
//                service.setHost(ipAddress);
//                service.setPort(port);
//                service.setUsername(username);
//                service.setPassword(password);
//                service.setTrustStoreLocation(new File(clientTrustFile));
//                service.setTrustStorePassword(clientTrustPassword);
//                service.connect();
//                return FormValidation.ok("Connection Successful!");
//            } catch (Exception e) {
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                PrintStream p = new PrintStream(out);
//                e.printStackTrace(p);
//                return FormValidation.error("Connection failed: " + new String(out.toByteArray()));
//            } finally {
//                service.disconnect();
//            }

        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Deploy To IBM WebSphere Liberty Profile";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            save();
            return super.configure(req,formData);
        }
    }
}
