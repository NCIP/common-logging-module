<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<table summary="" cellpadding="0" cellspacing="0" border="0" width="250"
	height="100%">

	<tr>
		<td class="subMenuPrimaryTitle" height="21"><bean:message
			key="label.query_section" /><!-- anchor to skip sub menu --><a
			href="#content"><img src="images/shim.gif" alt="Skip Menu" width="1"
			height="1" border="0" /></a></td>
	</tr>
	<tr>
		<td valign="top" class="contentPage">
		<table summary="" cellpadding="3" cellspacing="0" border="0">
		<html:form  styleId="QueryForm" action="/Query">
		<html:hidden property="activity" value="submitQuery"/>
		
			<tr>
				<td class="formMessage" colspan="3"><bean:message
					key="label.required_indicator" /></td>
			</tr>
			<tr>
				<td class="formTitle" height="20" colspan="3"><bean:message
					key="label.search_criteria" /></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5>&nbsp;</td>
				<td class=formLabel><LABEL for=field3><bean:message
					key="label.loglevel_type" /></LABEL></td>
				<td class=formField><SELECT class=formFieldSized id=logLevel size=1
					name=logLevel>
					<option value="" selected>All Levels</option>
					<option value="DEBUG">DEBUG</option>
					<option value="INFO">INFO</option>
					<option value="WARN">WARN</option>
					<option value="ERROR">ERROR</option>
					<option value="FATAL">FATAL</option>
				</SELECT></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=field1><bean:message
					key="label.application" /></td>
				<td class=formField>
					<INPUT class="formFieldSized" id="application" size="30" name="application" value="<bean:write name="queryForm" property="application"/>">
				</td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5>&nbsp;</td>
				<td class=formLabel><LABEL for=server><bean:message
					key="label.server" /></LABEL></td>
				<td class=formField><SELECT class=formFieldSized id=server size=1
					name=server>
					<OPTION value="">All Levels</OPTION>
				</SELECT></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5>*</td>
				<td class=formLabel><LABEL for=user><bean:message key="label.user" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=user size=30
					name=user value="<bean:write name="queryForm" property="user"/>"></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=organization><bean:message
					key="label.organization" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=organization
					size=30 name=organization value="<bean:write name="queryForm" property="organization"/>"></td>
			</tr>

			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=sessionID><bean:message
					key="label.session_id" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=sessionID size=30
					name=sessionID></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=message><bean:message
					key="label.message" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=message size=30
					name=message></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=ndc><bean:message key="label.ndc" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=ndc size=30
					name=ndc></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=thread><bean:message
					key="label.thread" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=thread size=30
					name=thread></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=throwable><bean:message
					key="label.throwable" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=throwable size=30
					name=throwable></td>
			</tr>			
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=objectID><bean:message
					key="label.object_id" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=objectID size=10
					name=objectID></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=objectName><bean:message
					key="label.object_name" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=objectName
					size=30 name=objectName></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=operation><bean:message
					key="label.operation" /></LABEL></td>
				<td class=formField><INPUT class=formFieldSized id=operation
					size=30 name=operation></td>
			</tr>
			
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=startDate><bean:message
					key="label.start_date" /></LABEL></td>
				<td class=formField><INPUT class=formField id=startDate size=10
					name=startDate value="<bean:write name="queryForm" property="startDate"/>"> <bean:message key="label.date_format" /></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=startTime><bean:message
					key="label.start_time" /></LABEL></td>
				<td class=formField><INPUT class=formField id=startTime size=10
					name=startTime value="<bean:write name="queryForm" property="startTime"/>"> <bean:message key="label.time_format" /></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=endDate><bean:message
					key="label.end_date" /></LABEL></td>
				<td class=formField><INPUT class=formField id=endDate size=10
					name=endDate value="<bean:write name="queryForm" property="endDate"/>"> <bean:message key="label.date_format" /></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=endTime><bean:message
					key="label.end_time" /></LABEL></td>
				<td class=formField><INPUT class=formField id=endTime size=10
					name=endTime value="<bean:write name="queryForm" property="endTime"/>"> <bean:message key="label.time_format" /></td>
			</tr>
			<tr>
				<td class=formRequiredNotice width=5></td>
				<td class=formLabel><LABEL for=recordCount><bean:message
					key="label.max_record_count" /></LABEL></td>
				<td class=formField><INPUT class=formField id=recordCount size=4
					name=recordCount value="<bean:write name="queryForm" property="recordCount"/>"></td>
			</tr>
			<tr>
				<td align="right" colspan="3"><!-- action buttons begins -->
				<table cellpadding="4" cellspacing="0" border="0">
					<tr>
						<td><input class="actionButton" type="submit"
							value='<bean:message key="label.submit_button"/>' /></td>
						<td><input class="actionButton" type="reset"
							value='<bean:message key="label.reset_button"/>' /></td>
					</tr>
				</table>
				<!-- action buttons end --></td>
			</tr>
			</html:form>
			
		</table>
		</td>
	</tr>
	<tr>
		<td class="subMenuFooter" height="22">&nbsp;</td>
	</tr>
</table>
