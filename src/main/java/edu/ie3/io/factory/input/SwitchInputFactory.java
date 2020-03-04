/*
 * © 2020. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.io.factory.input;

import edu.ie3.models.OperationTime;
import edu.ie3.models.input.NodeInput;
import edu.ie3.models.input.OperatorInput;
import edu.ie3.models.input.connector.SwitchInput;
import java.util.UUID;

public class SwitchInputFactory
    extends ConnectorInputEntityFactory<SwitchInput, ConnectorInputEntityData> {
  private static final String CLOSED = "closed";

  public SwitchInputFactory() {
    super(SwitchInput.class);
  }

  @Override
  protected String[] getAdditionalFields() {
    return new String[] {CLOSED};
  }

  @Override
  protected SwitchInput buildModel(
      ConnectorInputEntityData data,
      UUID uuid,
      String id,
      NodeInput nodeA,
      NodeInput nodeB,
      OperatorInput operatorInput,
      OperationTime operationTime) {
    final boolean closed = data.getBoolean(CLOSED);
    return new SwitchInput(uuid, operationTime, operatorInput, id, nodeA, nodeB, closed);
  }
}
