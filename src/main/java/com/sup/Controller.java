package com.sup;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    private final SystemInfo sysInfo;

    public Controller(SystemInfo sysInfo) {
        this.sysInfo = sysInfo;
    }

    @GetMapping("/info")
    public String getStuff() throws IOException {
        return sysInfo.getInfo();
    }
}
