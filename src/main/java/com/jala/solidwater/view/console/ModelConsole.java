/*
 * @(#)CriteriaSearch.java
 *
 * Copyright (c) 2019 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.jala.solidwater.view.console;

import com.jala.search.models.Asset;
import com.jala.search.models.CriteriaSearch;
import com.jala.search.models.SearchFile;
import com.jala.solidwater.console.models.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ModelConsole class defines the structure of a ModelConsole.
 *
 * @author Raúl Choque
 * @version 0.0.1
 */
public class ModelConsole {

    private static final String EMPTY_VALUE_FOR_SET_IN_ATTIBUTES_OF_CRITERIA = "";
    private static final String FILE_NAME_COMMAND = "-fn";
    private static final String EXTENSION_COMMAND = "-ex";
    private static final String SIZE_COMMAND = "-s";
    
    /**
     * @param inputCommandLine are valid data for the search
     * @return a Asset list that were find
     */
    public List<Asset> getSearch(CommandLine inputCommandLine){
        return getAsset(inputCommandLine);
    }

    /**
     * @param validCommand is a Map with all valid commands.
     * @return a Asset list with the criteria of search.
     */
    private List<Asset> getAsset(CommandLine validCommand) {

        ArrayList<Asset> listFileSearch = new ArrayList<>();
        int postPath = validCommand.getPositionOfCommandByAcronym("-p");

        String valuePath = "";
        try {
            valuePath = validCommand.getValueCommands().get(postPath);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("error : " + e);
        }
        CriteriaSearch criteria = new CriteriaSearch(valuePath);

        /*isCommandValueNull(validCommand, criteria, FILE_NAME_COMMAND);
        isCommandValueNull(validCommand, criteria, EXTENSION_COMMAND);
        isCommandValueNull(validCommand, criteria, SIZE_COMMAND);*/
        SearchFile searchFile = new SearchFile();
        try {
            listFileSearch = new ArrayList<>(searchFile.search(criteria));
        } catch (Exception e) {
            System.out.println("error : " + e);
        }
        return listFileSearch;
    }

    private void isCommandValueNull(Map<String, String> validCommand, CriteriaSearch criteria, String command) {
        if (validCommand.get(command) == null) {
            if (command.equals(FILE_NAME_COMMAND)) {
                criteria.setFileName(EMPTY_VALUE_FOR_SET_IN_ATTIBUTES_OF_CRITERIA);
            }else if (command.equals(EXTENSION_COMMAND)) {
                criteria.setExtension(EMPTY_VALUE_FOR_SET_IN_ATTIBUTES_OF_CRITERIA);
            } else if (command.equals(SIZE_COMMAND)) {
                criteria.setSize(EMPTY_VALUE_FOR_SET_IN_ATTIBUTES_OF_CRITERIA);
            }
        } else {
            if (command.equals(FILE_NAME_COMMAND)) {
                criteria.setFileName(validCommand.get(command));
            } else if (command.equals(EXTENSION_COMMAND)) {
                criteria.setExtension(validCommand.get(command));
            } else if (command.equals(SIZE_COMMAND)) {
                criteria.setSize(validCommand.get(command));
            }
        }
    }
}
