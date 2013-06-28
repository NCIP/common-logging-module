Welcome to the Common Logging Module (CLM) Project!
==================================================

Common Logging Module (CLM) is a open source project and it is written in Java using Hibernate, Spring Framework, Log4J technologies.
CLM is a set of tools that provides a flexible and comprehensive solution for auditing and logging. CLM includes Event Logging, Automated Object State Logging, Asynchronous Logging to database, Query API's and a web based Log Locator Tool.
 * Event Logging - This feature provides a log4j-based solution allowing users to log events. Since this feature provides the ability to propagate and store user information, it can be used for anything from auditing a user to detecting security breaches.
 * Automated Object State Logging - This tool provides an automated, Hibernate-based, object state logging mechanism to log the changes to an object's state.
 * Asynchronous Logging to database - This feature provides a log4j based JDBC appender that can log the messages to the database asynchronously. Asynchronous logging increases performance for applications that generate a high volume of log messages.
 * Query Interface - A new Query API provides programmatic access to the logged data. It also can return the log result in form of an XML document.
 * Web-based Log Locator Tool (LLT)- This tool is a web-based application that displays, searches, and filters log messages. The interface can be used to retrieve logs based on elements including timestamp, user ID, and log level. The LLT can now use CSM for User Provisioning as well as security.

CLM is flexible enough to allow application developers to integrate audit logging into almost any code design with minimal coding effort. This phase of the Common Logging Module brings its users one step closer to the goal of CFR 21 / part 11 (FDA) compliance.

Common Logging Module is distributed under the BSD 3-Clause License.
Please see the NOTICE and LICENSE files for details.

You will find more details about Common Logging Module in the following links:

 * [Community Wiki] (https://wiki.nci.nih.gov/x/4gBB)
 * [Issue Tracker] (https://tracker.nci.nih.gov/browse/CLM)
 * [Code Repository] (https://github.com/NCIP/common-logging-module)
 * [Documentation] (https://wiki.nci.nih.gov/x/4gBB) 
 * [Release Notes] (http://gforge.nci.nih.gov/docman/view.php/49/15150/CLM_4.1_Release_notes.txt)
 * [Installation Packages] (http://gforge.nci.nih.gov/frs/?group_id=49)
 * [Forum] (https://cabig-kc.nci.nih.gov/CaGrid/forums/viewforum.php?f=31&sid=317abcd4b7ae1f07e547e12f9c2b9059)

Please join us in further developing and improving Common Logging Module.

#Prerequisites
* JDK 1.5.x
* Apache Ant 1.7.x
* Apache Ivy 2.0.0 (Add ivy-2.0.0.jar to <ANT_HOME>/lib)

#Install
Please refer to [LLT user guide] (http://gforge.nci.nih.gov/docman/view.php/49/15169/LLT_User_Guide.pdf) for installation and setup procedure.

Please refer to chapter 9 of caCORE 3.2 technical guide (ftp://ftp1.nci.nih.gov/pub/cacore/caCORE3.2_Tech_Guide.pdf) for instructions on using the CLM API.

