package com.example.demo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ProxyCommand {
    @ShellMethod("Start the caching proxy server")
    public String startProxy(@ShellOption("--port") int port, @ShellOption("--origin") String origin) {
        return "Proxy server would start on port " + port + " and forward to " + origin;
    }

    @ShellMethod("Clear the cache")
    public String clearCache() {
        return "Cache would be cleared!";
    }
}
