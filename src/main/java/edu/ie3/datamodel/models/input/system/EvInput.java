/*
 * © 2020. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.datamodel.models.input.system;

import edu.ie3.datamodel.models.OperationTime;
import edu.ie3.datamodel.models.input.NodeInput;
import edu.ie3.datamodel.models.input.OperatorInput;
import edu.ie3.datamodel.models.input.system.type.EvTypeInput;
import java.util.Objects;
import java.util.UUID;

/** Describes an electric vehicle */
public class EvInput extends SystemParticipantInput {
  /** Type of this EV, containing default values for EVs of this kind */
  private final EvTypeInput type;
  /**
   * Constructor for an operated electric vehicle
   *
   * @param uuid of the input entity
   * @param operationTime Time for which the entity is operated
   * @param operator of the asset
   * @param id of the asset
   * @param node the asset is connected to
   * @param qCharacteristics
   * @param type of EV
   */
  public EvInput(
      UUID uuid,
      OperationTime operationTime,
      OperatorInput operator,
      String id,
      NodeInput node,
      String qCharacteristics,
      EvTypeInput type) {
    super(uuid, operationTime, operator, id, node, qCharacteristics);
    this.type = type;
  }

  /**
   * Constructor for a non-operated electric vehicle
   *
   * @param uuid of the input entity
   * @param id of the asset
   * @param node the asset is connected to
   * @param qCharacteristics
   * @param type of EV
   */
  public EvInput(UUID uuid, String id, NodeInput node, String qCharacteristics, EvTypeInput type) {
    super(uuid, id, node, qCharacteristics);
    this.type = type;
  }

  public EvTypeInput getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    EvInput evInput = (EvInput) o;
    return Objects.equals(type, evInput.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), type);
  }
}