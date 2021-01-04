/*
 * © 2021. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.datamodel.models.input.thermal;

import edu.ie3.datamodel.models.OperationTime;
import edu.ie3.datamodel.models.input.AssetInput;
import edu.ie3.datamodel.models.input.OperatorInput;
import java.util.UUID;
import org.apache.commons.lang3.NotImplementedException;

/** A thermal bus, to which different {@link ThermalUnitInput} units may be connected */
public class ThermalBusInput extends AssetInput {
  /**
   * Constructor for an operated thermal bus
   *
   * @param uuid Unique identifier of a certain thermal bus
   * @param id of the asset
   * @param operator of the asset
   * @param operationTime Time for which the entity is operated
   */
  public ThermalBusInput(
      UUID uuid, String id, OperatorInput operator, OperationTime operationTime) {
    super(uuid, id, operator, operationTime);
  }

  /**
   * Constructor for an operated, always on thermal bus
   *
   * @param uuid Unique identifier of a certain thermal bus
   * @param id of the asset
   */
  public ThermalBusInput(UUID uuid, String id) {
    super(uuid, id);
  }

  @Override
  public UniqueEntityBuilder copy() {
    throw new NotImplementedException(
        "Copying of " + this.getClass().getSimpleName() + " entities is not supported yet!");
  }
}
