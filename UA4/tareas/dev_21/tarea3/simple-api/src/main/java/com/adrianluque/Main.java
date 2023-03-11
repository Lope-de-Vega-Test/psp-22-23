package com.adrianluque;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.ini4j.Ini;

public class Main {
    private static String version = "0.1-BETA";
    private static String help = new StringBuilder()
            .append("\n\nUsage: `java api [<option>] ... [<value>]`\n\n")
            .append("    --help\tPrints this sheet.\n")
            .append("-v, --version\tPrints the API version.\n")
            .append("-h, --hostname\tSets the hostname.\n")
            .append("-p, --port\tSets the port.\n")
            .append("-c, --conf\tSets the configuration.\n\n")
            .append("Example:\n\n")
            .append("java api [-h|--hostname] 192.168.1.25 [-p|--port]=45722\n\n")
            .append("java api [-c|--config] confFile1.ini]")
            .toString();

    public static void main(String[] args) throws Exception {

        // Options creation
        Option help = Option.builder(null)
                .argName("help")
                .longOpt("help")
                .hasArg(false)
                .required(false)
                .desc("prints help sheet")
                .build();

        Option version = Option.builder("v")
                .argName("version")
                .longOpt("version")
                .hasArg(false)
                .required(false)
                .desc("prints version")
                .build();

        Option config = Option.builder("c")
                .argName("config")
                .longOpt("config")
                .hasArg(true)
                .required(false)
                .desc("uses config for setting up the API")
                .build();

        Option hostname = Option.builder("h")
                .argName("hostname")
                .longOpt("hostname")
                .hasArg(true)
                .required(false)
                .desc("sets the hostname")
                .build();

        Option port = Option.builder("p")
                .argName("port")
                .longOpt("port")
                .hasArg(true)
                .required(false)
                .desc("sets the port")
                .valueSeparator('=')
                .build();

        // Options group setup
        Options options = new Options();
        options.addOption(help)
                .addOption(version)
                .addOption(config)
                .addOption(hostname)
                .addOption(port);

        // Command Line Parser
        CommandLineParser parser = new DefaultParser();
        // Parses de command line arguments
        try {
            // Parses the command line arguments
            CommandLine line = parser.parse(options, args);
            // Gets all the line options
            Option opts[] = line.getOptions();
            // Creates a KEY-VALUE map
            Map<String, Object> arguments = new HashMap<String, Object>();

            // Collect arguments parsed
            for (Option opt : opts) {
                arguments.put(opt.getArgName(), opt.getValue());
            }

            // Do something, do some other thing
            if (arguments.containsKey("help")) {
                System.out.println(Main.help);
                System.exit(0);
            } else if (arguments.containsKey("version") || arguments.containsKey("v")) {
                System.out.println("API Version: " + Main.version);
                System.exit(0);
            }

            // Constructing the Api
            Api api = new Api();

            if (arguments.containsKey("hostname")) {
                api.setHostname(String.valueOf(arguments.get("hostname")));
            } else if (arguments.containsKey("h")) {
                api.setHostname(String.valueOf(arguments.get("h")));
            }

            if (arguments.containsKey("port")) {
                api.setPort(Integer.parseInt(String.valueOf(arguments.get("port"))));
            } else if (arguments.containsKey("p")) {
                api.setPort(Integer.parseInt(String.valueOf(arguments.get("p"))));
            }

            if (arguments.containsKey("config")) {
                Ini iniFile = new Ini(new File(String.valueOf(arguments.get("config"))));
                api.setHostname(iniFile.get("api", "hostname"));
                api.setPort(Integer.parseInt(iniFile.get("api", "port")));
            } else if (arguments.containsKey("c")) {
                Ini iniFile = new Ini(new File(String.valueOf(arguments.get("c"))));
                api.setHostname(iniFile.get("api", "hostname"));
                api.setPort(Integer.parseInt(iniFile.get("api", "port")));
            }

            api.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
