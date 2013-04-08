/*L
 *  Copyright Ekagra Software Technologies
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-logging-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging.webapp.viewobjects;

import java.util.List;



public interface Page {

  boolean isFirstPage();

  boolean isLastPage();

  boolean hasNextPage();

  boolean hasPreviousPage();

  int getLastPageNumber();

  List getThisPageElements();



  int getTotalNumberOfElements();

  int getThisPageFirstElementNumber();

  int getThisPageLastElementNumber();

  int getNextPageNumber();

  int getPreviousPageNumber();

  int getPageSize();

  int getPageNumber();
}