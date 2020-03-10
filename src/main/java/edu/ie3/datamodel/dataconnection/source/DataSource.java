/*
 * © 2020. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.datamodel.dataconnection.source;

import edu.ie3.datamodel.dataconnection.dataconnectors.DataConnector;

/** Describes a class that fetches data from a persistence location */
public interface DataSource {

  /** @return the connector of this source */
  DataConnector getDataConnector();
}