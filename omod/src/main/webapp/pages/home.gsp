<%
	ui.decorateWith("kenyaemr", "standardPage")
%>

${ ui.decorate("kenyaui", "panel", [ heading: "Welcome" ], "This is an example page showing the example form added by this module") }

<% if (patient) { %>
${ ui.decorate("kenyaui", "panel", [ heading: "Example Forms" ], ui.includeFragment("kenyaemr", "formList", [ visit: null, forms: forms ])) }
<% } else { %>
${ ui.decorate("kenyaui", "panel", [ heading: "Example Forms" ], "Select a patient with another app to see a form list here") }
<% } %>