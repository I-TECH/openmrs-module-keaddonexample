<%
	ui.decorateWith("kenyaemr", "standardPage")

	def simpleForms = forms.collect { kenyaEmrUi.simpleForm(it, ui) }
%>

${ ui.decorate("kenyaemr", "panel", [ heading: "Welcome" ], "This is an example page showing the example form added by this module") }

<% if (patient) { %>
${ ui.decorate("kenyaemr", "panel", [ heading: "Example Forms" ], ui.includeFragment("kenyaemr", "formList", [ visit: null, forms: simpleForms ])) }
<% } else { %>
${ ui.decorate("kenyaemr", "panel", [ heading: "Example Forms" ], "Select a patient with another app to see a form list here") }
<% } %>