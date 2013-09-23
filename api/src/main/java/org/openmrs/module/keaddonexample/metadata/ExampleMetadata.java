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

package org.openmrs.module.keaddonexample.metadata;

import org.openmrs.module.kenyacore.metadata.AbstractMetadataProvider;
import org.openmrs.module.kenyacore.metadata.installer.CoreMetadataInstaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Example metadata provider
 */
@Component
public class ExampleMetadata extends AbstractMetadataProvider {

	public static class EncounterType {
		public static final String EXAMPLE = "d69dedbd-3933-4e44-8292-bea939ce980a";
	}

	public static class Form {
		public static final String EXAMPLE = "b694b1bc-2086-47dd-a4ad-ba48f9471e4b";
	}

	@Autowired
	private CoreMetadataInstaller installer;

	/**
	 * @see org.openmrs.module.kenyacore.metadata.AbstractMetadataProvider#install()
	 */
	@Override
	public void install() {
		installer.encounterType("Example encounter", "Just an example", EncounterType.EXAMPLE);

		installer.form("Example form", null, EncounterType.EXAMPLE, "1", Form.EXAMPLE);
	}
}