package org.tsapko.jtt;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.github.rvesse.airline.SingleCommand;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

@Command(name = "java-transport-checker", description = "Utility should be used to check java transport")
public class JavaTransportChecker {

    @Option(name = {"-c", "--cluster-name"}, description = "An option that defines elasticsearch cluster name")
    private String clusterName = "";

    @Option(name = {"-h", "--host"}, description = "An option that defines elasticsearch node address")
    private String hostname = "localhost";

    @Option(name = {"-p", "--port"}, description = "An option that defines elasticsearch node port")
    private int port = 9300;

    @Arguments(description = "Additional arguments")
    private List<String> args;

    public static void main(String[] args) {
        SingleCommand<JavaTransportChecker> parser = SingleCommand.singleCommand(JavaTransportChecker.class);
        JavaTransportChecker cmd = parser.parse(args);
        cmd.run();
    }

    private void run() {
        System.out.println("cluster.name is " + this.clusterName);
        System.out.println("hostname is " + this.hostname);
        System.out.println("port is " + this.port);
        if (args != null)
            System.out.println("Arguments were " + StringUtils.join(args, ","));

        Settings settings = Settings.builder()
                .put("cluster.name", this.clusterName).build();

        InetAddress ia = null;
        try {
            ia = InetAddress.getByName(this.hostname);
        } catch (UnknownHostException ue) {
            System.out.println("cannot find host: " + this.clusterName + "err: " + ue.toString());
            return;
        }


        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(ia, this.port));

        ClusterAdminClient clusterAdminClient = client.admin().cluster();
        ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get();
        System.out.println("cluster status:" + healths.toString());

        // on shutdown
        client.close();
    }
}
