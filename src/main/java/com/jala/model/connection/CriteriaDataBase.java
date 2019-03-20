/*
 * @(#) CriteriaDataBase.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.jala.model.connection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jala.data.QueryGeneral;
import com.jala.model.criteria.CriteriaName;
import com.jala.model.criteria.CriteriaSearch;
import com.jala.utils.Logs;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriteriaDataBase {
	/** It creates to follow up the instruction of the class*/
	private Logger log = Logs.getInstance().getLog();
	private QueryGeneral queryGeneral;
	public CriteriaDataBase() {
		queryGeneral = new QueryGeneral();
	}

	public void saveCriteria(CriteriaSearch criteria) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(criteria);
			queryGeneral.addCriteria(json);
		} catch (JsonProcessingException event) {
			log.error("Error in : " + event.getMessage(), event);
		} catch (SQLException event) {
			log.error("Error in : " + event.getMessage(), event);
		}
	}
	public List<CriteriaName> loadCriteria() {
		ObjectMapper mapper = new ObjectMapper();
		List<CriteriaName> result = new ArrayList<>();
		try {
			CriteriaSearch criteriaSearch;
			ResultSet resultSet = queryGeneral.obtainDB();
			while (resultSet.next()) {
				criteriaSearch = (mapper.readValue(resultSet.getString("criteria"), CriteriaSearch.class));
				CriteriaName criteriaName = new CriteriaName(criteriaSearch.getNameCriteria(), criteriaSearch.getDateCreation(), resultSet.getInt("id"));
				result.add(criteriaName);
				System.out.println(resultSet.getInt("id"));
			}
		} catch (JsonMappingException event) {
			log.error("Error in : " + event.getMessage(), event);
		} catch (JsonParseException event) {
			log.error("Error in : " + event.getMessage(), event);
		} catch (IOException event) {
			log.error("Error in : " + event.getMessage(), event);
		} catch (SQLException event) {
			log.error("Error in : " + event.getMessage(), event);
		}
		return result;
	}
	public CriteriaSearch getCriteria(int id) {
		ObjectMapper mapper = new ObjectMapper();
		CriteriaSearch criteriaSearch = new CriteriaSearch(null);
		try {


			ResultSet resultSet = queryGeneral.selectCriteria(id);
			criteriaSearch = (mapper.readValue(resultSet.getString(id), CriteriaSearch.class));




		} catch (JsonMappingException event) {
			log.error("Error in : " + event.getMessage(), event);
		} catch (JsonParseException event) {
			log.error("Error in : " + event.getMessage(), event);
		} catch (IOException event) {
			log.error("Error in : " + event.getMessage(), event);
		} catch (SQLException event) {
			log.error("Error in : " + event.getMessage(), event);
		}
		return criteriaSearch;
	}
}
