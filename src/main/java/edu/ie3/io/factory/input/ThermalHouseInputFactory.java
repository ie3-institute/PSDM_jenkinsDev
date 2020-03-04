/*
 * © 2020. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.io.factory.input;

import edu.ie3.models.OperationTime;
import edu.ie3.models.StandardUnits;
import edu.ie3.models.input.OperatorInput;
import edu.ie3.models.input.thermal.ThermalBusInput;
import edu.ie3.models.input.thermal.ThermalHouseInput;
import edu.ie3.util.quantities.interfaces.HeatCapacity;
import edu.ie3.util.quantities.interfaces.ThermalConductance;

import javax.measure.Quantity;
import java.util.UUID;

public class ThermalHouseInputFactory
    extends AssetInputEntityFactory<ThermalHouseInput, ThermalUnitInputEntityData> {
  private static final String ETH_LOSSES = "ethlosses";
  private static final String ETH_CAPA = "ethcapa";

  public ThermalHouseInputFactory() {
    super(ThermalHouseInput.class);
  }

  @Override
  protected String[] getAdditionalFields() {
    return new String[] {ETH_LOSSES, ETH_CAPA};
  }

  @Override
  protected ThermalHouseInput buildModel(
      ThermalUnitInputEntityData data,
      UUID uuid,
      String id,
      OperatorInput operatorInput,
      OperationTime operationTime) {
    final ThermalBusInput busInput = data.getBusInput();
    final Quantity<ThermalConductance> ethLosses =
            data.getQuantity(ETH_LOSSES, StandardUnits.THERMAL_TRANSMISSION);
    final Quantity<HeatCapacity> ethCapa = data.getQuantity(ETH_CAPA, StandardUnits.HEAT_CAPACITY);
    return new ThermalHouseInput(uuid, id, busInput, ethLosses, ethCapa);
  }
}
