/*
 * © 2020. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.datamodel.models.input.system;

import edu.ie3.datamodel.models.OperationTime;
import edu.ie3.datamodel.models.StandardUnits;
import edu.ie3.datamodel.models.input.NodeInput;
import edu.ie3.datamodel.models.input.OperatorInput;
import edu.ie3.datamodel.models.input.system.type.BmTypeInput;
import edu.ie3.util.quantities.interfaces.EnergyPrice;
import java.util.Objects;
import java.util.UUID;
import javax.measure.Quantity;

/** Describes a biomass plant */
public class BmInput extends SystemParticipantInput {
  /** Type of this BM plant, containing default values for BM plants of this kind */
  private final BmTypeInput type;
  /** Is this asset market oriented? */
  private final boolean marketReaction;
  /**
   * Does this plant increase the output power if the revenues exceed the energy generation costs?
   */
  private final boolean costControlled;
  /** Granted feed in tariff (typically in €/kWh) */
  private final Quantity<EnergyPrice> feedInTariff;

  /**
   * Constructor for an operated biomass plant
   *
   * @param uuid of the input entity
   * @param operationTime Time for which the entity is operated
   * @param operator of the asset
   * @param id of the asset
   * @param node the asset is connected to
   * @param qCharacteristics
   * @param type of BM
   * @param marketReaction Is this asset market oriented?
   * @param costControlled Does this plant increase the output power if the revenues exceed the
   *     energy generation costs?
   * @param feedInTariff Granted feed in tariff (typically in €/kWh)
   */
  public BmInput(
      UUID uuid,
      OperationTime operationTime,
      OperatorInput operator,
      String id,
      NodeInput node,
      String qCharacteristics,
      BmTypeInput type,
      boolean marketReaction,
      boolean costControlled,
      Quantity<EnergyPrice> feedInTariff) {
    super(uuid, operationTime, operator, id, node, qCharacteristics);
    this.type = type;
    this.marketReaction = marketReaction;
    this.costControlled = costControlled;
    this.feedInTariff = feedInTariff.to(StandardUnits.ENERGY_PRICE);
  }

  /**
   * Constructor for a non-operated biomass plant
   *
   * @param uuid of the input entity
   * @param id of the asset
   * @param node the asset is connected to
   * @param qCharacteristics
   * @param type of BM
   * @param marketReaction Is this asset market oriented?
   * @param costControlled Does this plant increase the output power if the revenues exceed the
   *     energy generation costs?
   * @param feedInTariff Granted feed in tariff (typically in €/kWh)
   */
  public BmInput(
      UUID uuid,
      String id,
      NodeInput node,
      String qCharacteristics,
      BmTypeInput type,
      boolean marketReaction,
      boolean costControlled,
      Quantity<EnergyPrice> feedInTariff) {
    super(uuid, id, node, qCharacteristics);
    this.type = type;
    this.marketReaction = marketReaction;
    this.costControlled = costControlled;
    this.feedInTariff = feedInTariff.to(StandardUnits.ENERGY_PRICE);
  }

  public BmTypeInput getType() {
    return type;
  }

  public boolean isMarketReaction() {
    return marketReaction;
  }

  public boolean isCostControlled() {
    return costControlled;
  }

  public Quantity<EnergyPrice> getFeedInTariff() {
    return feedInTariff;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    BmInput bmInput = (BmInput) o;
    return marketReaction == bmInput.marketReaction
        && costControlled == bmInput.costControlled
        && type.equals(bmInput.type)
        && feedInTariff.equals(bmInput.feedInTariff);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), type, marketReaction, costControlled, feedInTariff);
  }
}