Kenya EMR Example Add-on Module
===============================

This is an example of a add-on module for the Kenya EMR. It uses the [appframework module](https://wiki.openmrs.org/display/docs/App+Framework+Module) to define an app of its own.
The Kenya EMR module will show this app's icon on its homepage along with its own apps. Example also uses the
[uiframework module](https://wiki.openmrs.org/display/docs/UI+Framework) and can access any UI resource from the Kenya EMR module.

Technical Overview
------------------

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

To use UI resources from the Kenya EMR module, the add-on module just has to specify the provider name, e.g.

```gsp
<% ui.decorateWith("kenyaemr", "standardPage") %>
```
