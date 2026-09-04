package com.sup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;

import org.springframework.stereotype.Component;

@Component
public class SystemInfo {

    private String info;

    public String getInfo() throws IOException {
        System.out.println("Hello World!");

        try {
            System.out.println(Instant.now() + "\n");
            // TODO: Parse system info better for better logging
            StringBuilder finalOutput = new StringBuilder();

            finalOutput
                    .append(runCommand("cat", "/etc/os-release")).append(System.lineSeparator())
                    .append(runCommand("cat", "/proc/stat")).append(System.lineSeparator())
                    .append(runCommand("cat", "/proc/meminfo")).append(System.lineSeparator());

            this.info = finalOutput.toString().trim();

            return this.info;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String runCommand(String... args) throws IOException {
        System.out.println("Running command \"" + String.join(" ", args) + "\"");
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.redirectErrorStream(true);

        Process process = pb.start();

        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("Command failed with exit code: " + exitCode);
            }

            return output.toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }


    }
}
