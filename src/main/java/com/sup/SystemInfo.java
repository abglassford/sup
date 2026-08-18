package com.sup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class SystemInfo {

    @PostConstruct
    public void init() throws IOException {
        System.out.println("Hello World!");

        try {
            System.out.println(Instant.now() + "\n");
            runCommand("cat", "/etc/os-release");
            runCommand("cat", "/proc/stat");
            runCommand("cat", "/proc/meminfo");
            runCommand("cat", "/sys/class/thermal/thermal_zone0/temp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runCommand(String... args) throws IOException {
        System.out.println("Running command \"" + String.join(" ", args) + "\"");
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.redirectErrorStream(true);

        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("\n");
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("Command failed with exit code: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
