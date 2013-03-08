/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.keaddonexample;


import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.kenyaemr.KenyaEmr;
import org.openmrs.module.kenyaemr.MetadataManager;
import org.openmrs.module.kenyaemr.form.FormConfig;
import org.openmrs.module.kenyaemr.form.FormManager;

import java.io.InputStream;

/**
 * This class contains the logic that is run every time this module is either started or stopped.
 */
public class KenyaEMRAddonExampleActivator implements ModuleActivator {
	
	protected static final Log log = LogFactory.getLog(KenyaEMRAddonExampleActivator.class);

	protected static final String PACKAGES_FILENAME = "packages.xml";
		
	/**
	 * @see ModuleActivator#willRefreshContext()
	 */
	public void willRefreshContext() {
		log.info("Refreshing Kenya EMR Add-on Example Module");
	}
	
	/**
	 * @see ModuleActivator#contextRefreshed()
	 */
	public void contextRefreshed() {
		log.info("Kenya EMR Add-on Example Module refreshed");
	}
	
	/**
	 * @see ModuleActivator#willStart()
	 */
	public void willStart() {
		log.warn("Starting Kenya EMR Add-on Example Module");
	}
	
	/**
	 * @see ModuleActivator#started()
	 */
	public void started() {
		try {
			/**
			 * Loads a metadata package from this module into the Kenya EMR. This example package contains one
			 * HTML form only.
			 */
			InputStream stream = getClass().getClassLoader().getResourceAsStream(PACKAGES_FILENAME);

			KenyaEmr.getInstance().getMetadataManager().loadPackagesFromXML(stream, getClass().getClassLoader());

			/**
			 * Registers an example form as a "once per visit" form which is available in the medical encounter and
			 * medical chart apps.
			 */
			KenyaEmr.getInstance().getFormManager().registerForm(
					ExampleConstants.EXAMPLE_ADDON_FORM_UUID,
					FormConfig.Frequency.VISIT,
					new String[] { "kenyaemr.medicalEncounter", "kenyaemr.medicalChart" }
			);
		}
		catch (Exception ex) {
			throw new RuntimeException("Failed to setup initial data", ex);
		}

		log.warn("Kenya EMR Add-on Example Module started");
	}
	
	/**
	 * @see ModuleActivator#willStop()
	 */
	public void willStop() {
		log.info("Stopping Kenya EMR Add-on Example Module");
	}
	
	/**
	 * @see ModuleActivator#stopped()
	 */
	public void stopped() {
		log.info("Kenya EMR Add-on Example Module stopped");
	}
}