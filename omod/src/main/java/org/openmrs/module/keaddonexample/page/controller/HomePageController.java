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

package org.openmrs.module.keaddonexample.page.controller;

import org.openmrs.Form;
import org.openmrs.Patient;
import org.openmrs.module.keaddonexample.ExampleConstants;
import org.openmrs.module.kenyacore.metadata.MetadataUtils;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Home page controller
 */
@AppPage(ExampleConstants.APP_EXAMPLE)
public class HomePageController {
	
	public void controller(@RequestParam(required=false, value="patientId") Patient patient,
						   PageModel model,
						   UiUtils ui) {

		model.addAttribute("patient", patient);

		Form exampleForm = MetadataUtils.getForm(ExampleConstants.EXAMPLE_ADDON_FORM_UUID);
        Form adherenceForm = MetadataUtils.getForm(ExampleConstants.ADHERENCE_FORM_UUID);

		//model.addAttribute("forms", Collections.singletonList(ui.simplifyObject(exampleForm)));



        List<SimpleObject> forms = new ArrayList<SimpleObject>();
        forms.add(ui.simplifyObject(exampleForm));
        forms.add(ui.simplifyObject(adherenceForm));

        //List<SimpleObject> oneTimeForms = new ArrayList<SimpleObject>();

        //List<FormDescriptor> oneTimeFormDescriptors = emr.getFormManager().getFormsForPatient(thisApp, patient);

        //for (FormDescriptor formDescriptor : oneTimeFormDescriptors) {
        //    Form form = formDescriptor.getTarget();
        //    oneTimeForms.add(ui.simplifyObject(form));
       // }
        //model.addAttribute("oneTimeForms", oneTimeForms);
        model.addAttribute("forms", forms);

	}
}