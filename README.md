# OpenCms Repository Manager Extension

## Brief description
This extension adds a new vaadin based View to the Launchpad called ***Module Repository***

With this extension you can install and uninstall modules from a remote location.

*(Currently a quick and dirty alpha implementation)* 

Tested with OpenCms version [10.5 beta](http://www.opencms.org/en/download/)

## How to use
- Install the repository manager module
- Change the admin username and password in the repomanager.properties file (within the classes folder of the module)
- Add a new repository (has its own content type) within the repositories folder of the module
    - or change the URL of the existing repository
- Restart the tomcat instance
- Open the Launchpad
- Launch the *Module Repository* extension

## Repository layout
Every exported module has to be represented as a link (HTML) with the CSS class **module** attached to it.

e.g:
```html
<a href="%MODULE_DOWNLOAD_URL%" class="module">%MODULE_NAME%</a>
```

Currently only this HTML layout is supported. I'm planning to add more layout types (JSON, XML, ...) in the future.

## The plan
The big plan behind this extension is to create an OpenCms community page.
A place where OpenCms users can share, comment and discuss their modules.
This community repository will become the **Main repository** for this extension.
Until then, local repositories will do.

## Todos
- Refactoring (removal of the quick-and-dirtiness)
- Store the admin username and password more securely (plain text is a no go)
- Add a better readme.md
- Add Javadoc
- Add Tests
- Implement module properties
- Implement different repository types
- Create the community repository

## Development
Do you want to contribute? That's great!
The project uses gradle as build system, you'll probably have to adjust the build.gradle file (the path of the tomcat instance may differ)


## License
[OpenCms](https://github.com/alkacon/opencms-core/blob/master/license.txt) is published under the LGPL license.
[vaadin](https://github.com/vaadin/vaadin/blob/master/LICENSE) is published under the ASL (2.0) license.