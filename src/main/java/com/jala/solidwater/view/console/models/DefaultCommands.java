package com.jala.solidwater.view.console.models;

import java.util.ArrayList;
import java.util.List;

public class DefaultCommands {
    private List<Command> defaultCommands;

    public List<Command> getDefaultCommands() {
        return defaultCommands;
    }

    public List<Command> addDefaultCommands() {
        defaultCommands = new ArrayList<>();
        Command commandPath = new Command("-p", "path", "Path of the file or directory");
        Command commandFileName = new Command("-fn", "fileName", "Name of the file");
        Command commandExtension = new Command("-e", "extension", "Extension of the file");
        Command commandSize = new Command("-s", "size", "Size of the file");
        defaultCommands.add(commandPath);
        defaultCommands.add(commandFileName);
        defaultCommands.add(commandExtension);
        defaultCommands.add(commandSize);
        return defaultCommands;
    }
}