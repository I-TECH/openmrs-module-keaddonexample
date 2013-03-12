Kenya EMR Example Add-on Module
===============================

This is an example of a add-on module for the Kenya EMR which shows how to add various things to the EMR.

Adding a new app
----------------

It uses the [appframework module](https://wiki.openmrs.org/display/docs/App+Framework+Module) to define an app of its own.
The Kenya EMR module will show this app's icon on its homepage along with its own apps.

To register an app, the module defines an app descriptor bean in its *webModuleApplicationContext.xml* file, e.g.

```xml
<bean id="kenyaEmrExampleAddonApp" class="org.openmrs.module.appframework.SimpleAppDescriptor">
  <property name="id" value="keaddonexample.example"/>
  <property name="label" value="Example"/>
  <property name="homepageUrl" value="keaddonexample/home.page"/>
  <property name="iconUrl" value="moduleResources/keaddonexample/images/apps/example.png"/>
</bean>
```

An add-on module can define as many new apps as it wants.

Adding new content
------------------

The KenyaEMR module contains 4 content managers which can be used to access existing content or register new content:

+ MetadataManager
+ RegimenManager
+ FormManager
+ ReportManager

### Adding new metadata

You can load a set of [metadata packages](https://wiki.openmrs.org/display/docs/Metadata+Sharing+Module) easily using 
the metadata manager. To do this the module has an XML file which lists it's packages, e.g.

```xml
<packages xmlns="http://www.kenyaemr.org/packages">
  <package groupUuid="b3dd6a3c-ecae-45e9-86a8-17c1f2f38da9" filename="metadata/Example_Addon_Metadata-1.zip" />
</packages>
```
This is loaded in the module activator as follows:

```java
InputStream stream = getClass().getClassLoader().getResourceAsStream(PACKAGES_FILENAME);
KenyaEmr.getInstance().getMetadataManager().loadPackagesFromXML(stream, getClass().getClassLoader());
```     

### Registering a form

Once you've added an HTML form via a metadata package, you can register it so that it's used within the main Kenya EMR
UI. The example module registers a new form in it's module activator like this:

```java
KenyaEmr.getInstance().getFormManager().registerForm(
  ExampleConstants.EXAMPLE_ADDON_FORM_UUID,
  FormConfig.Frequency.VISIT,
  new String[] { "kenyaemr.medicalEncounter", "kenyaemr.medicalChart" }
);
```

### Adding a new report

Reports in the Kenya EMR are created from ReportBuilders which are registered components. To define your own report
simply create a component which extends ReportBuilder, e.g.

```java
@Component
public class ExampleReport extends ReportBuilder {
  ...
}
```

Using the Kenya EMR UI
----------------------

Example also uses the [uiframework module](https://wiki.openmrs.org/display/docs/UI+Framework) and can access any UI
resource from the [Kenya EMR](https://github.com/I-TECH/openmrs-module-kenyaemr) module or [Kenya UI](https://github.com/I-TECH/openmrs-module-kenyaui) module. To use UI resources from the Kenya EMR module, the add-on module just has to specify
the provider name, e.g.

```gsp
<% ui.decorateWith("kenyaemr", "standardPage") %>

<% ui.includeFragment("kenyaui", "widget/button", [ label: "Ok" ]) %>
```

To access the KenyaEmrUiUtils class which contains useful methods for formatting objects, use the @SpringBean annotation
in a controller's parameter list, e.g.

```java
public void controller(PageModel model, UiUtils ui, @SpringBean KenyaEmrUiUtils kenyaUi) {
  ...
}
```
