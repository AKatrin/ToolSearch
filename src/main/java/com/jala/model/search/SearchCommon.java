/*
 * @(#) SearchCommon.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.jala.model.search;

import com.jala.model.criteria.CriteriaSearch;
import com.jala.model.search.asset.Asset;
import com.jala.utils.Logs;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchCommon.
 * Search file with common parameters.
 *
 * @author Regis Humana.
 * @version 0.0.1
 */
public class SearchCommon extends SearchBasic {

	/**
	 * It creates to follow up the instruction of the class.
	 */
	private Logger log = Logs.getInstance().getLog();

	/**
	 * Criteria of the file.
	 */
	private CriteriaSearch criteria;

	/**
	 * List of Asset for result.
	 */
	private List<Asset> result;

	/**
	 * This constructor receive the criteria, for using in the search.
	 *
	 * @param criteria for common search.
	 */
	public SearchCommon(CriteriaSearch criteria) {
		log.info("Searching on " + criteria.getPath());
		this.criteria = criteria;
	}

	/**
	 * Receive the list of files founded in the path, and deliver a list with the filters that was made for the
	 * customer.
	 *
	 * @return List<Asset> result.
	 */
	public List<Asset> search() {
		List<Asset> preview = super.search(criteria);
		result = new ArrayList<>();
		for (int i = 0; i < preview.size(); i++) {
			if ((criteria.getHidden() == TernaryBooleanEnum.OnlyTrue) && ! (preview.get(i).isHidden())) {
				continue;
			}
			if ((criteria.getHidden() == TernaryBooleanEnum.OnlyFalse) && (preview.get(i).isHidden())) {
				continue;
			}
			if ((! criteria.getCreationDateFrom().isEmpty()) && ! criteria.getCreationDateTo().isEmpty()) {
				if ((Date.valueOf(preview.get(i).getCreationDate()).before(Date.valueOf(criteria.getCreationDateFrom()))) || (Date.valueOf(preview.get(i).getCreationDate()).after(Date.valueOf(criteria.getCreationDateTo())))) {
					continue;
				}
			}
			if ((! criteria.getModificationDateFrom().isEmpty()) && ! criteria.getModificationDateTo().isEmpty()) {
				if ((Date.valueOf(preview.get(i).getModificationDate()).before(Date.valueOf(criteria.getModificationDateFrom()))) || (Date.valueOf(preview.get(i).getModificationDate())).after(Date.valueOf(criteria.getModificationDateTo()))) {
					continue;
				}
			}
			if ((! criteria.getLastDateFrom().isEmpty()) && ! criteria.getLastDateTo().isEmpty()) {
				if ((Date.valueOf(preview.get(i).getLastDate()).before(Date.valueOf(criteria.getLastDateFrom()))) || (Date.valueOf(preview.get(i).getLastDate()).after(Date.valueOf(criteria.getLastDateTo())))) {
					continue;
				}
			}
			if ((! criteria.getOwner().isEmpty()) && (! preview.get(i).getOwner().contains(criteria.getOwner()))) {
				continue;
			}
			if ((! criteria.getSize().isEmpty())) {
				if (criteria.isSizeCompareOption() && ! (Double.parseDouble(criteria.getSize()) > Double.parseDouble(preview.get(i).getSize()))) {
					continue;
				}
				if (! criteria.isSizeCompareOption() && ! (Double.parseDouble(criteria.getSize()) <= Double.parseDouble(preview.get(i).getSize()))) {
					continue;
				}
			}
			if ((! criteria.getFileName().isEmpty()) && (! preview.get(i).getFileName().contains(criteria.getFileName()))) {
				continue;
			}
			if ((! criteria.getExtension().isEmpty()) && (! FilenameUtils.getExtension(preview.get(i).getFileName()).equals(criteria.getExtension()))) {
				continue;
			}
			if ((criteria.getReadOnly() == TernaryBooleanEnum.OnlyTrue) && ! (preview.get(i).isReadOnly())) {
				continue;
			}
			if ((criteria.getReadOnly() == TernaryBooleanEnum.OnlyFalse) && (preview.get(i).isReadOnly())) {
				continue;
			}
			result.add(preview.get(i));
		}
		return result;
	}
}
